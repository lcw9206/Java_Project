package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**init 개채사용*/
public class ServletContextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//response.sendRedirect(""); 와는 다른개념
		request.setCharacterEncoding("utf-8");
		
		String message = "공유 데이터 입니다."; //데이터베이스에서 받아온 데이터 
				
		ServletContext context = getServletContext();
		String serverName = context.getServerInfo();
		System.out.println(serverName);//Apache Tomcat/8.0.53
		System.out.println(context.getContextPath());// /servlet
		context.setAttribute("shareData", message);
		//response.sendRedirect("hello.do");
		System.out.println(context.getInitParameter("location"));
		System.out.println(context.getAttribute("shareData"));
	}

}
