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
	
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException, ServletException
	{
		request.setCharacterEncoding( "utf-8" );
		String userID = request.getParameter( "id" );
		String query  = "select"
				+ " ac.userid, ac.subname, ac.name as accountname"
				+ ", info.name as animalname, pro.serialno"
				+ ", con.avgtemp, con.avgheart, con.step, con.checktime"
				+ " from"
				+ " Account ac, AnimalInfo info, Product pro, AnimalCondition con"
				+ " where"
				+ " ac.userid like '" + userID + "'"
				+ " and ac.userid = info.userid"
				+ " and pro.serialno = info.serialno"
				+ " and pro.serialno = con.serialno";
		Connection conn = null;
		Statement stmt = null;
		PrintWriter writer = response.getWriter();
		
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
				JSONObject out = new JSONObject();
				JSONObject in = new JSONObject();
				GregorianCalendar date = new GregorianCalendar();
				date.setTime( rs.getTime( "checktime" ) );
				String time = String.format( "%TF %TT", date, date );
				in.put( "usreid", rs.getString( "userid" ) );
				in.put( "subname", rs.getString( "subname" ) );
				in.put( "accountname", rs.getString( "accountname" ) );
				in.put( "animalname", rs.getString( "animalname" ) );
				in.put( "serialno", rs.getInt( "serialno" ) );
				in.put( "avgtemp", rs.getFloat( "avgtemp" ) );
				in.put( "avgheart", rs.getInt( "avgheart" ) );
				in.put( "step", rs.getInt( "step" ) );
				in.put( "checktime", time );
				out.put( "Condition", in );
				
				writer.println( out.toJSONString() );
			}
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
