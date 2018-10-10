package kr.or.kosta.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;
import java.util.Vector;

/**
 * Map을 이용한 은행계좌 관리
 *
 * @author 권현우
 *
 */
public class AccountManager {
	/** 계좌번호를 key값으로 하고 Account를 참조하는 객체를 value로 하는 Map */
	private Hashtable<String, Account> accounts;

	/**
	 * 추가된 계좌 수를 담고있는 변수 Vector 타입사용으로 폐기 private int count = 0;
	 */
	/** 계좌를 담을 Map의 크기를 설정하는 default 생성자 */
	public AccountManager() {
		// TODO Auto-generated constructor stub
		accounts = new Hashtable<String, Account>(50);
	}// end AccountManager()

	/** 계좌를 담을 Map의 크기를 설정하는 생성자 */
	public AccountManager(int total) {
		// TODO Auto-generated constructor stub
		accounts = new Hashtable<String, Account>(total, 5);
	}// end AccountManager(total)

	/** getter/setter */
	public Hashtable<String, Account> getAccount() {
		return accounts;
	}// end getAccount()

	public void setAccount(Hashtable<String, Account> account) {
		this.accounts = account;
	}// end setAccount()

	/**
	 * 입력받은 정보를 담은 계좌를 추가하는 메서드
	 * 
	 * @param addAccount 추가할 account 객채
	 * @throws AccountException 동일계좌가 존재할 시 예외처리
	 */
	public void add(Account addAccount) throws AccountException {
		if (accounts.containsKey(addAccount.getAccountNum()))
			throw new AccountException("동일한 계좌가 존재합니다", -200);
		accounts.put(addAccount.getAccountNum(), addAccount);
	}// end add()

	/**
	 * accounts에 추가된 계좌를 반환하는 메서드
	 * 
	 * @return 추가된 계좌를 담은 List반환
	 */
	public List<Account> list() {
		List<Account> list = new ArrayList<Account>(accounts.size());
		Enumeration<Account> enu = accounts.elements();
		while (enu.hasMoreElements()) {
			list.add(enu.nextElement());
		}
		/** 잔여 예금액에 따라 오름차순 정렬 */
		Collections.sort(list, new MoneyCompare());
		return list;
	}// end list()

	/**
	 * 파라미터(계좌번호)에 맞는 계좌를 반환하는 메서드
	 * 
	 * @param accountNum 검색계좌번호
	 * 
	 * @return 계좌번호에 맞는 계좌객체 반환
	 */
	public Account get(String accountNum) {
		/** 반환할 계좌 객체를 담는 변수 */
		return accounts.get(accountNum);
	}// end get()

	/**
	 * 이름에 맞는 계좌를 출력하는 메서드
	 * 
	 * @param accountOwner 출력하려는 계좌의 주인
	 * @return 이름에 해당하는 모든 계좌 객체를 담은 List 출력
	 */
	public List<Account> search(String accountOwner) {
		/** 반환할 Account 객체를 담는 Account 타입 */
		List<Account> nameAccount = new ArrayList<Account>();
		Collection<Account> enu = accounts.values();
		for (Account account : enu) {
			if (account.getAccountOwner().equals(accountOwner))
				nameAccount.add(account);
		}
		return nameAccount;
	}// end search()

	/**
	 * 파라미터를 통해 받은 계좌번호에 해당하는 계좌를 파기하는 메서드
	 * 
	 * @param accountNum 파기하려는 계좌의 계좌번호
	 * @return 파기 여부를 boolean값으로 리턴
	 */
	public boolean remove(String accountNum) {
		/** 파기여부를 확인할 boolean 타입 변수 */
		return accounts.remove(accountNum) != null;
	}// end remove()

}
