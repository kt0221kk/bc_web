package library_management_class;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import library_management_class.ConnectionManager;
import library_management_class.UserDao;
import java.sql.*;
import java.util.ArrayList;

@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        HttpSession session = request.getSession(true);

        boolean check = authUser(user, pass, session);
        if (check) {
            /* 認証済みにセット */
            session.setAttribute("login", "OK");
            session.setAttribute("user", user);
            

            /* 本来のアクセス先へ飛ばす */
            String target = (String) session.getAttribute("target");
            
            /* ログインページへのリダイレクトをチェック */
            if (target != null && target.equals("/library_management_system_bc/login.jsp")) {
                response.sendRedirect("/library_management_system_bc/");
            } else if (target == null) {
                response.sendRedirect("/library_management_system_bc/");
            } else {
                response.sendRedirect(target);
            }
        } else {
            /* 認証に失敗したら、ログイン画面に戻 */
            session.setAttribute("status", "Not Auth");
            response.sendRedirect("/library_management_system_bc/login.jsp");
        }
    }


  protected boolean authUser(String user, String pass,HttpSession session){
    ConnectionManager connectionManager = new ConnectionManager();
    Connection connection = connectionManager.getConnection();
    UserDao userDao = new UserDao(connection);
    ArrayList<User> userList = userDao.selectByUserName(user);
    LoginService loginService = new LoginService();
    String hashPassword = loginService.hashPassword(pass);
    for (User u : userList){
        if (u.getPassword().equals(hashPassword)){
            session.setAttribute("user_id", u.getUserId());
            return true;
        }
    }

    return false;
  }
}