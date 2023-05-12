import java.sql.*;
public class Main {
  public static void main(String[] args) {
      Connection connection = null;
      try {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://db:5432/web_java";
        String user = "bc";
        String password = "2068686";
        connection = DriverManager.getConnection(url, user, password);
        System.out.println("登録成功");
        PreparedStatement statement;
        try{
          String sql = "SELECT * from users";
          statement = connection.prepareStatement(sql);
          ResultSet resultSet = statement.executeQuery();
          System.out.println(resultSet);
          while(resultSet.next()){
            int UserId = resultSet.getInt("id");
            String UserName = resultSet.getString("name");
            String UserPassword = resultSet.getString("password");
            System.out.println("id:"+UserId+"  name:"+UserName+"  password:"+UserPassword);
          }
        }catch(SQLException e){
          throw new RuntimeException("SQLの実行に成功しました");
        }
      } catch (ClassNotFoundException e) {
        throw new RuntimeException("JDBCドライバの登録に失敗しました",e);
        // TODO: handle exception
      } catch (SQLException e){
        throw new RuntimeException("データベースの接続に失敗しました");
      } finally {
        try{
          connection.close();
        } catch(SQLException e){
          throw new RuntimeException("データベースの接続解除に失敗しました");
        }
      }
  }
}