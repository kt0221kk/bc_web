package library_management_class;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/UserFind")
public class UserFindServlet extends HttpServlet {
    public UserFindServlet(){
        super();
    }   

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
            LoginService loginService = new LoginService();
            if (!loginService.loginCheck(request, response)){
                return;
            }
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getConnection();
            UserDao userDao = new UserDao(connection);
            ArrayList<User> userList = userDao.selectAll();
            request.setAttribute("userList", userList);
            RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/user_find.jsp");
            dispatch.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
            doGet(request, response);
        }
}