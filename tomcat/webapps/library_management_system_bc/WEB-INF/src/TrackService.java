package library_management_class;
import library_management_class.ConnectionManager;
import java.sql.Connection;
import library_management_class.Track;
import library_management_class.Due;
import library_management_class.Reservation;
import library_management_class.TrackDao;
import java.util.ArrayList;
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

}