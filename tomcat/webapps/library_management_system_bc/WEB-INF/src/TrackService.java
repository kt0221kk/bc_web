package library_management_class;
import library_management_class.ConnectionManager;
import java.sql.Connection;
import library_management_class.*;
import java.sql.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.text.*;
import java.io.IOException;
public class TrackService{

    public TrackService(){
        System.out.println("TrackService");
    }
    public ArrayList<Track> getTrackList(){
        ArrayList<Track> trackList = new ArrayList<Track>();
        ConnectionManager cm = new ConnectionManager();
        Connection con = cm.getConnection();
        TrackDao trackDao = new TrackDao(con);
        trackList = trackDao.selectAll();
        //多態性を考慮したtrackListの作成
        ArrayList<Track> polymorphicTrackList = new ArrayList<Track>();
        for(Track track : trackList){
            if(track.getTrackStatus().equals("貸出")){
                Due due = new Due();
                DueDao dueDao = new DueDao(con);
                due = dueDao.selectByTrackId(track.getTrackId());
                due.setDue(track);
             } else if(track.getTrackStatus().equals("予約")){
                Reservation reservation = new Reservation();
                ReservationDao reservationDao = new ReservationDao(con);
                reservation = reservationDao.selectByTrackId(track.getTrackId());
                reservation.setReservation(track);
            } else {
                polymorphicTrackList.add(track);
            }
     }
        cm.closeConnection();
        return polymorphicTrackList;
    }

    public ArrayList<Track> getTrackListByBookId(int bookId){
        ArrayList<Track> trackList = new ArrayList<Track>();
        ConnectionManager cm = new ConnectionManager();
        Connection con = cm.getConnection();
        TrackDao trackDao = new TrackDao(con);
        trackList = trackDao.selectByBookId(bookId);
        //多態性を考慮したtrackListの作成
        ArrayList<Track> polymorphicTrackList = new ArrayList<Track>();
        for(Track track : trackList){
            if(track.getTrackStatus().equals("貸出")){
                Due due = new Due();
                DueDao dueDao = new DueDao(con);
                due = dueDao.selectByTrackId(track.getTrackId());
                due.setDue(track);
                polymorphicTrackList.add(due);
             } else if(track.getTrackStatus().equals("予約")){
                Reservation reservation = new Reservation();
                ReservationDao reservationDao = new ReservationDao(con);
                reservation = reservationDao.selectByTrackId(track.getTrackId());
                reservation.setReservation(track);
                polymorphicTrackList.add(reservation);
            } else {
                polymorphicTrackList.add(track);
            }
     }
        cm.closeConnection();
        return polymorphicTrackList;
    }
    public Track getTrackByTrackId(int trackId){
        Track track = new Track();
        ConnectionManager cm = new ConnectionManager();
        Connection con = cm.getConnection();
        TrackDao trackDao = new TrackDao(con);
        track = trackDao.selectByTrackId(trackId);
        if(track.getTrackStatus().equals("貸出")){
            Due due = new Due();
            DueDao dueDao = new DueDao(con);
            due = dueDao.selectByTrackId(track.getTrackId());
            due.setDue(track);
            track = due;
        } else if(track.getTrackStatus().equals("予約")){
            Reservation reservation = new Reservation();
            ReservationDao reservationDao = new ReservationDao(con);
            reservation = reservationDao.selectByTrackId(track.getTrackId());
            reservation.setReservation(track);
            track = reservation;
        }
        cm.closeConnection();
        return track;
    }
    public int registerReservationTrack(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException, ParseException{
        ConnectionManager cm = new ConnectionManager();
        // トランザクション開始 
        Connection con = cm.getConnection();
        // トラックの登録
        TrackDao trackDao = new TrackDao(con);
        Track track = new Track();
        track.setBookId(Integer.parseInt(request.getParameter("book_id")));
        String trackStatus = request.getParameter("track_status");
        track.setTrackStatus("予約");
        track.setUserId(Integer.parseInt(request.getParameter("user_id")));
        int track_id = trackDao.insert(track);
        System.out.println("TrackService,track_id:" + track_id);
        // 予約の登録
        Reservation reservation = new Reservation();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        reservation.setTrackId(track_id);
        reservation.setReservationStartDate(sdf.parse(request.getParameter("start")));
        reservation.setReservationEndDate(sdf.parse(request.getParameter("end")));
        reservation.setIsActive(true);
        ReservationDao reservationDao = new ReservationDao(con);
        reservationDao.insert(reservation);
        // 書籍情報の更新は予約は登録時には行わない
        // トランザクション終了

        cm.commit();
        cm.closeConnection();
        return track_id;


    }
    public int registerReturnTrack(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException, ParseException{
        ConnectionManager cm = new ConnectionManager();
        // トランザクション開始 
        Connection con = cm.getConnection();
        // トラックの登録
        TrackDao trackDao = new TrackDao(con);
        Track track = new Track();
        track.setBookId(Integer.parseInt(request.getParameter("book_id")));
        track.setTrackStatus("返却");
        track.setUserId(Integer.parseInt(request.getParameter("user_id")));
        int track_id = trackDao.insert(track);
        // 書籍情報の更新
        BookDao bookDao = new BookDao(con);
        Book book = bookDao.selectById(Integer.parseInt(request.getParameter("book_id")));
        book.setStatus("貸出可能");
        bookDao.update(book);
        // トランザクション終了
        cm.commit();
        cm.closeConnection();
        return track_id;
    }
    public int registerDueTrack(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException, ParseException{
        ConnectionManager cm = new ConnectionManager();
        // トランザクション開始 
        Connection con = cm.getConnection();
        // トラックの登録
        TrackDao trackDao = new TrackDao(con);
        Track track = new Track();
        track.setBookId(Integer.parseInt(request.getParameter("book_id")));
        track.setTrackStatus("貸出");
        track.setUserId(Integer.parseInt(request.getParameter("user_id")));
        int track_id = trackDao.insert(track);
        // 貸出の登録
        Due due = new Due();
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        due.setTrackId(track_id);
        due.setBorrowDate(sdf.parse(request.getParameter("start")));
        due.setReturnDueDate(sdf.parse(request.getParameter("end")));
        DueDao dueDao = new DueDao(con);
        dueDao.insert(due);
        // 書籍情報の更新
        BookDao bookDao = new BookDao(con);
        int bookId = Integer.parseInt(request.getParameter("book_id"));
        System.out.println("bookId:" + bookId);
        Book book = bookDao.selectById(bookId);
        String trackStatus = book.getStatus();
        book.setStatus("貸出中");
        bookDao.update(book);
        //ユーザ情報取得
        UserDao userDao = new UserDao(con);
        User user = userDao.selectByUserId(Integer.parseInt(request.getParameter("user_id")));
        // トランザクション終了
        if(trackStatus.equals("貸出中")){
            cm.rollback();
        }else{
            cm.commit();
        }
        cm.closeConnection();
        request.setAttribute("due", due);
        request.setAttribute("book", book);
        request.setAttribute("user",user);
        return track_id;
    }
    public int changeReservationToDue(HttpServletRequest request, HttpServletResponse response){
        int trackId = Integer.parseInt(request.getParameter("track_id"));
        ConnectionManager cm = new ConnectionManager();
        // トランザクション開始 
        Connection con = cm.getConnection();
        TrackService trackService = new TrackService();
        Track my_track = trackService.getTrackByTrackId(trackId);
        // トラックの登録
        TrackDao trackDao = new TrackDao(con);
        Track track = new Track();
        track.setBookId(my_track.getBookId());
        track.setTrackStatus("貸出");
        track.setUserId(my_track.getUserId());
        int track_id = trackDao.insert(track);
        track.setTrackId(track_id);
        // 貸出の登録
        Due due = new Due();
        Reservation reservation = (Reservation)my_track;
        due.setTrackId(track_id);
        due.setBorrowDate(reservation.getReservationStartDate());
        due.setReturnDueDate(reservation.getReservationEndDate());
        DueDao dueDao = new DueDao(con);
        dueDao.insert(due);
        //予約情報の更新
        ReservationDao reservationDao = new ReservationDao(con);
        reservation.setIsActive(false);
        reservationDao.updateIsActive(reservation);
        // 書籍情報の更新
        BookDao bookDao = new BookDao(con);
        int bookId = Integer.parseInt(request.getParameter("book_id"));
        System.out.println("bookId:" + bookId);
        Book book = bookDao.selectById(bookId);
        String trackStatus = book.getStatus();
        book.setStatus("貸出中");
        bookDao.update(book);
        //ユーザ情報取得
        UserDao userDao = new UserDao(con);
        User user = userDao.selectByUserId(Integer.parseInt(request.getParameter("user_id")));
        // トランザクション終了
        if(trackStatus.equals("貸出中")){
            cm.rollback();
        }else{
            cm.commit();
        }
        cm.closeConnection();
        request.setAttribute("track", track);
        request.setAttribute("book", book);
        request.setAttribute("user",user);
        return track_id;
    }

    public int cancelReservation(HttpServletRequest request, HttpServletResponse response){
        int trackId = Integer.parseInt(request.getParameter("track_id"));
        ConnectionManager cm = new ConnectionManager();
        // トランザクション開始 
        Connection con = cm.getConnection();
        Track my_track = getTrackByTrackId(trackId);
        //予約情報の更新
        ReservationDao reservationDao = new ReservationDao(con);
        Reservation reservation = (Reservation)my_track;
        reservation.setIsActive(false);
        reservationDao.updateIsActive(reservation);
        // トランザクション終了
        cm.commit();
        cm.closeConnection();
        request.setAttribute("track", reservation);

        return trackId;
    }

}