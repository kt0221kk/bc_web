package library_management_class;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import library_management_class.User;
import library_management_class.UserDao;
import library_management_class.ConnectionManager;

@WebServlet("/RegisterUser")
public class RegisterUser extends HttpServlet {
    public RegisterUser(){
        super();
    }   

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
            response.setContentType("text/html; charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            LoginService loginService = new LoginService();
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getConnection();
            UserDao userDao = new UserDao(connection);
            User user = new User();
            String password = request.getParameter("password");
            String hashedPassword = loginService.hashPassword(password);
            user.setPassword(hashedPassword);
            user.setUserName(request.getParameter("name"));
            int result = userDao.insert(user);
            connectionManager.commit();
            connectionManager.closeConnection();
            response.sendRedirect("/library_management_system_bc/");



        }
}