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
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbcp2.BasicDataSource;

import kr.or.kosta.servlet.dao.JdbcUserDao;
import kr.or.kosta.servlet.dao.User;


public class DatabaseServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USERNAME = "hr";
	private static final String PASSWORD = "a1234";
	JdbcUserDao dao;
	@Override //사전 설정시켜줄 요소들
	public void init() throws ServletException {
		dao = new JdbcUserDao();
		BasicDataSource dataSource = new BasicDataSource(); //dataSource는 sun의 규격 basicDAtaSource는 구현된 구현체 
		//DataSource dsource =  (BasicDataSource)new BasicDataSource();
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(URL);
		dataSource.setUsername(USERNAME);
		dataSource.setPassword(PASSWORD);
		dataSource.setInitialSize(5);
		dataSource.setMaxTotal(10);
		dataSource.setMaxIdle(7);
		dao.setDataSource(dataSource);
	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> list =null;
		try {
			list = dao.listAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//웹클라이언트로 (동적)출력 하고자 하는 데이터 생성
				//응답메세지의 해더에 컨텐츠 유형 설정
				
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();

				out.println("<html>");
				out.println("<head>");
				out.println("<title>Servlet Programming</title>");
				out.println("<meta charset=\"utf-8\">");
				out.println("<body style='font-size:20pt;'>");
				out.print("<table border='1' width='50%'>");
				for (User user : list) {
					System.out.println(user);
				}
				out.print("</table>");
				out.println("<body>");		
				out.println("</head>");
				out.println("</html>");
	}

}
