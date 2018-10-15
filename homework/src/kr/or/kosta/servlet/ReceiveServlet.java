package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ReceiveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//요청되는 데이터를 한글로 변환
		request.setCharacterEncoding("utf-8");
		//웹클라이언트로 (동적)출력 하고자 하는 데이터 생성
		//응답메세지의 해더에 컨텐츠 유형 설정
		
		response.setContentType("text/html; charset=utf-8");
		
		/**클라이언트 단으로 출력*/
		PrintWriter out = response.getWriter();
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String team = request.getParameter("teams");
		String [] hobbys = request.getParameterValues("hobby");
		
		Enumeration<String> paramNames = request.getParameterNames();
		
		while(paramNames.hasMoreElements()) {
			System.out.println(request.getParameter(paramNames.nextElement()));
		}
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet Programming</title>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<body style='font-size:20pt;'>");

		out.print("<h3>"+firstname+"</h3>");
		out.print("<h3>"+lastname+"</h3>");
		out.print("<h3>"+team+"</h3>");
		if(hobbys != null) {
		for (String hobby : hobbys) {
			out.println("<h3>"+hobby+"</h3>");
		}
		}
		out.println("<body>");		
		out.println("</head>");
		out.println("</html>");
	}

}
