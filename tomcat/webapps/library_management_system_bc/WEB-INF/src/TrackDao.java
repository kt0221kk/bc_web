package library_management_class;
import java.util.ArrayList;
import library_management_class.Track;
import library_management_class.TrackDao;
import library_management_class.Due;
import library_management_class.Reservation;
import java.sql.*;
import java.util.Date;

import java.sql.Timestamp;
import java.time.LocalDateTime; 
import java.time.ZonedDateTime;
import java.time.ZoneId;
public class TrackDao{
    private Connection connection;
    public TrackDao(Connection connection) {
        super();
        this.connection = connection;
    }
    public ArrayList<Track> selectAll() {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Track> trackList = new ArrayList<Track>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM track_tbl");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if(resultSet.getString("track_status").equals("返却")||resultSet.getString("track_status").equals("予約取消")){
                    Track track = new Track();
                    track.setTrackId(resultSet.getInt("track_id"));
                    track.setBookId(resultSet.getInt("book_id"));
                    track.setUserId(resultSet.getInt("user_id"));
                    track.setTrackStatus(resultSet.getString("track_status"));
                    track.setTrackTime(resultSet.getDate("track_time"));
                    trackList.add(track);
                }else if(resultSet.getString("track_status").equals("貸出")){
                    Due due = new Due();
                    due.setTrackId(resultSet.getInt("track_id"));
                    due.setBookId(resultSet.getInt("book_id"));
                    due.setUserId(resultSet.getInt("user_id"));
                    due.setTrackStatus(resultSet.getString("track_status"));
                    due.setTrackTime(resultSet.getDate("track_time"));
                    try{
                        preparedStatement = connection.prepareStatement("SELECT * FROM due_tbl WHERE track_id = ?");
                        preparedStatement.setInt(1, resultSet.getInt("track_id"));
                        ResultSet resultSet2 = preparedStatement.executeQuery();
                        while(resultSet2.next()){
                            due.setBorrowDate(resultSet2.getDate("borrow_date"));
                            due.setReturnDueDate(resultSet2.getDate("return_due_date"));
                        }
                    }catch(SQLException e){
                        throw new RuntimeException("due_tblのSELECTに失敗しました", e);
                    }finally{
                        try{
                            if (preparedStatement != null){
                                preparedStatement.close();
                            }
                        }catch(SQLException e){
                            throw new RuntimeException("本情報検索時のステートメントの解放に失敗しました", e);
                        }
                    }
                    trackList.add(due);
                }else if(resultSet.getString("track_status").equals("予約")){
                    Reservation reservation = new Reservation();
                    reservation.setTrackId(resultSet.getInt("track_id"));
                    reservation.setBookId(resultSet.getInt("book_id"));
                    reservation.setUserId(resultSet.getInt("user_id"));
                    reservation.setTrackStatus(resultSet.getString("track_status"));
                    reservation.setTrackTime(resultSet.getDate("track_time"));
                    try{
                        preparedStatement = connection.prepareStatement("SELECT * FROM reservation_tbl WHERE track_id = ?");
                        preparedStatement.setInt(1, resultSet.getInt("track_id"));
                        ResultSet resultSet2 = preparedStatement.executeQuery();
                        while(resultSet2.next()){
                            reservation.setReservationStartDate(resultSet2.getDate("reservation_start_date"));
                            reservation.setReservationEndDate(resultSet2.getDate("reservation_end_date"));
                            reservation.setIsActive(resultSet2.getBoolean("is_active"));
                        }
                    }catch(SQLException e){
                        throw new RuntimeException("reservation_tblのSELECTに失敗しました", e);
                    }finally{
                        try{
                            if (preparedStatement != null){
                                preparedStatement.close();
                            }
                        }catch(SQLException e){
                            throw new RuntimeException("本情報検索時のステートメントの解放に失敗しました", e);
                        }
                    }
                    trackList.add(reservation);
                }
            }
            return trackList;
        } catch (SQLException e) {
            throw new RuntimeException("本情報検索時にエラーが発生しました", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("本情報検索時のステートメントの解放に失敗しました", e);
            }
            return trackList; // 追加した行
        }
        

    }
    public int insert(Track track) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO track_tbl (book_id, user_id, track_status, track_time) VALUES (?, ?, ?, ?)");
            preparedStatement.setInt(1, track.getBookId());
            preparedStatement.setInt(2, track.getUserId());
            preparedStatement.setString(3, track.getTrackStatus());
            // LocalDateTime now = LocalDateTime.now();
            // Timestamp timestamp = Timestamp.valueOf(now);
            ZonedDateTime nowInJapan = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
            // ZonedDateTimeをLocalDateTimeに変換
            LocalDateTime localDateTimeInJapan = nowInJapan.toLocalDateTime();
            // LocalDateTimeをTimestampに変換
            Timestamp timestampInJapan = Timestamp.valueOf(localDateTimeInJapan);
            preparedStatement.setTimestamp(4, timestampInJapan);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("本情報登録時にエラーが発生しました", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("本情報登録時のステートメントの解放に失敗しました", e);
            }
        }
    }



    public ArrayList<Track> selectByStatus(String status) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Track> trackList = new ArrayList<Track>();
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM track_tbl WHERE track_status = ?");
            preparedStatement.setString(1, status);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if(resultSet.getString("track_status").equals("返却")||resultSet.getString("track_status").equals("予約取消")){
                    Track track = new Track();
                    track.setTrackId(resultSet.getInt("track_id"));
                    track.setBookId(resultSet.getInt("book_id"));
                    track.setUserId(resultSet.getInt("user_id"));
                    track.setTrackStatus(resultSet.getString("track_status"));
                    track.setTrackTime(resultSet.getDate("track_time"));
                    trackList.add(track);
                }else if(resultSet.getString("track_status").equals("貸出")){
                    Due due = new Due();
                    due.setTrackId(resultSet.getInt("track_id"));
                    due.setBookId(resultSet.getInt("book_id"));
                    due.setUserId(resultSet.getInt("user_id"));
                    due.setTrackStatus(resultSet.getString("track_status"));
                    due.setTrackTime(resultSet.getDate("track_time"));
                    try{
                        preparedStatement = connection.prepareStatement("SELECT * FROM due_tbl WHERE track_id = ?");
                        preparedStatement.setInt(1, resultSet.getInt("track_id"));
                        ResultSet resultSet2 = preparedStatement.executeQuery();
                        while(resultSet2.next()){
                            due.setBorrowDate(resultSet2.getDate("borrow_date"));
                            due.setReturnDueDate(resultSet2.getDate("return_due_date"));
                        }
                    }catch(SQLException e){
                        throw new RuntimeException("due_tblのSELECTに失敗しました", e);
                    }finally{
                        try{
                            if (preparedStatement != null){
                                preparedStatement.close();
                            }
                        }catch(SQLException e){
                            throw new RuntimeException("本情報検索時のステートメントの解放に失敗しました", e);
                        }
                    }
                    trackList.add(due);
                }else if(resultSet.getString("track_status").equals("予約")){
                    Reservation reservation = new Reservation();
                    reservation.setTrackId(resultSet.getInt("track_id"));
                    reservation.setBookId(resultSet.getInt("book_id"));
                    reservation.setUserId(resultSet.getInt("user_id"));
                    reservation.setTrackStatus(resultSet.getString("track_status"));
                    reservation.setTrackTime(resultSet.getDate("track_time"));
                    try{
                        preparedStatement = connection.prepareStatement("SELECT * FROM reservation_tbl WHERE track_id = ?");
                        preparedStatement.setInt(1, resultSet.getInt("track_id"));
                        ResultSet resultSet2 = preparedStatement.executeQuery();
                        while(resultSet2.next()){
                            reservation.setReservationStartDate(resultSet2.getDate("reservation_start_date"));
                            reservation.setReservationEndDate(resultSet2.getDate("reservation_end_date"));
                            reservation.setIsActive(resultSet2.getBoolean("is_active"));
                        }
                    }catch(SQLException e){
                        throw new RuntimeException("reservation_tblのSELECTに失敗しました", e);
                    }finally{
                        try{
                            if (preparedStatement != null){
                                preparedStatement.close();
                            }
                        }catch(SQLException e){
                            throw new RuntimeException("本情報検索時のステートメントの解放に失敗しました", e);
                        }
                    }
                    trackList.add(reservation);
                }
            }
            return trackList;

        }catch(SQLException e){
            throw new RuntimeException("本情報検索時にエラーが発生しました", e);
        }finally{
            try{
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch(SQLException e){
                throw new RuntimeException("本情報検索時のステートメントの解放に失敗しました", e);
            }
        }
    }
    public ArrayList<Track> selectByBookId(int bookId){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Track> trackList = new ArrayList<Track>();
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM track_tbl WHERE book_id = ?");
            preparedStatement.setInt(1, bookId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                if(resultSet.getString("track_status").equals("返却")||resultSet.getString("track_status").equals("予約取消")){
                    Track track = new Track();
                    track.setTrackId(resultSet.getInt("track_id"));
                    track.setBookId(resultSet.getInt("book_id"));
                    track.setUserId(resultSet.getInt("user_id"));
                    track.setTrackStatus(resultSet.getString("track_status"));
                    track.setTrackTime(resultSet.getDate("track_time"));
                    trackList.add(track);
                }else if(resultSet.getString("track_status").equals("貸出")){
                    Due due = new Due();
                    due.setTrackId(resultSet.getInt("track_id"));
                    due.setBookId(resultSet.getInt("book_id"));
                    due.setUserId(resultSet.getInt("user_id"));
                    due.setTrackStatus(resultSet.getString("track_status"));
                    due.setTrackTime(resultSet.getDate("track_time"));
                    try{
                        preparedStatement = connection.prepareStatement("SELECT * FROM due_tbl WHERE track_id = ?");
                        preparedStatement.setInt(1, resultSet.getInt("track_id"));
                        ResultSet resultSet2 = preparedStatement.executeQuery();
                        while(resultSet2.next()){
                            due.setBorrowDate(resultSet2.getDate("borrow_date"));
                            due.setReturnDueDate(resultSet2.getDate("return_due_date"));
                        }
                    }catch(SQLException e){
                        throw new RuntimeException("due_tblのSELECTに失敗しました", e);
                    }finally{
                        try{
                            if (preparedStatement != null){
                                preparedStatement.close();
                            }
                        }catch(SQLException e){
                            throw new RuntimeException("本情報検索時のステートメントの解放に失敗しました", e);
                        }
                    }
                    trackList.add(due);
                }else if(resultSet.getString("track_status").equals("予約")){
                    Reservation reservation = new Reservation();
                    reservation.setTrackId(resultSet.getInt("track_id"));
                    reservation.setBookId(resultSet.getInt("book_id"));
                    reservation.setUserId(resultSet.getInt("user_id"));
                    reservation.setTrackStatus(resultSet.getString("track_status"));
                    reservation.setTrackTime(resultSet.getDate("track_time"));
                    try{
                        preparedStatement = connection.prepareStatement("SELECT * FROM reservation_tbl WHERE track_id = ?");
                        preparedStatement.setInt(1, resultSet.getInt("track_id"));
                        ResultSet resultSet2 = preparedStatement.executeQuery();
                        while(resultSet2.next()){
                            reservation.setReservationStartDate(resultSet2.getDate("reservation_start_date"));
                            reservation.setReservationEndDate(resultSet2.getDate("reservation_end_date"));
                            reservation.setIsActive(resultSet2.getBoolean("is_active"));
                        }
                    }catch(SQLException e){
                        throw new RuntimeException("reservation_tblのSELECTに失敗しました", e);
                    }finally{
                        try{
                            if (preparedStatement != null){
                                preparedStatement.close();
                            }
                        }catch(SQLException e){
                            throw new RuntimeException("本情報検索時のステートメントの解放に失敗しました", e);
                        }
                    }
                    trackList.add(reservation);
                }
            }
            return trackList;
        }catch(SQLException e){
            throw new RuntimeException("本情報検索時にエラーが発生しました", e);
        }finally{
            try{
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch(SQLException e){
                throw new RuntimeException("本情報検索時のステートメントの解放に失敗しました", e);
            }
        }
        


    }
    public ArrayList<Track> selectByUserId(int userId){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Track> trackList = new ArrayList<Track>();
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM track_tbl WHERE user_id = ?");
            preparedStatement.setInt(1, userId);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                if(resultSet.getString("track_status").equals("返却")||resultSet.getString("track_status").equals("予約取消")){
                    Track track = new Track();
                    track.setTrackId(resultSet.getInt("track_id"));
                    track.setBookId(resultSet.getInt("book_id"));
                    track.setUserId(resultSet.getInt("user_id"));
                    track.setTrackStatus(resultSet.getString("track_status"));
                    track.setTrackTime(resultSet.getDate("track_time"));
                    trackList.add(track);
                }else if(resultSet.getString("track_status").equals("貸出")){
                    Due due = new Due();
                    due.setTrackId(resultSet.getInt("track_id"));
                    due.setBookId(resultSet.getInt("book_id"));
                    due.setUserId(resultSet.getInt("user_id"));
                    due.setTrackStatus(resultSet.getString("track_status"));
                    due.setTrackTime(resultSet.getDate("track_time"));
                    try{
                        preparedStatement = connection.prepareStatement("SELECT * FROM due_tbl WHERE track_id = ?");
                        preparedStatement.setInt(1, resultSet.getInt("track_id"));
                        ResultSet resultSet2 = preparedStatement.executeQuery();
                        while(resultSet2.next()){
                            due.setBorrowDate(resultSet2.getDate("borrow_date"));
                            due.setReturnDueDate(resultSet2.getDate("return_due_date"));
                        }
                    }catch(SQLException e){
                        throw new RuntimeException("due_tblのSELECTに失敗しました", e);
                    }finally{
                        try{
                            if (preparedStatement != null){
                                preparedStatement.close();
                            }
                        }catch(SQLException e){
                            throw new RuntimeException("本情報検索時のステートメントの解放に失敗しました", e);
                        }
                    }
                    trackList.add(due);
                }else if(resultSet.getString("track_status").equals("予約")){
                    Reservation reservation = new Reservation();
                    reservation.setTrackId(resultSet.getInt("track_id"));
                    reservation.setBookId(resultSet.getInt("book_id"));
                    reservation.setUserId(resultSet.getInt("user_id"));
                    reservation.setTrackStatus(resultSet.getString("track_status"));
                    reservation.setTrackTime(resultSet.getDate("track_time"));
                    try{
                        preparedStatement = connection.prepareStatement("SELECT * FROM reservation_tbl WHERE track_id = ?");
                        preparedStatement.setInt(1, resultSet.getInt("track_id"));
                        ResultSet resultSet2 = preparedStatement.executeQuery();
                        while(resultSet2.next()){
                            reservation.setReservationStartDate(resultSet2.getDate("reservation_start_date"));
                            reservation.setReservationEndDate(resultSet2.getDate("reservation_end_date"));
                            reservation.setIsActive(resultSet2.getBoolean("is_active"));
                        }
                    }catch(SQLException e){
                        throw new RuntimeException("reservation_tblのSELECTに失敗しました", e);
                    }finally{
                        try{
                            if (preparedStatement != null){
                                preparedStatement.close();
                            }
                        }catch(SQLException e){
                            throw new RuntimeException("本情報検索時のステートメントの解放に失敗しました", e);
                        }
                    }
                    trackList.add(reservation);
                }
            }
            return trackList;
        }catch(SQLException e){
            throw new RuntimeException("track_tblのSELECTに失敗しました", e);
        }finally{
            try{
                if (preparedStatement != null){
                    preparedStatement.close();
                }
                if (resultSet != null){
                    resultSet.close();
                }
            }catch(SQLException e){
                throw new RuntimeException("本情報検索時のステートメントの解放に失敗しました", e);
            }
        }
        
    }
    //start_dateからend_dateまでの間の貸出履歴を返す
    public ArrayList<Track> selectByTime(Date start_date,Date end_date){
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        ArrayList<Track> trackList = new ArrayList<Track>();
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM track_tbl WHERE track_time BETWEEN ? AND ?");
            preparedStatement.setDate(1,new java.sql.Date(start_date.getTime()));
            preparedStatement.setDate(2,new java.sql.Date(end_date.getTime()));
            // preparedStatement.setDate(2,Date(end_date.getTime()));
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                if(resultSet.getString("track_status").equals("返却")||resultSet.getString("track_status").equals("予約取消")){
                    Track track = new Track();
                    track.setTrackId(resultSet.getInt("track_id"));
                    track.setBookId(resultSet.getInt("book_id"));
                    track.setUserId(resultSet.getInt("user_id"));
                    track.setTrackStatus(resultSet.getString("track_status"));
                    track.setTrackTime(resultSet.getDate("track_time"));
                    trackList.add(track);
                }else if(resultSet.getString("track_status").equals("貸出")){
                    Due due = new Due();
                    due.setTrackId(resultSet.getInt("track_id"));
                    due.setBookId(resultSet.getInt("book_id"));
                    due.setUserId(resultSet.getInt("user_id"));
                    due.setTrackStatus(resultSet.getString("track_status"));
                    due.setTrackTime(resultSet.getDate("track_time"));
                    try{
                        preparedStatement = connection.prepareStatement("SELECT * FROM due_tbl WHERE track_id = ?");
                        preparedStatement.setInt(1, resultSet.getInt("track_id"));
                        ResultSet resultSet2 = preparedStatement.executeQuery();
                        while(resultSet2.next()){
                            due.setBorrowDate(resultSet2.getDate("borrow_date"));
                            due.setReturnDueDate(resultSet2.getDate("return_due_date"));
                        }
                    }catch(SQLException e){
                        throw new RuntimeException("due_tblのSELECTに失敗しました", e);
                    }finally{
                        try{
                            if (preparedStatement != null){
                                preparedStatement.close();
                            }
                        }catch(SQLException e){
                            throw new RuntimeException("本情報検索時のステートメントの解放に失敗しました", e);
                        }
                    }
                    trackList.add(due);
                }else if(resultSet.getString("track_status").equals("予約")){
                    Reservation reservation = new Reservation();
                    reservation.setTrackId(resultSet.getInt("track_id"));
                    reservation.setBookId(resultSet.getInt("book_id"));
                    reservation.setUserId(resultSet.getInt("user_id"));
                    reservation.setTrackStatus(resultSet.getString("track_status"));
                    reservation.setTrackTime(resultSet.getDate("track_time"));
                    try{
                        preparedStatement = connection.prepareStatement("SELECT * FROM reservation_tbl WHERE track_id = ?");
                        preparedStatement.setInt(1, resultSet.getInt("track_id"));
                        ResultSet resultSet2 = preparedStatement.executeQuery();
                        while(resultSet2.next()){
                            reservation.setReservationStartDate(resultSet2.getDate("reservation_start_date"));
                            reservation.setReservationEndDate(resultSet2.getDate("reservation_end_date"));
                            reservation.setIsActive(resultSet2.getBoolean("is_active"));
                        }
                        trackList.add(reservation);
                    }catch(SQLException e){
                        throw new RuntimeException("reservation_tblのSELECTに失敗しました", e);
                    }finally{
                        try{
                            if (preparedStatement != null){
                                preparedStatement.close();
                            }
                        }catch(SQLException e){
                            throw new RuntimeException("本情報検索時のステートメントの解放に失敗しました", e);
                        }
                    }
                }
            }
            return trackList;
        }catch(SQLException e){
            throw new RuntimeException("track_tblのSELECTに失敗しました", e);
        }finally{
            try{
                if (preparedStatement != null){
                    preparedStatement.close();
                }
                if (resultSet != null){
                    resultSet.close();
                }
            }catch(SQLException e){
                throw new RuntimeException("本情報検索時のステートメントの解放に失敗しました", e);
            }
        }
    }


}