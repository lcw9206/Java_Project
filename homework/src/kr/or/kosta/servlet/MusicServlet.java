package kr.or.kosta.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
	음악서비스 서블릿
 */
public class MusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String path="";
	private String file = "sample.pptx";
	@Override
		public void init(ServletConfig config) throws ServletException {
		path = config.getInitParameter("path");
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("audio/mpeg");//1 음원 형식으로 바꿔준다
		response.setContentType("application/vnd.ms-powerpoint");// application 형식으로 바꿔준다
		
		//header에 들어가는 양식 Content-Type: text/plain; charset = urf-8
	     InputStream in = new FileInputStream(path + file);
	     
	     // response가 제공하는 바이트입력스트림 취득
	     OutputStream out = response.getOutputStream();
	     byte[] buffer = new byte[1024];
	     int count = 0;
	     try{
	          while( (count = in.read(buffer)) != -1){
	               out.write(buffer, 0, count);
	          }
	     }finally{
	 
	          if(out != null) out.close();
	          if(in != null) in.close();
	     }

	}

}
