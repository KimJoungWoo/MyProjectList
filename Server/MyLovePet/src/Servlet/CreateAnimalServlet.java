package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
public class CreateAnimalServlet extends HttpServlet {
	
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
		if( context != null )
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
	
	@SuppressWarnings("unchecked")
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		boolean isSuccessed = false;
		
		request.setCharacterEncoding( "utf-8" );
		
		String strUserID = (String)session.getAttribute( "id" );
		String strAnimalIndex = request.getParameter( "AnimalIndex" );
		String strSerialNo = request.getParameter( "SerialNo" );
		String strName = request.getParameter( "Name" );
		String strGender = request.getParameter( "Gender" );
		String strBirth = request.getParameter( "Birth" );
		
		try {
			conn = dataSource.getConnection();
			String query = "insert "
					+ "into "
					+ "AnimalInfo ( userid, animalindex, serialno, name, gender, birth )"
					+ "values ( ?, ?, ?, ?, ?, ? )";
			
			pstmt = conn.prepareStatement( query );
			pstmt.setString( 1, strUserID );
			pstmt.setInt( 2,  Integer.parseInt( strAnimalIndex ) );
			pstmt.setInt( 3, Integer.parseInt( strSerialNo ) );
			pstmt.setString( 4, strName );
			pstmt.setString( 5, strGender );
			pstmt.setString( 6, strBirth );
			if( pstmt.executeUpdate() > 0 )
				isSuccessed = true;
			else
				isSuccessed = false;
			
			JSONObject inner = new JSONObject();
			JSONObject outer = new JSONObject();
			
			inner.put( "isSuccessed", isSuccessed );
			outer.put( "CreateAnimalReulst", inner );
			
			String str = outer.toJSONString();
			
			System.out.println( str );
			out.write( str );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if( conn != null )
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			if( pstmt != null )
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

}
