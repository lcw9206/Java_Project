package kr.or.kosta.boundary;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kr.or.kosta.entity.AccountException;
import javax.swing.JOptionPane;

import kr.or.kosta.entity.Account;
import kr.or.kosta.entity.AccountDAO;
import kr.or.kosta.entity.AccountManager;
import kr.or.kosta.entity.MinusAccount;

/**
 * 계좌관리 프로그램 MainFrame
 * 
 * @author 권현우
 *
 */
public class MainFrame extends Frame {
	/** 계좌관리 인스턴스 */
	/** 계좌정보를 불러올 accountManager */
	//AccountManager accountManager;
	AccountDAO accountDao;
	/** girdBaglayout으로 만들어진 panel */
	MainPanel mainPanel;
	List<Account> list;

	/** 테스트에 이용할 AccountList */

	/** Account Manager setter/getter */
	public void setAccountDAO(AccountDAO accountDao) {
		this.accountDao = accountDao;
	}// end setter()

	public AccountDAO getAccountDAO() {
		return accountDao;
	}// end getter()

	/** 생성자 */
	/** default 생성자 */
	public MainFrame() {
		this("AMS System");
	}// end MainFrame()

	/** 재목이 있는 frame을 출력하는 생성자 */
	public MainFrame(String title) {
		super(title);
		mainPanel = new MainPanel(this);
		list = new ArrayList<Account>();

		/** 화면 구현 */
		add(mainPanel);
		setSize(650, 430);

		/** 화면 가운데 등록 */
		setCenterPosition();

		/** 화면 실행 */
		setVisible(true);

		/** 이벤트 등록 */
		eventRegist();
	}// end MainFrame()

	/*** 프로그램 출력 위치를 화면 중앙으로 출력하는 메서드 */
	public void setCenterPosition() {
		Toolkit.getDefaultToolkit().beep();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (dim.width - getSize().width) / 2;
		int y = (dim.height - getSize().height) / 2;
		setLocation(x, y);
	}// end setCenterPosition()

	/** 계좌목록 Test를 위한 인스턴스 추가 메서드 
	 * @throws IOException */
	public void tempRegist() throws IOException {
		try {
			accountDao.add(new Account("1111-2222-3333", "홍길동", 1111, 50000));
			accountDao.add(new Account("1111-2222-4444", "강성구", 1111, 300000));
			accountDao.add(new Account("1111-2222-5555", "김기윤", 1111, 1000));
			// ** MinusAccount 등록 *//*
			accountDao.add(new MinusAccount("2222-2222-3333", "백종현", 1111, 0, 5000000));
			accountDao.add(new MinusAccount("2222-2222-4444", "김기정", 1111, 3000000, 5000000));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	/** 신규 계좌 등록 
	 * @throws IOException */
	public void addAccount() throws IOException {
		try {
		if (mainPanel.accountTypeC.getSelectedItem() == "입출금계좌") {
			Account account = new Account(mainPanel.accountNumTF.getText(), mainPanel.accountOwnerTF.getText(),
					Integer.parseInt(mainPanel.passwordTF.getText()),
					Long.parseLong(mainPanel.depositMoneyTF.getText()));
			
				accountDao.add(account);
			
				// TODO Auto-generated catch block
			
			mainPanel.cleanTextField();
		} else if (mainPanel.accountTypeC.getSelectedItem() == "마이너스계좌") {
			MinusAccount account = new MinusAccount(mainPanel.accountNumTF.getText(),
					mainPanel.accountOwnerTF.getText(), Integer.parseInt(mainPanel.passwordTF.getText()),
					Long.parseLong(mainPanel.depositMoneyTF.getText()),
					Long.parseLong(mainPanel.borrowMoneyTF.getText()));
			accountDao.add(account);
			mainPanel.cleanTextField();
		} else if (mainPanel.accountTypeC.getSelectedItem() == "전체") {
			JOptionPane.showMessageDialog(null, "계좌종류를 선택해주세요", "Caution", JOptionPane.ERROR_MESSAGE);
			return;
		}
		 list = accountDao.list();
		if (list.size() == 0) {
			mainPanel.accountListTA.append("등록된 계좌가 없습니다.\n");
		} else {
			for (Account listPrint : list) {
				mainPanel.accountListTA.append(listPrint.toString() + "\n");
			}
			mainPanel.accountListTA.append(
					"------------------------------------------------------------------------------------------"
							+ "\n");

		}
	} catch (IOException e) {
		e.printStackTrace();
	} 
	}// end addAccount()

	/** 전체 계좌 조회 
	 * @throws IOException */
	public void listAll()  {
		mainPanel.cleanTextField();
			try {
				list = accountDao.list();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (list.size() == 0) {
			mainPanel.accountListTA.append("등록된 계좌가 없습니다.\n");
		} else {
			for (Account listPrint : list) {
				mainPanel.accountListTA.append(listPrint.toString() + "\n");
			}
			mainPanel.accountListTA
					.append("------------------------------------------------------------------------------------------"
						+ "\n");
		}

	}// end listAll()

	/** 예금주명으로 계좌 조회 
	 * @throws IOException */
	public void searchAccount() throws IOException {
		List<Account> list = accountDao.search(mainPanel.accountOwnerTF.getText());
		mainPanel.cleanTextField();
		if (list.size() != 0) {
			for (Account SearchName : list)
				mainPanel.accountListTA.append(SearchName.toString() + "\n");
		} else {
			mainPanel.accountListTA.append("해당 계좌가 존재하지 않습니다.\n");
		}
		mainPanel.accountListTA.append("------------------------------------------------------------------------------------------" + "\n");
	}// end searchAccount()

	/** 계좌번호로 계좌 삭제 
	 * @throws IOException */
	public void removeAccount() throws IOException {
		Boolean deleteTest = accountDao.remove(mainPanel.accountNumTF.getText());
		mainPanel.cleanTextField();
		/** delete 이후의 계좌리스트를 받아오는 변수 */
		List<Account> deleteList = new ArrayList<Account>();
		if (deleteTest) {
			deleteList = accountDao.list();
			for (Account account : deleteList) {
				mainPanel.accountListTA.append(account.toString() + "\n");
			}
		} else {
			mainPanel.accountListTA.append("해당 계좌가 존재하지 않습니다.\n");
		}
		mainPanel.accountListTA.append(
				"------------------------------------------------------------------------------------------" + "\n");

	}// end removeAccount()

	/** 계좌번호로 계좌 조회 
	 * @throws IOException */
	public void inquaireAccount() throws IOException {
		Account search = accountDao.get(mainPanel.accountNumTF.getText());
		mainPanel.cleanTextField();
		if (search != null) {
			mainPanel.accountListTA.append(search.toString()+"\n");
		} else {
			mainPanel.accountListTA.append("해당 계좌가 존재하지 않습니다.\n");
		}
		mainPanel.accountListTA
		.append("------------------------------------------------------------------------------------------"
				+ "\n");
	}// end inquaireAccount()

	/** frame을 종료 메서드 */
	public void exit() {
		setVisible(false);
		dispose();
		System.exit(0);
	}// end exit()

	/** 이벤트 처리 메서드 */
	public void eventRegist() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				exit();
			}// end windowClosing()
		});// end addWindowListner()
	};// end eventRegits()
}// end MainFrame
