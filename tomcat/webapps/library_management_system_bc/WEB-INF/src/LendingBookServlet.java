package library_management_class;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.text.*;

@WebServlet("/LendingBook")
public class LendingBookServlet extends HttpServlet{
    static final String contextPath = "/library_management_system_bc";
    // public void doGet(HttpServletRequest request, HttpServletResponse response)
    // throws IOException, ServletException{
    //     LoginService loginService = new LoginService();
    //     if (!loginService.loginCheck(request, response)){
    //         return;
    //     }
    //     TrackService trackService = new TrackService();
    //     trackService.registerDueTrack(request, response);
    //     response.sendRedirect(contextPath+"/book_list");
    // }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        LoginService loginService = new LoginService();
        if (!loginService.loginCheck(request, response)){
            return;
        }
        TrackService trackService = new TrackService();
        try{
        trackService.registerDueTrack(request, response);
        }catch(ParseException e){
            // IDのint変換に失敗しました
            request.setAttribute("message", "IDのint変換に失敗しました");
        }
        request.setAttribute("message", "貸出し処理が完了しました");
        RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/lending_book.jsp");
        dispatch.forward(request, response);
    }
}