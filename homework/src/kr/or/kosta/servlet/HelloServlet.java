package kr.or.kosta.servlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**최초 작성 서블릿
 * @author 권현우
 *Servlet -> 확장 HttpServlet -> 상속 
 */

public class HelloServlet extends HttpServlet/* implements Servlet*/{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//웹클라이언트로 (동적)출력 하고자 하는 데이터 생성
		ServletContext context = getServletContext();
		Calendar now = Calendar.getInstance();
		String today = String.format("%1tF %1$tT", now);
		//응답메세지의 해더에 컨텐츠 유형 설정
		response.setContentType("text/html; charset=utf-8");
		/**클라이언트 단으로 출력*/
		PrintWriter out = response.getWriter();
	
		/*String html = "<html>";
				html += 
		*/
		

		
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Servlet Programming</title>");
		out.println("<meta charset=\"utf-8\">");
		out.println("<body>");
		//out.println("<h2>오늘은"+context.geta+"입니다....<h2>");
		out.println("<body>");		
		out.println("</head>");
		out.println("</html>");
		
	}
}
