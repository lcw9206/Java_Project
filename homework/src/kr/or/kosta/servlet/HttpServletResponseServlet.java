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


public class HttpServletResponseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
/*		//웹클라이언트로 (동적)출력 하고자 하는 데이터 생성
				//응답메세지의 해더에 컨텐츠 유형 설정
				response.setContentType("text/html; charset=utf-8");
				*//**클라이언트 단으로 출력*//*
				//response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				
				String name= request.getParameter("name");
				
				if(name != null && name.length() !=0 ) {
					if(name.equals("bangry")) {
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
					}
				}
				
				
				PrintWriter out = response.getWriter();

				out.println("<html>");
				out.println("<head>");
				out.println("<title>Servlet Programming</title>");
				out.println("<meta charset=\"utf-8\">");
				out.println("<body style='font-size:20pt;'>");
				out.print("<ul>");
				out.print("</ul>");
				out.println("<body>");		
				out.println("</head>");
				out.println("</html>");*/
		
		//Dispatch 기술
		//raw한 방법
/*		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);//301
		response.setHeader("Location", "/servlet/practice.do");*/
		response.sendRedirect("/servlet/hello.do");
	}

}
