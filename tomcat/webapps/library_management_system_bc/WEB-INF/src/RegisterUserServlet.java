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

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException{
            doGet(request, response);
        }
}