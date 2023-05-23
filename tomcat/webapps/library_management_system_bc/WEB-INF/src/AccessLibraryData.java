package library_management_class;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
public class AccessLibraryData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AccessLibraryData() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Setting the book name
        Connection connection = null;
        String book_name = "Wonder Alice";

        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://db:5432/web_java";
            String user = "bc";
            String password = "2068686";
            connection = DriverManager.getConnection(url, user, password);
            // System.out.println("登録成功");
            book_name = "登録成功";
            PreparedStatement statement;
            try{
                String sql = "SELECT * from users";
                statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery();
                // System.out.println(resultSet);
                while(resultSet.next()){
                int UserId = resultSet.getInt("id");
                book_name = resultSet.getString("name");
                String UserPassword = resultSet.getString("password");
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
        request.setAttribute("book_name", book_name);
        // Forwarding the request to "plot_book_data.jsp"
        RequestDispatcher dispatch = request.getRequestDispatcher("plot_book_data.jsp");
        dispatch.forward(request, response);
    }
}