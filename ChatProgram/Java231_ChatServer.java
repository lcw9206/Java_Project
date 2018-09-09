package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Java231_ChatServer {	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket server = new ServerSocket(7777); //서버생성 
			
			
			while(true) { //다른 클라이언트도 받아야 하니까... ;;
				Socket client = server.accept();
			
			System.out.println("Client가 "+client.getInetAddress()+"로 접속..;");
			Java231_ChatHandler handler=new Java231_ChatHandler(client); //스레드
													//client의 정보를 보낸다.
			handler.initStart();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
