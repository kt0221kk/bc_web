import java.sql.*;
public class Main {
  public static void main(String[] args) {
      try {
        Class.forName("org.postgresql.Driver");
        // String url = "jdbc:postgresql://localhost:5432/jdbc";
        // String user = "bc";
        // String password = "2068686";
        System.out.println("登録成功");

      } catch (ClassNotFoundException e) {
        throw new RuntimeException("JDBCドライバの登録に失敗しました",e);
        // TODO: handle exception
      }
  }
}