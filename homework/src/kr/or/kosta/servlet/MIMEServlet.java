package kr.or.kosta.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
	마임타입의 이해
 */
public class MIMEServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain; charset = utf-8");
		//header에 들어가는 양식 Content-Type: text/plain; charset = urf-8
		PrintWriter out = response.getWriter();
		out.println("일반적인 텍스트입니다...");
	}

}
