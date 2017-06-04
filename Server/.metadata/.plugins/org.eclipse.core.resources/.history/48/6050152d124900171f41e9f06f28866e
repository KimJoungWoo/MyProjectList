package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class AddPetsiterServlet extends HttpServlet {
	
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
		Connection conn = null;
		PreparedStatement pstmt = null;
		String id;
		String title = request.getParameter( "Title" );
		String startDate = request.getParameter( "Date" );
		String endDate = request.getParameter( "Term" );
		String feedback = request.getParameter( "Feedback" );
		String petList = request.getParameter( "petList" );
		HttpSession session = request.getSession();
		id = (String)session.getAttribute( "id" );
		
		JSONParser parser = new JSONParser();
		JSONArray arry = null;
		
		try {
			arry = (JSONArray)parser.parse( petList );			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//String query = ""
		
	}

}
