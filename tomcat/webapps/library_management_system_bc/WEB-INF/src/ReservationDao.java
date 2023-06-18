package library_management_class;
import java.util.Date;
import library_management_class.Reservation;
import library_management_class.ConnectionManager;
import java.sql.*;


public class ReservationDao{
    private Connection connection;
    public ReservationDao(Connection connection){
        super();
        this.connection = connection;
    }
    public int insert(Reservation reservation){
        int result = 0;
        try{
            String sql = "INSERT INTO reservation_tbl(track_id, reservation_start_date, reservation_end_date, is_active) VALUES(?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, reservation.getTrackId());
            stmt.setDate(2, new java.sql.Date((reservation.getReservationStartDate()).getTime()));
            stmt.setDate(3, new java.sql.Date((reservation.getReservationEndDate()).getTime()));
            stmt.setBoolean(4, reservation.getIsActive());
            result = stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    public boolean updateIsActive(Reservation reservation){
        boolean result = false;
        try{
            String sql = "UPDATE reservation_tbl SET is_active = ? WHERE track_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setBoolean(1, reservation.getIsActive());
            stmt.setInt(2, reservation.getTrackId());
            int updateResult = stmt.executeUpdate();
            if(updateResult == 1){
                result = true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    public int updateIsActiveFalse(int trackId){
        int result = 0;
        try{
            String sql = "UPDATE due_tbl SET is_active = false WHERE track_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, trackId);
            result = stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    public Reservation selectByTrackId(int trackId){
        Reservation reservation = new Reservation();
        try{
            String sql = "SELECT * FROM reservation_tbl WHERE track_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, trackId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                reservation.setTrackId(rs.getInt("track_id"));
                reservation.setReservationStartDate(rs.getDate("reservation_start_date"));
                reservation.setReservationEndDate(rs.getDate("reservation_end_date"));
                reservation.setIsActive(rs.getBoolean("is_active"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return reservation;
    }

}