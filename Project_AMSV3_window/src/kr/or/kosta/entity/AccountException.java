package kr.or.kosta.entity;

/**
 * 계좌 처리중 에러상황을 알리는 예외처리
 * 
 * @author 권현우
 *
 */
public class AccountException extends Exception {
	// String message;
	/** 오류 코드 */
	private int errorCode;

	/** getter */
	public int getErrorCode() {
		return errorCode;
	}// end getErrorCode()

	/** default생성자 */
	public AccountException() {
		// TODO Auto-generated constructor stub
		this("계좌처리중 예기치 않은 문제가 발생했습니다.", -9);
	}// end AccountException()

	/** message를 사용자가 지정하는 메세지로 지정하는 생성자 */
	public AccountException(String message, int errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	/*
	 * (non-Javadoc) 예외 포멧 메서드
	 */
	@Override
	public String toString() {
		return "AccountException [errorCode=" + errorCode + ", getMessage()=" + getMessage() + "]";
		// 고객이 원하는 포맷으로 변경할수 있음
	}

}
