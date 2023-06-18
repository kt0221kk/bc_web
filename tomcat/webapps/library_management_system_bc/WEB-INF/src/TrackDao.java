package library_management_class;
import java.util.ArrayList;
import library_management_class.Track;
import java.sql.*;
import java.util.Date;

import java.sql.Timestamp;
import java.time.LocalDateTime; 
import java.time.ZonedDateTime;
import java.time.ZoneId;


public class TrackDao{
    private Connection connection;
    public TrackDao(Connection connection){
        super();
        this.connection = connection;
    }
    public int insert(Track track){
        PreparedStatement stmt = null;
        int track_id = -1;
        try{
            String sql = "INSERT INTO track_tbl (book_id, user_id, track_status, track_time) VALUES (?, ?, ?, ?)";
            stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, track.getBookId());
            stmt.setInt(2, track.getUserId());
            stmt.setString(3, track.getTrackStatus());
            ZonedDateTime nowInJapan = ZonedDateTime.now(ZoneId.of("Asia/Tokyo"));
            // ZonedDateTimeをLocalDateTimeに変換
            LocalDateTime localDateTimeInJapan = nowInJapan.toLocalDateTime();
            // LocalDateTimeをTimestampに変換
            Timestamp timestampInJapan = Timestamp.valueOf(localDateTimeInJapan);
            stmt.setTimestamp(4, timestampInJapan);
            // result = stmt.executeUpdate();
            // System.out.println("INSERT文: " + stmt.toString());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                track_id = rs.getInt(1);
                System.out.println("getGeneratedKeys: " + track_id);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
            } catch(SQLException e){
                throw new RuntimeException("ステートメントの解放に失敗しました。", e);
            }
        }
        return track_id;
    }

    public Track selectByTrackId(int trackId){
        PreparedStatement stmt = null;
        Track track = new Track();
        try{
            String sql = "SELECT * FROM track_tbl WHERE track_id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, trackId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                track.setTrackId(rs.getInt("track_id"));
                track.setBookId(rs.getInt("book_id"));
                track.setUserId(rs.getInt("user_id"));
                track.setTrackStatus(rs.getString("track_status"));
                track.setTrackTime(rs.getDate("track_time"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
            } catch(SQLException e){
                throw new RuntimeException("ステートメントの解放に失敗しました。", e);
            }
        }
        return track;
    }
    
    public ArrayList<Track> selectAll(){
        PreparedStatement stmt = null;
        ArrayList<Track> trackList = new ArrayList<Track>();
        try{
            String sql = "SELECT * FROM track_tbl";
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Track track = new Track();
                track.setTrackId(rs.getInt("track_id"));
                track.setBookId(rs.getInt("book_id"));
                track.setUserId(rs.getInt("user_id"));
                track.setTrackStatus(rs.getString("track_status"));
                track.setTrackTime(rs.getDate("track_time"));
                trackList.add(track);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
            } catch(SQLException e){
                throw new RuntimeException("ステートメントの解放に失敗しました。", e);
            }
        }
        return trackList;
    }
    public ArrayList<Track> selectByBookId(int bookId){
        PreparedStatement stmt = null;
        ArrayList<Track> trackList = new ArrayList<Track>();
        try{
            String sql = "SELECT * FROM track_tbl WHERE book_id = ?";
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Track track = new Track();
                track.setTrackId(rs.getInt("track_id"));
                track.setBookId(rs.getInt("book_id"));
                track.setUserId(rs.getInt("user_id"));
                track.setTrackStatus(rs.getString("track_status"));
                track.setTrackTime(rs.getDate("track_time"));
                trackList.add(track);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt != null){
                    stmt.close();
                }
            } catch(SQLException e){
                throw new RuntimeException("ステートメントの解放に失敗しました。", e);
            }
        }
        return trackList;
    }

}