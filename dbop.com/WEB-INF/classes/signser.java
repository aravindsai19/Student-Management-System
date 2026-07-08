import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet("/signser")

public class signser extends HttpServlet
{	
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();

		String name = req.getParameter("uname");
		Long con=Long.parseLong(req.getParameter("num"));
		int age=Integer.parseInt(req.getParameter("uage"));
		String gen=req.getParameter("ugender");
		String email=req.getParameter("umail");
		String usn=req.getParameter("use");
		String pas1=req.getParameter("pass");
		String pas2=req.getParameter("pass1");

		Cookies c=new Cooikes("un",name);
		req.addCookie(c);

		if(!pas1.equals(pas2))
		{
			pw.println("passwords dosent match");
			RequestDispatcher rd1=req.getRequestDispatcher("signup.html");
			rd1.include(req,res);
		}
		else
		{
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			pw.println("<br>Driver Loaded Successfully");

			Connection c=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","1234");
			pw.println("<br>Connection Established");
			c.setAutoCommit(true);
			PreparedStatement ps =c.prepareStatement("INSERT INTO myusers VALUES (myusers_seq.NEXTVAL,?,?,?,?,?,?,?)");
			ps.setString(1,name);
			ps.setLong(2,con);
			ps.setInt(3,age);
			ps.setString(4,gen);
			ps.setString(5,email);
			ps.setString(6,usn);
			ps.setString(7,pas1);
			int n = ps.executeUpdate();

			if(n > 0)
			{
			pw.println("<h2>regestration successfull go to login</h2>");
    			RequestDispatcher rd =req.getRequestDispatcher("index.html");
    			rd.forward(req,res);
			}
			else
			{
    			pw.println("Registration Failed");
			}	
			ps.close();
			c.close();			
			
			
		}
		catch(Exception e)
		{
			pw.println(e);
		}
			
		}
		
	}
}