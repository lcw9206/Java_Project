package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**init 개채사용*/
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//response.sendRedirect(""); 와는 다른개념
		
		String id="bangry";
		
		Cookie cookie = new Cookie("loginID", id);
//		cookie.setMaxAge(60*60*24*30);
		cookie.setDomain("http://www.naver.com");
		response.addCookie(cookie);//response메세지 해더에 넣어
//		cookie.setPath("/");
		
//response.sendRedirect("hello2");
	}

}
