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