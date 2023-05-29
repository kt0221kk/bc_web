import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
@WebServlet("/HelloWorld")//[1]
public class HelloWorld extends HttpServlet {//[]2]
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {//[3]
        response.setContentType("text/html");//[4]
        PrintWriter out = response.getWriter();//[5]
        out.println("<html><head></head><body>");//[6]
        out.println("<p>Simple Application</p>");//[7]
        out.println("<p>Hello World!</p>");//[8]
        out.println("</body></html>");//[9]
    }
}