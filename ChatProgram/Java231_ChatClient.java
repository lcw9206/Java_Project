package chat;

import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Java231_ChatClient implements ActionListener,Runnable{
	String userName;
	String host;
	int port;
	private JFrame frm;
	private JPanel pan;
	private JTextArea taOutput;
	private JLabel lbName;
	private JTextField tfInput;
	private DataInputStream dataIn;
	private DataOutputStream dataOut;
	Thread th;
	
	public Java231_ChatClient(String host,int port) {
		System.out.print("사용자 이름을 입력하세요.. : ");
		Scanner sc=new Scanner(System.in);
		userName=sc.nextLine();
		if(userName==null) {
			userName="guest";
		}
		this.host=host; //멤버변수에 넣어준다
		this.port=port;
		initStart();
		
	}//chatclient 
	
	///////////////////////////////////////////////////////////////////////////////////////////
	
	private void initStart() {
		if(th==null) {
			Socket socket=null;
			try {
				socket=new Socket(host,port);
				InputStream is=socket.getInputStream();
				OutputStream os=socket.getOutputStream();
				dataIn=new DataInputStream(new BufferedInputStream(is));
				dataOut=new DataOutputStream(new BufferedOutputStream(os));
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("서버와 연결을 종료합니다.");
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		th=new Thread(this); //서버에서 보내준 메세지를 읽을 스레드
		th.start();
		
	}//ini
	
	private void initComponent() {
		frm=new JFrame("채팅 프로그램["+host+":"+"port"+"]");
		taOutput=new JTextArea(); //채팅창 (서버에서 보내준것을 받는곳)
		taOutput.setEditable(false);
		
		tfInput=new JTextField(); //입력창
		tfInput.addActionListener(this);//엔터치면 전송ㅇㅅㅇ
		
		
		JScrollPane scroll=new JScrollPane(taOutput);
		pan=new JPanel();
		
		lbName=new JLabel("입력");
		
		pan.setLayout(new BorderLayout());
		pan.add(lbName,BorderLayout.WEST);
		pan.add(tfInput, BorderLayout.CENTER);
		
		frm.add(BorderLayout.CENTER,scroll);
		frm.add(BorderLayout.SOUTH,pan);
		
	
		
		
		
		
		frm.setSize(400, 400);
		frm.setVisible(true);
		
		frm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frm.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				stop();
			}
		});
		//frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj=e.getSource();
		if(obj==tfInput) {
		
			try {
				dataOut.writeUTF(userName+":"+e.getActionCommand());//tfinput의 문자열 가져옴
				dataOut.flush();
				tfInput.setText("");
				tfInput.requestFocus();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				handleIOException(e1);
			}
		}
		
	}//actionperformed
	
	private void handleIOException(IOException e) {
		if(th!=null) {//스레드가 종료되있지 않으면
			tfInput.setVisible(false);
			frm.validate();
			if(th!=Thread.currentThread()) {//지금 쓰레드가 아니면
				th.interrupt();
				th=null;  //가져와 서 종료시키기ㅋ
				try {
					dataOut.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		}
	
	
	
	@Override
	public void run() { //보내주는 것을 받기위한 스레드
		
		while(!Thread.interrupted()) {//스레드가 이상이 있지 않으믄
		
		System.out.println("메세지 수신 대기중\n");
		try {
			String line=dataIn.readUTF();
			taOutput.append(line+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	}

	public void stop() {
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
			 
			 frm.setVisible(false);
			 frm.dispose();
			 System.exit(0);
			 
		 }	
	
	}
	
	
	
	public static void main(String[] args) {

		Java231_ChatClient client =
		new Java231_ChatClient("LocalHost",7777);//로컬의 아이피주소는 무조건 127.0.0.1입니다
		client.initComponent();
		
		
		
	}


}
