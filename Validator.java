package kr.or.kosta.boundary;

/**
 * 입력 데이터의 유효성을 검사하는 클래스
 * 
 * @author 권현우
 *
 */
public class Validator {
	public Validator() {
		// TODO Auto-generated constructor stub
	}

	/** 입력값이 공백없는 N자리의 정수인가 */
	public boolean numberTest(int length, String testNum) {
		String pattern;
		if (length == 0) {
			pattern = "^[0-9]*$";
		} else {
			pattern = "^[0-9]{" + length + "}$";
		}
		return testNum.matches(pattern);
	}

	/** 입력값이 공백과 숫자를 제외한 문자인가 */
	public boolean charTest(int length, String testChar) {
		String pattern = null;
		if (length == 0) {
			pattern = "^[^0-9]*$";
		} else {
			pattern = "^[^0-9]{" + length + "}$";
		}
		return testChar.matches(pattern);
	}
	
/**test를 위한 메인 메서드 */
/*	public static void main(String[] args) {
		Validator avt = new Validator();
		System.out.println(avt.numberTest(0, "0"));
		System.out.println(avt.charTest(0, "권현우"));

	}*/
}
