package library_management_class;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/RegisterBookUI")
public class RegisterBookServlet extends HttpServlet {
    public RegisterBookServlet(){
        super();
    }   

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
            HttpSession session = request.getSession(false);
            String target = request.getRequestURI();

            if (session == null || session.getAttribute("login") == null) {
                /* まだ認証されていない */
                session = request.getSession(true);
                session.setAttribute("target", target);
                response.sendRedirect("/library_management_system_bc/login.jsp");
            } else {
                RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/register_book.jsp");
                dispatch.forward(request, response);
            }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
            doGet(request, response);
        }
}
