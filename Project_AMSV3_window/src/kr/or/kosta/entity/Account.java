package kr.or.kosta.entity;

import java.util.Formatter;

/**
 * 예금 출금 기능을 가진 은행 계좌 객체
 * 
 * @author 권현우
 *
 */
public class Account {
	/** static 클래스 변수 */
	public final static String bankName = "AMS - Main"; // Account.bankName

	/** 인스턴스 변수 선언 */
	/** 계좌번호 */
	private String accountNum;
	/** 예금주명 */
	private String accountOwner;
	/** 비밀번호 */
	private int passwd;
	/** 잔액 */
	private long restMoney;

	/** 생성자 */
	/** default */
	public Account() {
		this(null, null);
	}

	/** 계좌번호 초기화 */
	public Account(String accountNum) {
		this.accountNum = accountNum;

	}

	/** 계좌번호, 예금주명 초기화 */
	public Account(String accountNum, String accountOwner) {
		this(accountNum, accountOwner, 100, 1000);
	}

	/** 계좌번호, 예금주명, 암호, 잔액 초기ㅗ하 */
	public Account(String accountNum, String accountOwner, int passwd, long restMoney) {
		this.accountNum = accountNum;
		this.accountOwner = accountOwner;
		this.passwd = passwd;
		this.restMoney = restMoney;
	}

	/** 변수 접근 setter/getter 메서드 */
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}

	public String getAccountNum() {
		return accountNum;
	}

	public void setAccountOwner(String accountOwner) {
		this.accountOwner = accountOwner;
	}

	public String getAccountOwner() {
		return accountOwner;
	}

	public void setPasswd(int passwd) {
		this.passwd = passwd;
	}

	public int getPasswd() {
		return passwd;
	}

	public void setRestMoney(long restMoney) {
		this.restMoney = restMoney;
	}

	public long getRestMoney() {
		return restMoney;
	}

	/** 기능 */

	/**
	 * 계좌예금 기능
	 * 
	 * @param money 입금액
	 * @return 계좌 예금액
	 * @throws AccountException param 값이 음수일 경우 에러코드를 보여주는 예외발생
	 */
	public long deposit(long money) throws AccountException {
		if (money <= 0) {
			throw new AccountException("입금하고자 하는 금액은 음수일 수 없습니다.", -1);
		}
		restMoney += money;
		return restMoney;
	}// end deposit()

	/**
	 * 계좌출금기능
	 * 
	 * @param money 출금액
	 * @return 계좌 예금액
	 * @throws AccountException param값이 음수일 경우, restMoney보다 클경우 예외처리
	 */
	public long withdraw(long money) throws AccountException {
		if (money < 0) {
			throw new AccountException("출금하고자하는 금액은 음수일 수 없습니다.", -2);
		}
		if (money > restMoney) {
			throw new AccountException("잔액이 부족합니다.", -3);
		}
		restMoney -= money;
		return restMoney;
	}// end withdraw()

	/**
	 * password 체크기능
	 * 
	 * @param passwd 확인할 계좌의 비밀번호
	 * @return 맞을경우 true 값 반환
	 */
	public boolean checkPasswd(int passwd) {
		return this.passwd == passwd;
	}// end checkPasswd

	/*
	 * 계좌정보 문자열로 제공
	 * 
	 */
	public String toString() {
		String Account = new Formatter().format("    %-16s%-22s", "입출금", getAccountNum()).toString();
		if (getAccountOwner().length() == 3)
			Account += new Formatter()
					.format("      %6s                      %,-17d", getAccountOwner(), getRestMoney()).toString();
		else {
			Account += new Formatter()
					.format("      %4s                      %,-17d", getAccountOwner(), getRestMoney()).toString();
		}
		;
		return Account;
	}// end toString()

	/*
	 * Account 객체가 맞는지 확인하는 기능
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		boolean eq = false;
		if (obj instanceof Account) {
			eq = toString().equals(obj.toString());
		}
		return eq;
	}// end equals()
}// end Account
