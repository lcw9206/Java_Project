package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet 생명주기 테스트
 */
public class LifecycleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**방문객 수*/
	private int count;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LifecycleServlet() {
        System.out.println("LifecycleServlet called");//서블릿 생성
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		 System.out.println("init(ServletConfig config) called");
		 super.init(config);
		 count=0;
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("init() called");
	}
	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		 System.out.println("destroy called");
		 }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	System.out.println("service called");
	System.out.println(++count);
	super.service(req, resp);
}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**클라이언트 단으로 출력*/
		System.out.println(request.getRemoteAddr());
		response.setContentType("text/html; charset=utf-8");
		System.out.println(request);
		System.out.println(response);
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>서블릿 카운터</title>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<body>");
		out.println("<h2>당신은"+count+"번째 방문객 입니다....<h2>");
		out.println("<body>");		
		out.println("</head>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 System.out.println("doPost called");	
	}

}
