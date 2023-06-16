package library_management_class;

import java.util.ArrayList;
import library_management_class.Due;
import library_management_class.ConnectionManager;
import java.sql.*;


public class DueDao{
    private Connection connection;
    public DueDao(Connection connection){
        super();
        this.connection = connection;
    }

    public ArrayList<Due> selectAll(){
        ArrayList<Due> dueList = new ArrayList<Due>();
        try{
            String sql = "SELECT * FROM due_tbl";
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Due due = new Due();
                due.setBorrowDate(rs.getDate("borrow_date"));
                due.setReturnDueDate(rs.getDate("return_due_date"));
                dueList.add(due);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return dueList;
    }

    public int insert(Due due){
        int result = 0;
        try{
            String sql = "INSERT INTO due_tbl (track_id,borrow_date, return_due_date) VALUES (?. ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, due.getTrackId());
            stmt.setDate(2, new java.sql.Date((due.getBorrowDate()).getTime()));
            stmt.setDate(3, new java.sql.Date((due.getReturnDueDate()).getTime()));
            result = stmt.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public Due selectByTrackId(int trackId){
        Due due = new Due();
        try{
            String sql = "SELECT * FROM due_tbl WHERE track_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, trackId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                due.setBorrowDate(rs.getDate("borrow_date"));
                due.setReturnDueDate(rs.getDate("return_due_date"));
            }
            due.setTrackId(trackId);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return due;
    }
}