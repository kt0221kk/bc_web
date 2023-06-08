package library_management_class;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();
        RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/logout.jsp");
        dispatch.forward(request, response);
    }
}
