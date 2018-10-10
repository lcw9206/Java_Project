package kr.or.kosta.entity;

import java.util.List;

import javax.swing.JOptionPane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class AccountDAO {
	/** 저장 파일 경로 */
	private static final String FILE_PATH = "accounts.dbf";

	/** 레코드(계좌)수 저장을 위한 레코드 사이즈 */
	private static final int RECORD_COUNT_LENGTH=4;
	/** 레코드(계좌번호, 예금주, 비밀번호, 잔액, 대출금액) 저장을 위한 컬럼별 사이즈 고정 */
	private static final int ACCOUNTNUM_LENGTH = 28; // 계좌번호(36바이트)
	private static final int ACCOUNTOWNER_LENGTH = 10; // 이름(10바이트)
	private static final int ACCOUNTPASS_LENGTH = 4; // 비밀번호(4바이트)
	private static final int ACCOUNTDEPOSIT_LENGTH = 8;// 예금액(8바이트)
	private static final int ACCOUNTBORROW_LENGTH = 8;// 대출액(8바이트);

	/** 단일 계좌 정보 저장을 위한 레코드 사이즈: 56바이트 */
	public static final int RECORD_LENGTH = ACCOUNTNUM_LENGTH + ACCOUNTOWNER_LENGTH + ACCOUNTPASS_LENGTH
			+ ACCOUNTDEPOSIT_LENGTH + ACCOUNTBORROW_LENGTH;

	public RandomAccessFile file;

	private int recordCount = 0;

	/** 생성자 */
	/** default 생성자 */
	
	/**
	 * 총 계좌 수를 고정하는 생성자
	 * 
	 * @throws IOException
	 */
	public AccountDAO() throws IOException {
		file = new RandomAccessFile(FILE_PATH, "rw");
		  if (file.length() != 0) { recordCount = file.readInt(); } else { System.out.println("등록계좌 없음"); }
	}

	/** getter */
	public int getRecordCount() {
		return recordCount;
	}

	/**
	 * 입력받은 정보를 담은 계좌를 추가하는 메서드
	 * 
	 * @throws IOException
	 * @throws AccountException 
	 * 
	 */
	public void add(Account account) throws IOException{
		file.seek((recordCount * RECORD_LENGTH) + RECORD_COUNT_LENGTH);//0
		System.out.println("pointer" + file.getFilePointer());
		/** 새로운 계좌 추가 */
		String accountNum = account.getAccountNum();
		/*Account testAccount = new Account();
		testAccount = get(accountNum);
		if(testAccount !=null) {
				try {
					throw new AccountException("동일한 계좌가 존재합니다", -200);
				} catch (AccountException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}*/
		String accountOwner = account.getAccountOwner();
		int passwd = account.getPasswd();
		long restMoney;
		long borrowMoney;
		if (account instanceof MinusAccount) {
			restMoney = ((MinusAccount) account).getOriginRestMoney();
			borrowMoney = ((MinusAccount) account).getBorrowMoney();
			System.out.println(borrowMoney);
			
		} else {
			restMoney=account.getRestMoney();
			borrowMoney = -1L;
		}

		// 14자리 고정값
		for (int i = 0; i < (ACCOUNTNUM_LENGTH / 2); i++) {
			file.writeChar(accountNum.charAt(i));
		}

		// 최대 10바이트(5글자)로 이름 저장
		int charCount = accountOwner.length();
		for (int i = 0; i < (ACCOUNTOWNER_LENGTH / 2); i++) {
			file.writeChar((i < charCount ? accountOwner.charAt(i) : ' '));

		}
		// 비밀번호 저장(4바이트)
		file.writeInt(passwd);
		// 예금액 저장(8바이트)
		file.writeLong(restMoney);
		// 대출액 저장(8바이트)
		file.writeLong(borrowMoney);
		
		file.seek(0);
		file.writeInt(++recordCount);

	}

	/**
	 * accounts에 추가된 계좌를 반환하는 메서드
	 * 
	 * @return
	 * @throws IOException
	 */
	public List<Account> list() throws IOException {
		List<Account> list = new ArrayList<Account>();
		for (int i = 0; i < recordCount; i++) {
			Account account = read(i);
			System.out.println(i);
			list.add(account);
		}
		return list;
	}

	/**
	 * 특정 레코드의 정보를 Account 로 반환
	 * 
	 * @throws IOException
	 */
	private Account read(int index) throws IOException {
		Account account = null;
		file.seek((index * RECORD_LENGTH) + RECORD_COUNT_LENGTH);
		System.out.println(file.getFilePointer());
		String accountNum = "";
		for (int i = 0; i < (ACCOUNTNUM_LENGTH / 2); i++) {
			accountNum += file.readChar();
		}
		String accountOwner = "";
		for (int i = 0; i < (ACCOUNTOWNER_LENGTH / 2); i++) {
			accountOwner += file.readChar();
		}
		accountOwner = accountOwner.trim();

		int passwd = 0;
		passwd = file.readInt();
		long restMoney = 0;
		restMoney = file.readLong();
		long borrowMoney = 0;
		borrowMoney = file.readLong();

		if (borrowMoney == -1) {
			account = new Account(accountNum, accountOwner, passwd, restMoney);
		}else {
			account = new MinusAccount(accountNum, accountOwner, passwd, restMoney, borrowMoney);
		}
		return account;
	}

	/**
	 * 파라미터(계좌번호)에 맞는 계좌를 반환하는 메서드
	 * 
	 * @param accountNum
	 * @return
	 * @throws IOException
	 */
	public Account get(String accountNum) throws IOException {
		Account account = null;
		for (int i = 0; i < recordCount; i++) {
			String findAccountNum = "";
			file.seek((i * RECORD_LENGTH) + RECORD_COUNT_LENGTH);
			for (int j = 0; j < (ACCOUNTNUM_LENGTH / 2); j++) {
				findAccountNum += file.readChar();
			}
			if (findAccountNum.equals(accountNum)) {
				account = read(i);
			}

		}
		file.seek(0);
		return account;
	}

	/**
	 * 이름에 맞는 계좌를 출력하는 메서드
	 * 
	 * @param accountOwner
	 * @return
	 * @throws IOException
	 */
	public List<Account> search(String accountOwner) throws IOException {
		List<Account> nameAccount = new ArrayList<Account>();
		Account account = null;
		for (int i = 0; i < recordCount; i++) {
			String findAccountOwner = "";
			file.seek((i * RECORD_LENGTH) + RECORD_COUNT_LENGTH + ACCOUNTNUM_LENGTH);
			for (int j = 0; j < (ACCOUNTOWNER_LENGTH / 2); j++) {
				findAccountOwner += file.readChar();
			}
			findAccountOwner = findAccountOwner.trim();
			if(findAccountOwner.equals(accountOwner)) {
				account = read(i);
				nameAccount.add(account);
			}
		}
		return nameAccount;
	}

	/**
	 * 파라미터를 통해 받은 계좌번호에 해당하는 계좌를 파기하는 메서드
	 * 
	 * @param accountNum
	 * @return
	 * @throws IOException 
	 */
	public boolean remove(String accountNum) throws IOException {
		boolean removeCheck = false;
		
		/** 현재 존재하는 계좌 수 */
		for (int i = 0; i <recordCount; i++) {
			
			String findAccountNum = "";
			/**각 계좌의 첫부분으로*/
			file.seek((i * RECORD_LENGTH) + RECORD_COUNT_LENGTH);
			//계좌받기
			for (int j = 0; j < (ACCOUNTNUM_LENGTH / 2); j++) {
				findAccountNum += file.readChar();
			}
			
			/**그i가 마지막 계좌이면 */
			if(i==(recordCount-1)){
				file.seek(((i+1) * RECORD_LENGTH) + RECORD_COUNT_LENGTH);
				for (int j = 0; j < (ACCOUNTNUM_LENGTH / 2); j++) {
					file.writeChar(' ');
				}

				// 최대 10바이트(5글자)로 이름 저장
				for (int j = 0; j < (ACCOUNTOWNER_LENGTH / 2); j++) {
					file.writeChar(' ');

				}
				// 비밀번호 저장(4바이트)
				file.writeInt(0);
				// 예금액 저장(8바이트)
				file.writeLong(0);
				// 대출액 저장(8바이트)
				file.writeLong(0);
				removeCheck = false;
			}
			
			//계좌가 파라미터와 같으면
			if (findAccountNum.equals(accountNum)) {
				//다음 번 계좌로 가서
				file.seek(((i+1) * RECORD_LENGTH) + RECORD_COUNT_LENGTH);
				//계좌번호 받고
				String nextaccountNum = "";
				for (int j = 0; j < (ACCOUNTNUM_LENGTH / 2); j++) {
					nextaccountNum += file.readChar();
				}
				//이름받고
				String accountOwner = "";
				for (int j = 0; j < (ACCOUNTOWNER_LENGTH / 2); j++) {
					accountOwner += file.readChar();
				}
				accountOwner = accountOwner.trim();
				//비밀번호 받고
				int passwd = 0;
				passwd = file.readInt();
				//남은돈 받고
				long restMoney = 0;
				restMoney = file.readLong();
				//대출액 받고
				long borrowMoney = 0;
				borrowMoney = file.readLong();
				//원래 계좌로가서
				file.seek((i * RECORD_LENGTH) + RECORD_COUNT_LENGTH);
				System.out.println("del"+file.getFilePointer());
				for (int j = 0; j < (ACCOUNTNUM_LENGTH / 2); j++) {
					file.writeChar(nextaccountNum.charAt(j));
				}

				// 최대 10바이트(5글자)로 이름 저장
				int charCount = accountOwner.length();
				for (int j = 0; j < (ACCOUNTOWNER_LENGTH / 2); j++) {
					file.writeChar((i < charCount ? accountOwner.charAt(j) : ' '));
					accountOwner = accountOwner.trim();
				}
				// 비밀번호 저장(4바이트)
				file.writeInt(passwd);
				// 예금액 저장(8바이트)
				file.writeLong(restMoney);
				// 대출액 저장(8바이트)
				file.writeLong(borrowMoney);
			}

		}
		file.seek(0);
		file.writeInt(--recordCount);
		System.out.println("recordCount"+recordCount);
		removeCheck = true;
		return removeCheck;
	}
	
	
	// 스트림 닫기
	public void close() {
		if (file != null)
			try {
				file.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
}
