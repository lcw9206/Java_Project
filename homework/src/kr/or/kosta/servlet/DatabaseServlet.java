package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DatabaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "hr";
	String password = "a1234";
	String sql = "SELECT E.employee_id     id, \r\n" + 
			 "       E.department_id   eName, \r\n" + 
			 "       E.salary          salary, \r\n" + 
			 "       TO_CHAR(E.hire_date ,'yyyy-mm-dd hh24:mi')  hireDate, \r\n" + 
			 "       D.department_name dName \r\n" + 
			 "FROM   employees E \r\n" + 
			 "       join departments D \r\n" + 
			 "         ON E.department_id = D.department_id "; // 안에 ; 는 쓰지 않는다.

	Connection conn = null;
	@Override //사전 설정시켜줄 요소들
	public void init() throws ServletException {
		try {
			Class.forName(driver);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 동적 묵시적 생성
		
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//웹클라이언트로 (동적)출력 하고자 하는 데이터 생성
				//응답메세지의 해더에 컨텐츠 유형 설정
				
		
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				conn = DriverManager.getConnection(url, userName, password);
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();

				out.println("<html>");
				out.println("<head>");
				out.println("<title>Servlet Programming</title>");
				out.println("<meta charset=\"utf-8\">");
				out.println("<body style='font-size:20pt;'>");
				out.print("<table border='1' width='50%'>");
				try {
					while(rs.next()) {
						String id = rs.getString("id");
						String eName = rs.getString("eName");
						int salary = rs.getInt("salary");
						String hireDate = rs.getString("hireDate");
						String dName = rs.getString("dName");
					out.print("<tr>");
					out.print("<td>"+id+"</td><td>"+eName+"</td><td>"+salary+"</td><td>"+hireDate+"</td><td>"+dName+"</td>");
					out.print("</tr>");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				out.print("</table>");
				out.println("<body>");		
				out.println("</head>");
				out.println("</html>");
	}
	
	@Override
	public void destroy() {
		if(conn!=null)
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
