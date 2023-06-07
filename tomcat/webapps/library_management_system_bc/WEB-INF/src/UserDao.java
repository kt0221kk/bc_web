package library_management_class;

import java.util.ArrayList;

import library_management_class.ConnectionManager;
import java.sql.*;
public class UserDao {
    private Connection connection;
    public UserDao(Connection connection) {
        super();
        this.connection = connection;
    }
    public int insert(User user) {
        // TODO: Implement this method
        PreparedStatement preparedStatement = null;
        try {
            String sql = "INSERT INTO user_tbl (password, user_name, address) VALUES(?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getAddress());
            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("user_tblのINSERTに失敗しました", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("ユーザ情報登録時のステートメントの解放に失敗しました", e);
            }
        }
    }

    public boolean update(User user) {
        PreparedStatement preparedStatement = null;
        try{
            String sql = "UPDATE user_tbl SET password = ?, user_name = ?, address = ? WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setInt(4, user.getUserId());
            int result = preparedStatement.executeUpdate();
            if (result == 1){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            throw new RuntimeException("user_tblのUPDATEに失敗しました", e);
        }finally{
            try{
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch(SQLException e){
                throw new RuntimeException("ユーザ情報更新時のステートメントの解放に失敗しました", e);
            }
        }
    }

    // Remainder of class as is, but update `find` and `findAll` methods to reflect schema changes
    public User find(int userId) {
        PreparedStatement preparedStatement = null;
        try{
            String sql = "SELECT * FROM user_tbl WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                String password = resultSet.getString("password");
                String user_name = resultSet.getString("user_name");
                String address = resultSet.getString("address");
                User user = new User();
                user.setUserId(userId);
                user.setPassword(password);
                user.setUserName(user_name);
                user.setAddress(address);
                return user;
            }else{
                return null;
            }
        }catch(SQLException e){
            throw new RuntimeException("user_tblのSELECTに失敗しました", e);
        }finally{
            try{
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch(SQLException e){
                throw new RuntimeException("ユーザ情報検索時のステートメントの解放に失敗しました", e);
            }
        }
    }

    public ArrayList<User> findAll() {
        ArrayList<User> userList = new ArrayList<User>();
        PreparedStatement preparedStatement = null;
        try {
            String sql = "SELECT * FROM user_tbl";
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int user_id = resultSet.getInt("user_id");
                String password = resultSet.getString("password");
                String user_name = resultSet.getString("user_name");
                String address = resultSet.getString("address");
                
                User user = new User();

                user.setUserId(user_id);
                user.setPassword(password);
                user.setUserName(user_name);
                user.setAddress(address);
                userList.add(user);
            }
            return userList;
        } catch (SQLException e) {
            throw new RuntimeException("user_tblのSELECTに失敗しました", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException("ステートメントの解放に失敗しました", e);
            }
        }
    }
    public ArrayList<User> selectByUserName(String userName){
        ArrayList<User> userList = new ArrayList<User>();
        PreparedStatement preparedStatement = null;
        try{
            String sql = "SELECT * FROM user_tbl WHERE user_name LIKE ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + userName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int user_id = resultSet.getInt("user_id");
                String password = resultSet.getString("password");
                String user_name = resultSet.getString("user_name");
                String address = resultSet.getString("address");
                User user = new User();
                user.setUserId(user_id);
                user.setPassword(password);
                user.setUserName(user_name);
                user.setAddress(address);
                userList.add(user);
            }
            return userList;
        }catch(SQLException e){
            throw new RuntimeException("user_tblのSELECTに失敗しました", e);
        }finally{
            try{
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch(SQLException e){
                throw new RuntimeException("ステートメントの解放に失敗しました", e);
            }
        }
    }
}
