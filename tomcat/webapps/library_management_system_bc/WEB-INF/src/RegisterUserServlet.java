package library_management_class;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/RegisterUserUI")
public class RegisterUserServlet extends HttpServlet {
    public RegisterUserServlet(){
        super();
    }   

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {
            HttpSession session = request.getSession(false);
            String target = request.getRequestURI();
            if (session == null){
                /* まだ認証されていない */
                session = request.getSession(true);
                session.setAttribute("target", target);
                response.sendRedirect("/library_management_system_bc/login");
                return;
            }else{
                Object loginCheck = session.getAttribute("login");
                if (loginCheck == null){
                    /* まだ認証されていない */
                    session.setAttribute("target", target);
                    response.sendRedirect("/library_management_system_bc/login");
                    return;
                }
            }
            RequestDispatcher dispatch = request.getRequestDispatcher("WEB-INF/jsp/register_user.jsp");
            dispatch.forward(request, response);


    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
            doGet(request, response);
        }
}