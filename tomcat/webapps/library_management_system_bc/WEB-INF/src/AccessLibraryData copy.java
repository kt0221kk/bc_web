package library_management_class;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import library_management_class.Book;
public class AccessLibraryData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AccessLibraryData() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
            HttpSession session = request.getSession(false);
            String target = request.getRequestURI();
            if (session == null){
                /* まだ認証されていない */
                session = request.getSession(true);
                session.setAttribute("target", target);
                response.sendRedirect("/library_management_system_bc/login.jsp");
                return;
            }else{
                Object loginCheck = session.getAttribute("login");
                if (loginCheck == null){
                    /* まだ認証されていない */
                    session.setAttribute("target", target);
                    response.sendRedirect("/library_management_system_bc/login.jsp");
                    return;
                }
            }
            // Setting the book name
            Connection connection = null;
            String book_name = "Wonder Alice";
            Book book = new Book();
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
                    String sql = "SELECT * from user_tbl";
                    statement = connection.prepareStatement(sql);
                    ResultSet resultSet = statement.executeQuery();
                    // System.out.println(resultSet);
                    while(resultSet.next()){
                    int UserId = resultSet.getInt("user_id");
                    book_name = resultSet.getString("first_name");
                    book.setTitle(book_name);
                    String UserPassword = resultSet.getString("pass");
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
            request.setAttribute("book_name", book.getTitle());
            // Forwarding the request to "plot_book_data.jsp"
            RequestDispatcher dispatch = request.getRequestDispatcher("plot_book_data.jsp");
            dispatch.forward(request, response);
        }
}