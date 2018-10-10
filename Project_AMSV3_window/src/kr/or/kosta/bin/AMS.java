package kr.or.kosta.bin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.or.kosta.boundary.MainFrame;
import kr.or.kosta.boundary.MainPanel;
import kr.or.kosta.entity.Account;
import kr.or.kosta.entity.AccountDAO;
import kr.or.kosta.entity.AccountManager;
import kr.or.kosta.boundary.MainPanel;

/**
 * 
 * 은행 계좌 관리 애플리케이션
 * `
 * 
 * @author 권현우
 *
 */
public class AMS {
	public static void main(String[] args) throws IOException {
		/**최대 100개의 account를 관리할 수 있는 AccountManager 생성*/
		//AccountManager manager = new AccountManager(100);
		
		/**mianFrame 생성*/
		MainFrame mainFrame= new MainFrame(Account.bankName);
		AccountDAO dao = new AccountDAO();
		/**MainFrame에 AccountManager 추가*/
		mainFrame.setAccountDAO(dao);
		/**Test Account등록*/
		//mainFrame.tempRegist();
	}// end main()
}// end class
