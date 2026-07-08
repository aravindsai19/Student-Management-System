import java.io.*;
import java.sql.*;
import oracle.jdbc.pool.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/ser")

public class search extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		int num=Integer.parseInt(req.getParameter("t1"));
		
		try
		{
			OracleDataSource ds = new OracleDataSource();
			ds.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
			ds.setUser("system");
			ds.setPassword("1234");
			Connection c=ds.getConnection();
			c.setAutoCommit(true);
			pw.println("connection established");
			
			PreparedStatement ps=c.prepareStatement("select * from student where sid=?");
			ps.setInt(1,num);
			ResultSet rs=ps.executeQuery();
				
			RequestDispatcher rd = req.getRequestDispatcher("search.html");
			rd.include(req, res);
			
			if(rs.next())
			{
			pw.println("<body>");
			pw.println("<center>");
			pw.println("<font size='5' color='darkred'><b>Student Details</b></font><br><br>");
			pw.println("<table border='1' cellpadding='10' cellspacing='0' style='font-size:22px;'>");
			pw.println("<tr>");
				pw.println("<td><b>Student ID :</b></td>");
				pw.println("<td>"+rs.getInt(1)+"</td>");
			pw.println("</tr>");
			pw.println("<tr>");
				pw.println("<td><b>Student Name :</b></td>");
				pw.println("<td>"+rs.getString(2)+"</td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
				pw.println("<td><b>Student Branch :</b></td>");
				pw.println("<td>"+rs.getString(3)+"</td>");
			pw.println("</tr>");
			
			pw.println("<tr>");
				pw.println("<td><b>Student 1st Subject Marks :</b></td>");
				pw.println("<td>"+rs.getInt(4)+"</td>");
			pw.println("</tr>");
				
			pw.println("<tr>");
				pw.println("<td><b>Student 2nd Subject Marks :</b></td>");
				pw.println("<td>"+rs.getInt(5)+"</td>");
			pw.println("</tr>");
				
			pw.println("<tr>");
				pw.println("<td><b>Student 3rd Subject Marks :</b></td>");
				pw.println("<td>"+rs.getInt(6)+"</td>");
			pw.println("</tr>");
			pw.println("</table>");
			pw.println("</center>");
			pw.println("</body>");
		}
		else
		{
			pw.println("student deatils not found ");
		}
		}
		catch(Exception e)
		{
			pw.println("the exception rised is "+e.getMessage());
		}
	}
}