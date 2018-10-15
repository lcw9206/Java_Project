package kr.or.kosta.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class loginServlet
 */
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	Cookie[] cookies = request.getCookies();
	System.out.println(cookies != null);
	if(cookies != null) {
		for (Cookie cookie : cookies) {
			if(cookie.getValue() =="") {
				System.out.println(cookie.getName() + ":" + cookie.getValue());
				cookie.setMaxAge(0);
				response.addCookie(cookie);	
				response.sendRedirect("/homework/index.html");
			}
		}
		return;
	}
	System.out.println("here");
	String Id = request.getParameter("userId");
	String pass = request.getParameter("userPw");
	Cookie cookie = new Cookie("loginID", Id);
	response.addCookie(cookie);
	response.sendRedirect("/homework/index.html");
	}
}
