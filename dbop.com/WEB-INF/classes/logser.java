import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/logser")

public class logser extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String un=req.getParameter("uname");
		String pas=req.getParameter("pass");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			pw.println("<br>Driver Loaded Successfully");

			Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","1234");
			pw.println("<br>Connection Established");
			PreparedStatement ps =c.prepareStatement("SELECT * FROM myusers WHERE uname=? AND pass=?");
			ps.setString(1, un);
			ps.setString(2, pas);
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				RequestDispatcher rd =req.getRequestDispatcher("./home.html");
				rd.forward(req,res);
			}
			else
			{
				pw.println("<font color='red'>Invalid Credentials</font>");
				RequestDispatcher rd =req.getRequestDispatcher("./index.html");
				rd.include(req,res);
			}
		}
		catch(Exception e)
		{
			pw.println(e);
		}
			
		
		
	}
	
	public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		doPost(req,res);
	}
}