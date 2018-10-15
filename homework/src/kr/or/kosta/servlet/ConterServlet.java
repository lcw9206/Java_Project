package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int count = 0;
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("counts")) {
					count = Integer.parseInt(cookie.getValue());
				}
			}
		}
		count ++;
		
		Cookie cookie = new Cookie("counts", String.valueOf(count));
		cookie.setMaxAge(60*60*24*30);
		response.addCookie(cookie);
		String cookieName = null;
		String cookieValue = null;

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet Programming</title>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<body style='font-size:20pt;'>");
		out.print("<h3>" + count + "</h3>");
		out.println("<body>");
		out.println("</head>");
		out.println("</html>");
	}
}
