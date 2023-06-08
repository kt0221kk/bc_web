package library_management_class;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebFilter(filterName="LoginFilter",urlPatterns={"/AccessLibraryData"})
public class LoginFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
                request.setCharacterEncoding("UTF-8");

                HttpServletRequest request_cast_to_http = (HttpServletRequest) request;
                HttpServletResponse response_cast_to_http = (HttpServletResponse) response;
                HttpSession session = request_cast_to_http.getSession(false);
                String target = request_cast_to_http.getRequestURI();

                 if (session == null){
                    /* まだ認証されていない */
                    session = request_cast_to_http.getSession(true);
                    session.setAttribute("target", target);
                    response_cast_to_http.sendRedirect("/library_management_system_bc/login.jsp");
                    
                }else{
                    Object loginCheck = session.getAttribute("login");
                    if (loginCheck == null){
                        /* まだ認証されていない */
                        session.setAttribute("target", target);
                        response_cast_to_http.sendRedirect("/library_management_system_bc/login.jsp");
                        
                    }
                }
                
                // chain.doFilter(request, response);

    }

    public void destroy() {
    }
}