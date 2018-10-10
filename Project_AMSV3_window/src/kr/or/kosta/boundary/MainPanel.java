package kr.or.kosta.boundary;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import javax.swing.JOptionPane;

import kr.or.kosta.entity.Account;
import kr.or.kosta.entity.AccountException;
import kr.or.kosta.entity.AccountManager;
import kr.or.kosta.entity.MinusAccount;

/**
 * 계좌관리 프로그램 MainPanel
 * 
 * @author 권현우
 *
 */
public class MainPanel extends Panel {
	/** AWT 구성 변수 */
	/** Label */
	Label accountTypeL, accountNumL, accountOwnerL, passwordL, borrowMoneyL, depositMoneyL, accountListL, currencyL,
			infoL;
	/** Choice */
	Choice accountTypeC;
	/** TextField */
	TextField accountNumTF, accountOwnerTF, passwordTF, depositMoneyTF, borrowMoneyTF;
	/** Button */
	Button inquireBtn, deleteBtn, searchBtn, registBtn, inquireAllBtn;
	/** TextArea */
	TextArea accountListTA;
	/** GridBagLayout */
	GridBagLayout gridBagLayout;
	GridBagConstraints gridBagConstraints;
	/** frame */
	MainFrame mainFrame;
	/** TextField 유효성Test를 위한 boolean변수 */
	Boolean accountNumTest;
	Boolean accountOwnerTest;
	Boolean depositMoneyTest;
	Boolean borrowMoneyTest;
	Boolean passwdTest;

	/** 유효성검사 */
	Validator validator;

	/** default 생성자 */
	public MainPanel() {
		super();
	}

	/**
	 * MainFrame과 인스턴스변수, Component를 초기화하는 생성자
	 * 
	 * @param frame//MainFrame
	 * 
	 */
	public MainPanel(MainFrame frame) {
		/** mainFrame 초기화 */
		this.mainFrame = frame;

		/** 변수 초기화 */
		/** Label */
		accountTypeL = new Label("계좌종류");
		accountNumL = new Label("계좌번호");
		accountOwnerL = new Label("예금주명");
		passwordL = new Label("비밀번호");
		borrowMoneyL = new Label("대출금액");
		depositMoneyL = new Label("입금금액");
		accountListL = new Label("계좌목록");
		currencyL = new Label("(단위: 원)");
		infoL = new Label("");
		/** Choice */
		accountTypeC = new Choice();
		accountTypeC.add("전체");
		accountTypeC.add("입출금계좌");
		accountTypeC.add("마이너스계좌");

		/** TextField */
		accountNumTF = new TextField("12자리,공백('-'포함)없이 입력");
		accountNumTF.setName("계좌번호");
		accountOwnerTF = new TextField("숫자,공백없이 입력");
		accountOwnerTF.setName("예금주명");
		passwordTF = new TextField();
		passwordTF.setEchoChar('*');
		depositMoneyTF = new TextField("0");
		borrowMoneyTF = new TextField("0");

		/** Button */
		inquireBtn = new Button("조회");
		deleteBtn = new Button("삭제");
		searchBtn = new Button("검색");
		registBtn = new Button("신규등록");
		inquireAllBtn = new Button("전체조회");

		/** TextArea */
		accountListTA = new TextArea();
		accountListTA.setEditable(false);
		accountListTA.setFocusable(false);
		accountListTA.append(
				"------------------------------------------------------------------------------------------" + "\n");
		accountListTA.append(new Formatter()
				.format("  %6s\t %6s\t %6s\t %6s\t%s\n", "계좌종류", "계좌번호", "예금주명", "현재잔액", "대출금액").toString()); // "\t"+"계좌종류\t"+"계좌번호"+"\t"+"예금주명"+"\t"+"현재잔액"+"\t"+"대출금액"+"\n");
		accountListTA.append(
				"==========================================================================================" + "\n");

		// accountListTA.setEnabled(false);

		/** GridBagLayout */
		gridBagLayout = new GridBagLayout();
		gridBagConstraints = new GridBagConstraints();

		/** test변수 초기화 */
		accountNumTest = true;
		accountOwnerTest = true;
		depositMoneyTest = true;
		borrowMoneyTest = true;
		passwdTest = true;
		validator = new Validator();

		/** Component 배치 */
		setComponents();

		/** event 등록 */
		eventRegist();
	}

	/**
	 * Component 배치 메서드
	 */
	private void setComponents() {
		/** 레이아웃 변경 */
		setLayout(gridBagLayout);
		/** Component 추가 */
		add(new Label(""), 0, 0, 1, 1, 0, 0);
		add(accountTypeL, 1, 0, 1, 1, 0, 0);
		add(accountTypeC, 2, 0, 1, 1, 5, 0);
		add(new Label(""), 3, 0, 1, 1, 0, 0);

		add(new Label(""), 0, 1, 1, 1, 0, 0);
		add(accountNumL, 1, 1, 1, 1, 0, 0);
		add(accountNumTF, 2, 1, 1, 1, 5, 0);
		add(new Label(""), 3, 1, 1, 1, 0, 0);
		add(inquireBtn, 4, 1, 1, 1, 0, 0);
		add(new Label(""), 5, 1, 1, 1, 0, 0);
		add(deleteBtn, 6, 1, 1, 1, 0, 0);

		add(new Label(""), 0, 2, 1, 1, 0, 0);
		add(accountOwnerL, 1, 2, 1, 1, 0, 0);
		add(accountOwnerTF, 2, 2, 1, 1, 5, 0);
		add(new Label(""), 3, 2, 1, 1, 0, 0);
		add(searchBtn, 4, 2, 1, 1, 0, 0);

		add(new Label(""), 0, 3, 1, 1, 0, 0);
		add(passwordL, 1, 3, 1, 1, 0, 0);
		add(passwordTF, 2, 3, 1, 1, 5, 0);
		add(new Label(""), 3, 3, 1, 1, 0, 0);
		add(depositMoneyL, 4, 3, 1, 1, 0, 0);
		add(new Label(""), 5, 3, 1, 1, 0, 0);
		add(depositMoneyTF, 6, 3, 4, 1, 0, 0);

		add(new Label(""), 0, 4, 1, 1, 0, 0);
		add(borrowMoneyL, 1, 4, 1, 1, 0, 0);
		add(borrowMoneyTF, 2, 4, 1, 1, 5, 0);
		add(new Label(""), 3, 4, 1, 1, 0, 0);
		add(registBtn, 4, 4, 1, 1, 0, 0);
		add(new Label(""), 5, 4, 1, 1, 0, 0);
		add(inquireAllBtn, 6, 4, 1, 1, 0, 0);
		add(new Label(""), 7, 4, 1, 1, 0, 0);
		add(new Label(""), 8, 4, 1, 1, 0, 0);
		add(new Label(""), 9, 4, 1, 1, 0, 0);
		add(new Label(""), 10, 4, 1, 1, 1, 0);

		add(new Label(""), 0, 5, 1, 1, 0, 0);
		add(accountListL, 1, 5, 1, 1, 0, 0);
		add(infoL, 2, 5, 1, 1, 3, 0);
		add(currencyL, 9, 5, 1, 1, 0, 0);

		add(accountListTA, 1, 6, 9, 1, 0, 0);
	}

	/**
	 * GridbagLayout Component 추가 매서드
	 * 
	 * @param component
	 *            추가할 component 요소
	 * @param gridx
	 *            열값
	 * @param gridy
	 *            행값
	 * @param gridwidth
	 *            넓이
	 * @param gridheight
	 *            높이
	 * @param weightx
	 *            열 가중치
	 * @param weighty
	 *            행 가중치
	 */
	private void add(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weightx,
			double weighty) {
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.gridwidth = gridwidth;
		gridBagConstraints.gridheight = gridheight;
		gridBagConstraints.weightx = weightx;
		gridBagConstraints.weighty = weighty;
		/** 채우기 */
		gridBagConstraints.fill = gridBagConstraints.BOTH;
		/** Component 간격 */
		gridBagConstraints.insets = new Insets(1, 2, 5, 2);
		/** Component 등록 */
		gridBagLayout.setConstraints(component, gridBagConstraints);
		/** Component 추가 */
		add(component);
	}

	/**
	 * 신규 계좌등록시 TextField 유효성 검사
	 * 
	 * @throws AccountException
	 */
	private void valuableCheck() throws AccountException {
		if ((accountNumTF.getText().equals("")) || (accountNumTF.getText().equals("12자리,공백('-'포함)없이 입력")))
			throw new AccountException("계좌번호를 입력해주세요", -300);
		if ((accountOwnerTF.getText().equals("")) || (accountOwnerTF.getText().equals("숫자,공백없이 입력")))
			throw new AccountException("예금주명을 입력해주세요", -310);
		if (passwordTF.getText().equals(""))
			throw new AccountException("비밀번호 입력해주세요", -320);
		if (!(infoL.getText().equals("")))
			throw new AccountException("입력항목을 확인해주세요", -330);
		if (depositMoneyTF.getText().charAt(0) == '-')
			throw new AccountException("음수값은 입력할 수 없습니다", -330);
	}// end valueableCheck()

	/**
	 * 계좌 조회/검색/삭제시 유효성 검사
	 * 
	 * @throws AccountException
	 */
	private void valuableCheck(TextField textField) throws AccountException {
		if (textField.getText().equals("")) {
			switch (textField.getName()) {
			case "계좌번호":
				throw new AccountException("계좌번호를 입력해주세요", -310);
			case "예금주명":
				throw new AccountException("예금주명을 입력해주세요", -310);
			}
		}
	}// end nulCheck(TextField textField)

	/**
	 * passwdTF/depositMoneyTF/borrowMoneyTF 입력시 유효성 검사
	 * 
	 * @param test//Test할
	 *            boolean변수
	 * @throws AccountException
	 */
	private void valuableCheck(boolean test) throws AccountException {
		if ((test == passwdTest) && (!test)) {
			passwordTF.setText("");
			throw new AccountException("올바른 암호를 입력하세요(4자리 숫자)", -340);
		}
		if ((test == depositMoneyTest) && (!test)) {
			depositMoneyTF.setText("0");
			throw new AccountException("올바른 예금액을 입력하세요(양수인 정수값만 허용)", -340);
		}
		if ((test == borrowMoneyTest) && (!test)) {
			borrowMoneyTF.setText("0");
			throw new AccountException("올바른 대출액을 입력하세요(양수인 정수값만 허용)", -340);
		}
	}// end nulCheck(TextField textField)

	/** 계좌 목록 머릿글 등록 */
	public void openSetting() {
		accountListTA.append(
				"------------------------------------------------------------------------------------------" + "\n");
		accountListTA.append(new Formatter()
				.format("  %6s\t %6s\t %6s\t %6s\t%s\n", "계좌종류", "계좌번호", "예금주명", "현재잔액", "대출금액").toString()); // "\t"+"계좌종류\t"+"계좌번호"+"\t"+"예금주명"+"\t"+"현재잔액"+"\t"+"대출금액"+"\n");
		accountListTA.append(
				"==========================================================================================" + "\n");
	}// end openSetting()

	/** textField text 초기화 후 머릿글 등록 메서드 */
	public void cleanTextField() {
		accountNumTF.setText("12자리,공백('-'포함)없이 입력");
		accountOwnerTF.setText("숫자,공백없이 입력");
		passwordTF.setText("");
		depositMoneyTF.setText("0");
		borrowMoneyTF.setText("0");
		accountListTA.setText("");
		accountTypeC.select(0);
		borrowMoneyTF.setEnabled(true);
		openSetting();
	}// end cleanTextField()

	/** Component 이벤트 등록 */
	private void eventRegist() {

		/** registBtn action 발생시 */
		registBtn.addActionListener(new ActionListener() {
			/*
			 * 유효성 검사후 신규계좌 등록
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					valuableCheck();
					mainFrame.addAccount();
				} catch (AccountException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Caution", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});// end ActionListner
		/** inquireAllBtn action 발생시 */
		inquireAllBtn.addActionListener(new ActionListener() {
			/*
			 * 전체계좌리스트 출력
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainFrame.listAll();
			}
		});// end ActionListner
		/** inquireBtn action 발생시 */
		inquireBtn.addActionListener(new ActionListener() {
			/*
			 * 유효성 검사후 계좌번호에 맞는 계좌 출력
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					valuableCheck(accountNumTF);
				} catch (AccountException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Caution", JOptionPane.ERROR_MESSAGE);
				}
				try {
					mainFrame.inquaireAccount();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});// end ActionListner
		/** deleteBtn action 발생시 */
		deleteBtn.addActionListener(new ActionListener() {
			/*
			 * 유효성 검사후 입력 계좌번호와 일치하는 계좌 삭제, 리스트 출력
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					valuableCheck(accountNumTF);
				} catch (AccountException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Caution", JOptionPane.ERROR_MESSAGE);
				}
				try {
					mainFrame.removeAccount();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});// end ActionListner
		/** searchBtn action 발생시 */
		searchBtn.addActionListener(new ActionListener() {

			/*
			 * 입력한 예금주명과 일치하는 계좌 출력
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					valuableCheck(accountOwnerTF);
					mainFrame.searchAccount();
				} catch (AccountException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "test", JOptionPane.ERROR_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});// end ActionListner

		/** accoutTypeC item =입출금계좌 일때 borowwMoneyTF block 메서드 */
		accountTypeC.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				borrowMoneyTF.setEnabled(true);
				if (((String) e.getItem()).equals("입출금계좌")) {
					borrowMoneyTF.setText("0");
					borrowMoneyTF.setEnabled(false);
				}
			}
		});// end ItemListener

		/** accountNumTF focusEvent 발생시 */
		accountNumTF.addFocusListener(new FocusAdapter() {
			/** focus확득시 TF 초기화 */
			@Override
			public void focusGained(FocusEvent e) {
				accountNumTF.setText("");
			}

			@Override
			/** focus 상실 시 유효성 검사후 '-' 추가 */
			public void focusLost(FocusEvent e) {
				/** 유효성 검사를 위한 TextField입력값 */
				String testText = accountNumTF.getText();
				/** 기본 문자열 setting */
				if (accountNumTF.getText().equals("")) {
					accountNumTF.setText("12자리,공백('-'포함)없이 입력");
					accountNumTest = false;
					return;
				}

				// ** 입력값이 공백없는 12자리의 정수인가 *//
				accountNumTest = validator.numberTest(12, testText);
				/** 유효성 검사 통과 시 "-" 추가 */
				if (accountNumTest) {
					accountNumTF.setText(
							testText.substring(0, 4) + "-" + testText.substring(4, 8) + "-" + testText.substring(8));
					/** accountNum/accountOwner 유효성검사 통과 시 infoL 초기화 */
					if (accountNumTest && accountOwnerTest)
						infoL.setText("");
					else
						/** accountOwner만 미통과 시 infoL에 상황 공지 */
						infoL.setText("유효한 예금주명 입력");
					infoL.setForeground(Color.RED);

				} else {
					/** 유효성 검사 미 통과 시 infoL에 상황 공지 */
					infoL.setText("유효한 계좌번호 입력");
					infoL.setForeground(Color.RED);
				}

			}
		});// end addFocusListener

		/** accountOwnerTF focusEvent 발생시 */
		accountOwnerTF.addFocusListener(new FocusAdapter() {
			@Override
			/** focus획득시 TF 초기화 */
			public void focusGained(FocusEvent e) {
				accountOwnerTF.setText("");
			}

			@Override
			/** focus 상실 시 유효성 검사 */
			public void focusLost(FocusEvent e) {
				/** 유효성 검사를 위한 TextField입력값 */
				String testText = accountOwnerTF.getText();
				// ** 입력값이 공백이거나 숫자가 없는 문자인가? *//
				accountOwnerTest = validator.charTest(0, testText);
				/** 입력값 없을시 기본 문자열 입력 */
				if (accountOwnerTF.getText().equals("")) {
					accountOwnerTF.setText("숫자,공백없이 입력");
					accountOwnerTest = false;
					return;
				}
				/** 유효성 검사 미통과 시 infoL에 상황공지 */
				if (!accountOwnerTest) {
					infoL.setText("유효한 예금주명 입력");
					infoL.setForeground(Color.RED);
				} else {
					/** accountNum/accountOwner 유효성검사 통과 시 infoL 초기화 */
					if (accountNumTest && accountOwnerTest) {
						infoL.setText("");
						accountOwnerTF.setText(testText.trim());
					} else
						/** accountNum만 미통과시 infoL에 상황공지 */
						infoL.setText("유효한 계좌번호 입력");
					infoL.setForeground(Color.RED);
				}

			}

		});// end addFocusListener

		/** passWordTF focusEvent 발생시 */
		passwordTF.addFocusListener(new FocusAdapter() {
			@Override
			/** focus획득시 TF 초기화 */
			public void focusGained(FocusEvent e) {
				passwordTF.setText("");
			}

			@Override
			/** focus 상실 시 유효성 검사 */
			public void focusLost(FocusEvent e) {
				/** 유효성 검사를 위한 TextField입력값 */
				String testText = passwordTF.getText();
				//** 입력값이 4자리의 정수인가? *//
				passwdTest = validator.numberTest(4, testText);
				if (passwordTF.getText().equals("")) {
					passwdTest = false;
					return;
				}
				try {
					valuableCheck(passwdTest);
				} catch (AccountException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Caution", JOptionPane.ERROR_MESSAGE);
				} finally {
					passwdTest = true;
				}
			}
		});
		/** depositMoneyTF focusEvent 발생시 */
		depositMoneyTF.addFocusListener(new FocusAdapter() {
			@Override
			/** focus획득시 TextField 초기화 */
			public void focusGained(FocusEvent e) {
				depositMoneyTF.setText("");
			}

			@Override
			/** focus상실시 유효성 검사 */
			public void focusLost(FocusEvent e) {
				/** 유효성 검사를 위한 TextField입력값 */
				String testText = depositMoneyTF.getText();
				depositMoneyTest = validator.numberTest(0, testText);
				/** 값 미 입력시 TextField 초기화 */
				if (depositMoneyTF.getText().equals("")) {
					depositMoneyTF.setText("0");
					depositMoneyTest = false;
					return;
				}

				try {
					valuableCheck(depositMoneyTest);
				} catch (AccountException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Caution", JOptionPane.ERROR_MESSAGE);
				} finally {
					depositMoneyTest = true;
				}
			}

		});// end addFocusListener

		/** borrowMoneyTF focusEvent 발생시 */
		borrowMoneyTF.addFocusListener(new FocusAdapter() {
			@Override
			/** focus 획득시 TextField 초기화 */
			public void focusGained(FocusEvent e) {
				borrowMoneyTF.setText("");
			}

			@Override
			/** focus 상실시 유효성 검사 */
			public void focusLost(FocusEvent e) {
				/** 유효성 검사를 위한 TextField입력값 */
				String testText = borrowMoneyTF.getText();
				borrowMoneyTest = validator.numberTest(0, testText);
				/** 값 미입력시 TextField 초기화 */
				if (borrowMoneyTF.getText().equals("")) {
					borrowMoneyTF.setText("0");
					borrowMoneyTest = false;
					return;
				}

				try {
					valuableCheck(borrowMoneyTest);
				} catch (AccountException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Caution", JOptionPane.ERROR_MESSAGE);
				} finally {
					borrowMoneyTest = true;
				}
			}

		});// end addFocusListener
	}// end eventRegist();
}// end MainPanel
