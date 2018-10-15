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


public class HttpServletRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//웹클라이언트로 (동적)출력 하고자 하는 데이터 생성
				//응답메세지의 해더에 컨텐츠 유형 설정
				response.setContentType("text/html; charset=utf-8");
				/**클라이언트 단으로 출력*/
				PrintWriter out = response.getWriter();
				String result="";
				
				String ip = request.getRemoteAddr();//ip
				String method = request.getMethod();//요청방식
				String url = request.getRequestURI();//url
				String protocol = request.getProtocol();//protocol
				
				/**브라우져에서 서버로 보내는 메타데이터*/
				Enumeration<String> headerNames = request.getHeaderNames();
				
				/**쿼리스트링*/
				/*String queryString = request.getQueryString();*/
				String queryString = request.getParameter("name");
				
				/**application Name*/
				String applicationName = request.getContextPath();
				
				/**servlet path*/
				String servletpath = request.getServletPath();
				out.println("<html>");
				out.println("<head>");
				out.println("<title>Servlet Programming</title>");
				out.println("<meta charset=\"utf-8\">");
				out.println("<body style='font-size:20pt;'>");
				out.print("<ul>");
				out.print("<li>"+ip+"</li>");
				out.print("<li>"+method+"</li>");
				out.print("<li>"+url+"</li>");
				out.print("<li>"+protocol+"</li>");
				out.print("<h2>"+"header"+"</h2>");
				while(headerNames.hasMoreElements()) {
					String name = headerNames.nextElement();
				out.print("<li>"+name+" : "+request.getHeader(name)+"</li>");
				}
				out.print("<li>"+"queryString : "+queryString+"</li>");
				out.print("<li>"+"applicationName : "+applicationName+"</li>");
				out.print("<li>"+"servletpath : "+servletpath+"</li>");
				
				
				out.print("</ul>");
				out.println("<body>");		
				out.println("</head>");
				out.println("</html>");
	}

}
