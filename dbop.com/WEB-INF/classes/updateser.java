import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.*;
import oracle.jdbc.pool.*;

@WebServlet("/update")

public class updateser extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
	{
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		int id=Integer.parseInt(req.getParameter("sid"));
		String name=req.getParameter("sname");
		String sbar=req.getParameter("branch");
		int ma1=Integer.parseInt(req.getParameter("m1"));
		int ma2=Integer.parseInt(req.getParameter("m2"));
		int ma3=Integer.parseInt(req.getParameter("m3"));
		
		try
		{
			OracleDataSource ds = new OracleDataSource();
			ds.setURL("jdbc:oracle:thin:@localhost:1521:orcl");
			ds.setUser("system");
			ds.setPassword("1234");
			Connection c=ds.getConnection();
			c.setAutoCommit();
			
			PreparedStatement ps=c.prepareStatement("update student set sname=?,branch=?,marks1=?,marks2=?,marks3=? where sid=?");
			
			ps.setString(1,name);
			ps.setString(2,sbar);
			ps.setInt(3,ma1);
			ps.setInt(4,ma2);
			ps.setInt(5,ma3);
			ps.setInt(6,id);
			
			int n = ps.executeUpdate();
			if(n > 0)
			{
				pw.println("<font color='black'>Student Updated Successfully</font>");
				RequestDispatcher rd=req.getRequestDispatcher("update.html");
				rd.include(req,res);
			}
			else
			{
				pw.println("Student Not Found");
				RequestDispatcher rd=req.getRequestDispatcher("update.html");
				rd.include(req,res);
			}
		}
		catch(Exception e)
		{
			pw.println(e.getMessage());
		}
	}
}