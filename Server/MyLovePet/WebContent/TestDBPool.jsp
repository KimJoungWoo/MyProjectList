<%@page contentType="text/html; charset=euc-kr" errorPage="DBError.jsp" %>
<%@page import="java.sql.*"%>
<%@page import="javax.sql.*"%>
<%@page import="javax.naming.*"%>
<HTML>
    <HEAD><TITLE>�����ͺ��̽� Ŀ�ؼ� Ǯ �׽�Ʈ</TITLE></HEAD>
    <BODY>
    <H3>�����ͺ��̽� Ŀ�ؼ� Ǯ �׽�Ʈ</H3>
    <%
        Context context = new InitialContext();
        DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/OurCompany");
        Connection conn = ds.getConnection(); 
        if (conn != null) {
            out.println("���� ��� �Ϸ�<BR>");
            conn.close();
            out.println("���� ��ȯ �Ϸ�<BR>");
        }
        else {
            out.println("���� ��� ����<BR>");
        }
    %>
    </BODY>
</HTML>
