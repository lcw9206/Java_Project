package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class HelloServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String driver = "";//동적 값
	
	@Override//환경변수 불러오기
	public void init(ServletConfig config) throws ServletException {
		driver = config.getInitParameter("driver");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println(request.getSession().getAttribute("userName"));
	
	}

}
