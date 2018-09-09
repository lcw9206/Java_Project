package chat;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Vector;

public class Java231_ChatHandler implements Runnable{ //접속한 클라이언트를 스레드로 돌리라
		Socket socket;
		private DataInputStream dataIn;
		private DataOutputStream dataOut;
		private Thread th;
		private static Vector handlers =new Vector(); //현재 서버에 접속되어있는 클라이언트의 정보 
		//공유가능하게 static으로 (같은 타입객체로눈ㅇㄹㅇㄹㅇㄹㅇㄹ
	public Java231_ChatHandler() {
	}
	public Java231_ChatHandler(Socket socket) {
		this.socket=socket;
		
		
	}
	public void initStart() { //스레드 마다의 입출력 스트림 만들수있게 ㅎㅎ
		if(th==null) {
			try {
				InputStream is = socket.getInputStream();
				OutputStream os= socket.getOutputStream();
				dataIn = new DataInputStream(new BufferedInputStream(is));
				dataOut= new DataOutputStream(new BufferedOutputStream(os));
				th= new Thread(this);
				th.start();  //스타트~
			
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
	@Override
	public void run() {
		handlers.addElement(this); //현재객체를 벡터에 ㅎ.............
		
		while(!Thread.interrupted()) {
			try {
					
				String mese=dataIn.readUTF(); //읽기
				broadcast(mese);   //보내주기
				
			} catch (IOException e) {
				System.out.println(e.toString());
				return;
			}
		}
		
	}////////////////////////////////////////////////////////////////////////////
	
 synchronized public  void broadcast(String message) {
		Enumeration<Java231_ChatHandler> enu=handlers.elements(); //클라이언트 정보 나열
		while(enu.hasMoreElements()){
			Java231_ChatHandler client=(Java231_ChatHandler)enu.nextElement();
		try {
			client.dataOut.writeUTF(message);
			client.dataOut.flush();    //다 보내주기 ㅎ;;;;;
		
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			client.stop();
		}
		}
	 	
	}//broadcast
 
 
 synchronized public void stop() {
	 if(th!=null) {
		 if(th!=Thread.currentThread()) {
			 th.interrupt();
			 th=null;
			 
			 try {
				dataOut.close();
				
			} catch (IOException e) {
		
			//	e.printStackTrace();
			}
		 }
		 
		 
		 
	 }
	 
 }
 
	
}
