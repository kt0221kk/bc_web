package library_management_class;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;
import java.text.*;

@WebServlet("/CancelReservation")
public class CancelReservationServlet extends HttpServlet{
    static final String contextPath = "/library_management_system_bc";
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{
        response.setContentType("text/html; charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        LoginService loginService = new LoginService();
        if (!loginService.loginCheck(request, response)){
            return;
        }
        TrackService trackService = new TrackService();
        trackService.cancelReservation(request, response);
        RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/cancel_reservation.jsp");
        dispatch.forward(request, response);
    }
}