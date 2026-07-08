import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/up")
public class upser extends HttpServlet
{
    public void doGet(HttpServletRequest req,
                      HttpServletResponse res)
            throws IOException, ServletException
    {
        res.setContentType("text/html");
        PrintWriter pw = res.getWriter();

        ServletContext sc = getServletContext();

        String name = (String) sc.getAttribute("un");

        pw.println("<html>");
        pw.println("<body style='margin:0;padding:0;"
                +"background:linear-gradient(to right,#7B0000,#B22222,#FFD700);"
                +"font-family:Arial;'>");

        pw.println("<h2 style='color:white;padding:20px;'>");
        pw.println("👤 Logged in : <font color='gold'>"+name+"</font>");
        pw.println("</h2>");

        pw.println("</body>");
        pw.println("</html>");
    }
}