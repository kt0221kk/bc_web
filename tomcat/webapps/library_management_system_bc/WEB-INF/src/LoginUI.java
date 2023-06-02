package library_management_class;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginUI extends HttpServlet {
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException{

    response.setContentType("text/html; charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("<title>ログインページ</title>");
    out.println("</head>");
    out.println("<body>");

    out.println("<h1>ログイン画面</h1>");

    HttpSession session = request.getSession(true);

    /* 認証失敗から呼び出されたのかどうか */
    Object status = session.getAttribute("status");

    if (status != null){
      out.println("<p>認証に失敗しました</p>");
      out.println("<p>再度ユーザー名とパスワードを入力して下さい</p>");

      session.setAttribute("status", null);
    }

    out.println("<form method=\"POST\" action=\"/library_management_system_bc/LoginCheck\" name=\"loginform\">");
    out.println("<table>");
    out.println("<tr>");
    out.println("<td>ユーザー名</td>");
    out.println("<td><input type=\"text\" name=\"user\" size=\"32\"></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td>パスワード</td>");
    out.println("<td><input type=\"password\" name=\"pass\" size=\"32\"></td>");
    out.println("</tr>");
    out.println("<tr>");
    out.println("<td><input type=\"submit\" value=\"login\"></td>");
    out.println("<td><input type=\"reset\" value=\"reset\"></td>");
    out.println("</tr>");
    out.println("</table>");
    out.println("</form>");

    out.println("</body>");
    out.println("</html>");
  }
}