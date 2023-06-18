package library_management_class;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import library_management_class.*;
import java.util.*;
import java.text.SimpleDateFormat;

@WebServlet("/DetailTrack")
public class DetailTrackServlet extends HttpServlet {
    public DetailTrackServlet(){
        super();
    }   

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
            response.setContentType("text/html; charset=UTF-8");
            request.setCharacterEncoding("UTF-8");
            HttpSession session = request.getSession(false);
            String target = request.getRequestURI();
            // if (session == null){
            //     /* まだ認証されていない */
            //     session = request.getSession(true);
            //     session.setAttribute("target", target);
            //     response.sendRedirect("/library_management_system_bc/login.jsp");
            //     return;
            // }else{
            //     Object loginCheck = session.getAttribute("login");
            //     if (loginCheck == null){
            //         /* まだ認証されていない */
            //         session.setAttribute("target", target);
            //         response.sendRedirect("/library_management_system_bc/login.jsp");
            //         return;
            //     }
            // }
            LoginService loginService = new LoginService();
            loginService.loginCheck(request, response);
            ConnectionManager connectionManager = new ConnectionManager();
            Connection connection = connectionManager.getConnection();
            TrackService trackService = new TrackService();
            int trackId = Integer.parseInt(request.getParameter("track_id"));
            Track my_track = trackService.getTrackByTrackId(trackId);
            request.setAttribute("track", my_track);
            UserDao userDao = new UserDao(connection);
            User user = userDao.selectByUserId(my_track.getUserId());
            request.setAttribute("user", user);
            
            BookDao bookDao = new BookDao(connection);
            int bookId = Integer.parseInt(request.getParameter("book_id"));

            Book book = bookDao.selectById(bookId);
            request.setAttribute("book", book);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/detail_track.jsp");
            dispatcher.forward(request, response);
    }
}