package library_management_class;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import library_management_class.*;
import java.sql.*;
import java.text.ParseException;

@WebServlet("/ReturnBook")
public class ReturnBookServlet extends HttpServlet {
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
            // ログイン確認
            LoginService loginService = new LoginService();
            if(!loginService.loginCheck(request, response)){
                return;
            }
            // 本情報取得
            TrackService trackService = new TrackService();
            int track_id = -1;
            try{
                track_id = trackService.registerReturnTrack(request, response);
            }catch(ParseException e){
                e.printStackTrace();
            }
            // リクエストにセット
            request.setAttribute("track_id", track_id);
            RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/return_book.jsp");
            dispatch.forward(request, response);
        
    }
}