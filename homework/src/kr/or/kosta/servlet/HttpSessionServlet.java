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
import javax.servlet.http.HttpSession;

/**init 개채사용*/
public class HttpSessionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//response.sendRedirect(""); 와는 다른개념
		request.setCharacterEncoding("utf-8");
		String name ="권현우";
		HttpSession session = request.getSession();
		System.out.println(session.isNew());
		session.setAttribute("userName", name);
		//response.sendRedirect("hello2");
	}

}
