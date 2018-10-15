package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**init 개채사용*/
public class DispatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//response.sendRedirect(""); 와는 다른개념
		request.setCharacterEncoding("utf-8");
		/**디스패치 forward*/
		RequestDispatcher rd = request.getRequestDispatcher("/hello.do"); //path: 현제 컨태이너 속에 생성된 servlet한정 /servlet 빼고써야함
		rd.forward(request, response);
		
		//rd.include(request, response);
	}

}
