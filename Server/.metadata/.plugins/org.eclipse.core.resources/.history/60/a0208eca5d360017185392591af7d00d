package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
import javax.sql.DataSource;

import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class JoinServlet extends HttpServlet {
	
	private Context context;
	private DataSource dataSource;
		
	public void init( ServletConfig config ) throws ServletException
	{
		context = null;
		dataSource = null;
		
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup( "java:comp/env/jdbc/OurCompany" );			
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
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException
	{
		request.setCharacterEncoding( "utf-8" );
		String id = request.getParameter( "id" );
		String passwd = request.getParameter( "passwd" );
		String name = request.getParameter( "name" );
		String subName = request.getParameter( "subName" );
		String email = request.getParameter( "email" );
		String zoneCode = request.getParameter( "zoneCode" );
		String city = request.getParameter( "city" );
		String streetAddr = request.getParameter( "streetAddr" );
		PrintWriter out = response.getWriter();
		
		Connection conn = null;
		Statement stmt = null;
		String query = String.format( "insert"
				+ " into"
				+ " Account"
				+ " values( '%s', PASSWORD( '%s' ), '%s', '%s', '%s', '%s', '%s', '%s' )"
				, id, passwd, name, subName, email, zoneCode, city, streetAddr);
		boolean isSuccessed = false;
		
		try {
			conn = dataSource.getConnection();
			if( conn == null )
				throw new ServletException( "Connection Null Exception" );
			
			stmt = conn.createStatement();
			if( stmt.executeUpdate( query ) > 0 )
				isSuccessed = true;
			else
				isSuccessed = false;
			
			JSONObject outer = new JSONObject();
			JSONObject inner = new JSONObject();
			
			inner.put( "isSuccessed", isSuccessed );
			outer.put( "JoinReport", inner );
			
			String result = outer.toJSONString();
			
			System.out.println( result );
			out.write( result );
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
