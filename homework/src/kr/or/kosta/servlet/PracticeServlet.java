package kr.or.kosta.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PracticeServlet
 */
public class PracticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String path =  "C:\\KOSTA187\\workspace\\ServletStudy\\WebContent\\asset\\";
	private String file = "sample.pptx";
	private String param="";
	@Override
	public void init(ServletConfig config) throws ServletException {
	param = config.getInitParameter("name");	
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		process(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		//Content_Type
		response.setContentType("text/html; charset=utf-8");
		//요청되는 데이터를 한글로 변환
		request.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		String ip = request.getRemoteAddr();
		String method = request.getMethod();
		String url = request.getRequestURI();
		String protocol = request.getProtocol();	
		Enumeration<String>headerNames = request.getHeaderNames();
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String country = request.getParameter("country");
		String queryString = request.getParameter("name");
		String applicationName = request.getContextPath();
		String applicationPath = request.getServletPath();
		
		request.setAttribute("data", "babo");
		RequestDispatcher rd = request.getRequestDispatcher("/practice2.do");
		rd.forward(request, response);
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet Programming</title>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<body style='font-size:20pt;'>");
		out.print("<h3>"+"param : "+applicationPath+"</li>");
		out.println("<body>");		
		out.println("</head>");
		out.println("</html>");
		
		
	}

}
