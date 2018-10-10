package kr.or.kosta.entity;

import java.util.Comparator;

/**계좌를 잔여액에 따라 내림차순으로 정렬하도록 하는 클래스
 * @author 권현우
 *
 */
public class MoneyCompare implements Comparator<Account>{

	@Override
	public int compare(Account o1, Account o2) {
	return (int)(o2.getRestMoney()-o1.getRestMoney());
	}
}
