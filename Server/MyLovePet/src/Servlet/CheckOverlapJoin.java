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
import javax.sql.DataSource;

import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class CheckOverlapJoin extends HttpServlet {

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
		Connection conn = null;
		Statement stmt = null;
		String type = request.getParameter( "type" );
		String id = request.getParameter( "id" );
		PrintWriter out = response.getWriter();
		
		try {
			conn = dataSource.getConnection();
			if( conn == null )
				throw new SQLException( "Connection Exception" );
			
			stmt = conn.createStatement();
			
			String query = null;
			
			if( type.equals( "idCheck" ) )
				query = "select userid from Account where userid like '" + id + "'";
			else if( type.equals( "subNameCheck" ) )
				query = "select subname from Account where subname like '" + id + "'";
			
			boolean isAble = false;
			
			ResultSet rs = stmt.executeQuery( query );
			if( rs.next() )
				isAble = false;
			else
				isAble = true;
			
			JSONObject outer = new JSONObject();
			JSONObject inner = new JSONObject();
			inner.put( "isAble", isAble );
			outer.put( type, inner );
			
			String result = outer.toJSONString();		
			
			System.out.println( result );
			out.print( result );
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
