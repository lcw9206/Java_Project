package kr.or.kosta.entity;

import java.util.Formatter;

/**
 * Account를 확장한 minusAccount
 * 
 * @author 권현우
 *
 */
public class MinusAccount extends Account {
	/** 대출금 */
	private long borrowMoney;

	/** default 생성자 */
	public MinusAccount() {
		this("111-222-333-444", "권현우", 0, 0, 0);
	}

	/** 상속변수와 확장된 변수를 포함하는 생성자 */
	public MinusAccount(String accountNum, String accountOwner, int passwd, long restMoney, long borrowMoney) {
		super(accountNum, accountOwner, passwd, restMoney);
		this.borrowMoney = borrowMoney;
	}

	/** setter/getter */
	public long getBorrowMoney() {
		return borrowMoney;
	}

	public void setBorrowMoney(long borrowMoney) {
		this.borrowMoney = borrowMoney;
	}

	/** 대출액을 반영한 계좌잔여금 */
	@Override
	public long getRestMoney() {
		// TODO Auto-generated method stub
		//long sum = getRestMoney() - getBorrowMoney();
		//System.out.println(sum);
		long borrowMoney = getBorrowMoney() * -1;
		long restMoney = super.getRestMoney();
		return restMoney + borrowMoney;
	}
	
	public long getOriginRestMoney() {
		return super.getRestMoney();
	}

	/** 계좌정보를 문자열로 반환하는 메서드 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String minusAccount = new Formatter().format("    %-14s%-22s", "마이너스", getAccountNum()).toString();
		/** 예금주명이 3글자 */
		if (getAccountOwner().length() == 3)
			minusAccount += new Formatter()
					.format("      %6s                     %,-17d", getAccountOwner(), getRestMoney()).toString();
		/** 예금주명이 3글자 이상 */
		else {
			minusAccount += new Formatter()
					.format("      %4s                      %,-17d", getAccountOwner(), getRestMoney()).toString();
		}
		;
		/** 잔여액 절대값이 1,000,000 이하 */
		if (Math.abs(getRestMoney()) < 1000000) {
			minusAccount += new Formatter().format("    %,4d", getBorrowMoney()).toString();
			/** 잔여액 절대값이 100,000,000이상 */
		} else if (Math.abs(getRestMoney()) >= 100000000) {
			minusAccount += new Formatter().format("    %,2d", getBorrowMoney()).toString();
			/** 이외의 잔여액 */
		} else {
			minusAccount += new Formatter().format("     %,3d", getBorrowMoney()).toString();
		}
		return minusAccount;
	}// end toString

}// end MinusAccount
