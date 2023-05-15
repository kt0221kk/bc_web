import java.io.IOException;
import java.servlet.*;
@WebServlet("/hello")
public void doGet(HttpServletRequest req, HttpServletResponse resp)
    throws ServletException, IOException{
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head></head>");
        out.println("<body>");
        out.println("<h1>Hello Servlet!</h1>");
        out.println("</body>");
        out.println("</html>");
    }