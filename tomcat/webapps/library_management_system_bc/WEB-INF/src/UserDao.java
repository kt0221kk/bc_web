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
            // SQLの定義
            //"INSERT INTO book_tbl (book_id, title, author, genre, status, publication_year, ISBN, publisher) VALUES (1, 'Book Title 1', 'Author 1', 'Genre 1', "AvailableForLoan", 2022, '123-4567890123', 'Publisher 1');"
            // INSERT INTO user_tbl (user_id, pass, first_name, last_name, address) VALUES (1, 'hashedpassword', 'First', 'Last', '123 Main St');
            String sql = "INSERT INTO user_tbl (pass, first_name, last_name, address) VALUES(?,?,?,?)";
            // SQLの作成(準備)
            preparedStatement = connection.prepareStatement(sql);
            // SQLバインド変数への値設定
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getAddress());
            // SQLの実行
            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException("user_tblのINSERTに失敗しました", e);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                    // System.out.println("ステートメントの解放に成功しました");
                }
            } catch (SQLException e) {
                throw new RuntimeException("ユーザ情報登録時のステートメントの解放に失敗しました", e);
            }
        }
    }

    public boolean update(User user) {
        PreparedStatement preparedStatement = null;
        try{
            String sql = "UPDATE user_tbl SET pass = ?, first_name = ?, last_name = ?, address = ? WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setInt(5, user.getUserId());
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

    public boolean delete(int userId) {
        PreparedStatement preparedStatement = null;
        try{
            String sql = "DELETE FROM user_tbl WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            int result = preparedStatement.executeUpdate();
            if (result == 1){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            throw new RuntimeException("user_tblのDELETEに失敗しました", e);
        }finally{
            try{
                if (preparedStatement != null){
                    preparedStatement.close();
                }
            }catch(SQLException e){
                throw new RuntimeException("ユーザ情報削除時のステートメントの解放に失敗しました", e);
            }
        }
    }

    public User find(int userId) {
        PreparedStatement preparedStatement = null;
        try{
            String sql = "SELECT * FROM user_tbl WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                String password = resultSet.getString("pass");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                User user = new User();
                user.setUserId(userId);
                user.setPassword(password);
                user.setFirstName(first_name);
                user.setLastName(last_name);
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
         // ステートメントの定義
         PreparedStatement preparedStatement = null;
         try {
             // SQLの定義
             String sql = "SELECT * FROM user_tbl";
             // SQLの作成(準備)
             preparedStatement = connection.prepareStatement(sql);
             // SQLの実行
             ResultSet resultSet = preparedStatement.executeQuery();
             // 問い合わせ結果の取得
             while (resultSet.next()) {
                 int user_id = resultSet.getInt("user_id");
                 String password = resultSet.getString("password");
                 String first_name = resultSet.getString("first_name");
                 String last_name = resultSet.getString("last_name");
                 String address = resultSet.getString("address");
                 
                 User user = new User();

                 user.setUserId(user_id);
                 user.setPassword(password);
                 user.setFirstName(first_name);
                 user.setLastName(last_name);
                 user.setAddress(address);
                 userList.add(user);
             }
             return userList;
         } catch (SQLException e) {
             throw new RuntimeException("EMPLOYEEテーブルのSELECTに失敗しました", e);
         } finally {
             try {
                 if (preparedStatement != null) {
                     preparedStatement.close();
                     System.out.println("ステートメントの解放に成功しました");
                 }
             } catch (SQLException e) {
                 throw new RuntimeException("ステートメントの解放に失敗しました", e);
             }
         }

        
    }
}
