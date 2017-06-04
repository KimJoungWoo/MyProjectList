package Servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;

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
public class ConditionServlet extends HttpServlet {
	
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
	
	@SuppressWarnings("unchecked")
	public String makeJsonString( int serialNo, float avgTemp, float avgHeart, int step, String checkTime )
	{
		JSONObject out = new JSONObject();
		JSONObject in = new JSONObject();
		
		in.put( "serialno", serialNo );
		in.put( "avgtemp", avgTemp );
		in.put( "avgheart", avgHeart );
		in.put( "step", step );
		in.put( "checktime", checkTime );
		out.put( "Condition", in );
		
		return out.toJSONString();
	}
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	{
		doPost( request, response );
	}
	
	
	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException
	{
		request.setCharacterEncoding( "utf-8" );
		String serialNo = request.getParameter( "serialNo" );
		String query  = "select * from "
				+ "AnimalCondition "
				+ "where "
				+ "serialno = "
				+ serialNo
				+ " order by checktime desc "
				+ "limit 1";
		Connection conn = null;
		Statement stmt = null;
		PrintWriter writer = response.getWriter();
		String result;
		
		try {
			conn = dataSource.getConnection();
			
			if( conn == null )
			{
				System.out.println( "DBConnection error" );
				return ;
			}
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery( query );
			if( rs.next() )
			{

				GregorianCalendar date = new GregorianCalendar();
				date.setTime( rs.getTimestamp( "checktime" ) );
				String time = String.format( "%TF %TT", date, date );
				result = makeJsonString( rs.getInt( "serialno" ), rs.getFloat( "avgtemp" )
						, rs.getInt( "avgheart" ), rs.getInt( "step" ), time );
			}
			else
				result = makeJsonString( -1, -1, -1, -1, "-1" );
			
			writer.println( result );
		} 
		
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
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
