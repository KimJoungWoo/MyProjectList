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
import javax.sql.DataSource;

import org.json.simple.JSONObject;

public class AddConditionServlet extends HttpServlet {
	
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
		int serialNo = Integer.parseInt( request.getParameter( "serialNo" ) );
		float avgTemp = Float.parseFloat( request.getParameter( "avgTemp" ) );
		float avgHeart = Float.parseFloat( request.getParameter( "avgHeart" ) );
		int step = Integer.parseInt( request.getParameter( "step" ) );
		PrintWriter out = response.getWriter();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String query = "insert "
				+ "into "
				+ "AnimalCondition( serialno, avgtemp, avgheart, step ) "
				+ "values( ?, ?, ?, ? )";
		boolean isSuccessed = false;
		
		try {
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement( query );
			
			pstmt.setInt( 1, serialNo );
			pstmt.setFloat( 2, avgTemp );
			pstmt.setFloat( 3, avgHeart );
			pstmt.setInt( 4, step );
			if( pstmt.executeUpdate() > 0 )
				isSuccessed = true;
			else
				isSuccessed = false;
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
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		JSONObject outer = new JSONObject();
		JSONObject inner = new JSONObject();
		inner.put( "isSuccessed", isSuccessed );
		outer.put( "Result", inner );
		
		out.write( outer.toJSONString() );

	}

}
