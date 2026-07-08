import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import oracle.jdbc.pool.*;

@WebServlet("/del")

public class delet extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws 	IOException,ServletException
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
			
			PreparedStatement ps=c.prepareStatement("delete from student where sid=?");
			ps.setInt(1,num);
			int n=ps.executeUpdate();
			if(n>0)
			{
				pw.println("deletion of "+num+" is  succsess full");
				RequestDispatcher rd=req.getRequestDispatcher("delet.html");
				rd.include(req,res);
			}
			else
			{
				pw.println("deletion failed");
				RequestDispatcher rd=req.getRequestDispatcher("delet.html");
				rd.include(req,res);
			}
		}
		catch(Exception e)
		{
			pw.println(e.getMessage());
		}
	}
}