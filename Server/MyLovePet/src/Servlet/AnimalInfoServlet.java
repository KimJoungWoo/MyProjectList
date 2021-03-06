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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class AnimalInfoServlet extends HttpServlet {
	
	private Context context;
	private DataSource dataSource;
	
	public void init( ServletConfig config ) throws ServletException
	{
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
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		doPost( request, response );
	}
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		response.setContentType( "text/html; charset=UTF-8");
		Connection conn = null;
		Statement stmt = null;
		JSONObject outer = new JSONObject();
		PrintWriter out = response.getWriter();
		String id = (String)session.getAttribute( "id" );
		String query = String.format( "select * "
				+ "from "
				+ "AnimalInfo "
				+ "where "
				+ "userid like '%s'", id );

		
		String result = null;
		
		try {
			conn = dataSource.getConnection();
			if( conn == null )
				throw new ServletException( "Connection Error" );
			stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery( query );
			JSONArray arry = new JSONArray();
			
			while( rs.next() )
			{
				JSONObject obj = new JSONObject();
				obj.put( "iAnimalNo", rs.getInt( "AnimalNo" ) );
				obj.put( "iAnimalIndex", rs.getInt( "AnimalIndex" ) );
				obj.put( "iSerialNo", rs.getInt( "SerialNo" ) );
				obj.put( "strName", rs.getString( "Name" ) );
				obj.put( "strGender", rs.getString( "Gender" ) );
				obj.put( "strBirth", rs.getDate( "Birth" ) );
				obj.put( "strPhoto", null );
				
				arry.add( obj );
			}
			
			outer.put( "AnimalList", arry );
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
