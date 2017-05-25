package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

@SuppressWarnings("serial")
public class SerialCheckServlet extends HttpServlet {

	private Context context;
	private DataSource dataSource;

	public void init(ServletConfig config) throws ServletException {
		context = null;
		dataSource = null;

		try {
			context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/OurCompany"); // "java:comp/env/jdbc/OurCompany"
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void destroy() {
		try {
			context.close();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String serialNo = request.getParameter("serialNo");
		boolean isMattched = false;
		PrintWriter out = response.getWriter();

		try {
			conn = dataSource.getConnection();
			if( conn == null )
				throw new ServletException( "connection error" );

			String query = "select * from Product where (select serialno from AnimalInfo where serialno = ?) is null and serialno = ?";
			pstmt = conn.prepareStatement( query );
			pstmt.setString( 1, serialNo );
			pstmt.setString( 2, serialNo );
			ResultSet rs = pstmt.executeQuery();
			
			if( rs.next() )
				isMattched = true;
			else
				isMattched = false;
			
			JSONObject inner = new JSONObject();
			JSONObject outer = new JSONObject();
			inner.put( "isMattched", isMattched );
			outer.put( "CheckResult", inner );
			
			String str = outer.toJSONString();
			
			System.out.println( str );
			out.write( str );
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
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
	}

}
