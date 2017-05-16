package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {
	
	private Context context;
	private DataSource dataSource;
	
	public void init( ServletConfig config ) throws ServletException
	{
		context = null;
		dataSource = null;
		
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup( "java:comp/env/jdbc/OurCompany");		//"java:comp/env/jdbc/OurCompany"
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void destroy()
	{
		try {
			context.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException {
		doPost( request, response );
	}
	
	@SuppressWarnings("unchecked")
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException
	{
		String id = request.getParameter( "id" );
		String pass = request.getParameter( "pass" );
		String result = null;
		Connection conn = null;
		Statement stmt = null;
		boolean isSuccessed = false;
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		
		try {
			conn = dataSource.getConnection();
			if( conn == null )
				throw new ServletException( "Connection Error" );
			
			stmt = conn.createStatement();
			String query = String.format( "select userid "
					+ "from "
					+ "Account "
					+ "where "
					+ "userid like '%s' and password like PASSWORD( '%s' )", id, pass );
			
			ResultSet rs = stmt.executeQuery( query );
			
			if( rs.next() )
			{
				isSuccessed = true;
				session.setAttribute( "id", id );
			}
			else
				isSuccessed = false;
			
			JSONObject outer = new JSONObject();
			JSONObject inner = new JSONObject();
			
			inner.put( "isSuccessed", isSuccessed );
			outer.put( "loginResult", inner );
			result = outer.toJSONString();
			System.out.println( result );
			
			out.write( result );			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
