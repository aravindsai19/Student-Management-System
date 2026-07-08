import java.util.Scanner;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.sql.*;
import oracle.jdbc.pool.*;


@WebServlet("/inser")

public class insert extends HttpServlet
{
	public void doPost(HttpServletRequest req,HttpServletResponse res)throws  IOException,ServletException
	{
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		String sna=req.getParameter("t2");
		String sbra=req.getParameter("t3");
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
			c.setAutoCommit(true);
			pw.println("connection established");
			
			PreparedStatement ps=c.prepareStatement("insert into student values(student_seq.NEXTVAL,?,?,?,?,?)");
			ps.setString(1,sna);
			ps.setString(2,sbra);
			ps.setInt(3,ma1);
			ps.setInt(4,ma2);
			ps.setInt(5,ma3);
			int n = ps.executeUpdate();
			if(n > 0)
			{
				pw.println("Student Inserted Successfully");
				RequestDispatcher rd=req.getRequestDispatcher("./insert.html");
				rd.include(req,res);
			}
			else
			{
				pw.println("Insertion Failed");
				RequestDispatcher rd=req.getRequestDispatcher("./insert.html");
				rd.include(req,res);
			}
		}
		catch(Exception e)
		{
			pw.println(e.getMessage());
		}
	}
}