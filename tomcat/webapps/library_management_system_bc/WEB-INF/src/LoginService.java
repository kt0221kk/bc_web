package library_management_class;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class LoginService{
    static final String contextPath = "/library_management_system_bc";
    public boolean authUser(String user, String pass,HttpSession session){
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        UserDao userDao = new UserDao(connection);
        ArrayList<User> userList = userDao.selectByUserName(user);
        for (User u : userList){
            if (u.getPassword().equals(pass)){
                session.setAttribute("user_id", u.getUserId());
                session.setAttribute("user_name", u.getUserName());
                return true;
            }
        }
        return false;
    }
    public boolean loginCheck(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
        HttpSession session = request.getSession(false);
        String target = request.getRequestURI();
        if (session == null){
            /* まだ認証されていない */
            session = request.getSession(true);
            session.setAttribute("target", target);
            response.sendRedirect(contextPath+"/login");
            return false;
        }else{
            /* 認証済み */
            String login = (String) session.getAttribute("login");
            if (login == null || !login.equals("OK")){
                /* 認証済みでない */
                session.setAttribute("target", target);
                response.sendRedirect(contextPath+"/login");
                return  false;
            }
        }
        return true;
    }
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    public String hashPassword(String password){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(encodedhash);
        }catch(NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
    }

}