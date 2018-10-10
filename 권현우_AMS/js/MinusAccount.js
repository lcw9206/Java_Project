/**
 * Account를 확장한 minusAccount
 * 
 * @author 권현우
 */

/** 
 * Constructor MinusAccount Object 생성 
 * param number type의 passwd,restMoney,borrowMoney
 * 		 string type의 accountNum,accountOwner
 * */
function MinusAccount(accountType,accountNum, accountOwner, passwd, restMoney, borrowMoney) {
	/**부모 객체를 call 메서드를 통해 호출
	 * 부모객체로 매개변수 지정*/
	Account.call(this, accountType,accountNum, accountOwner, passwd, restMoney);
	/**borrowMoney는 MinusAccount의 고유 변수*/
	this.borrowMoney = borrowMoney;
}

/**MinusAccont의 prototype에 부모객체의 인스터스 복사*/
MinusAccount.prototype = new Account();

/**prototype의 불필요한 요소 제거*/
delete MinusAccount.prototype.accountType;
delete MinusAccount.prototype.accountNum;
delete MinusAccount.prototype.accountOwner;
delete MinusAccount.prototype.passwd;
delete MinusAccount.prototype.restMoney;

/**constructor 참조 객체 재정의*/
MinusAccount.prototype.constructor= MinusAccount;

/** Account의 toString 메서드 재정의 */
MinusAccount.prototype.toString = function() {
	var impArray = [ this.accountType, this.accountNum, this.accountOwner, this.restMoney, this.borrowMoney ]
	/**'   '를 구분자로 join*/
	var result = impArray.join('***');
	return result;
}

/**Account의 equals 메서드 재정의*//*
MinusAccount.prototype.equals= function(obj){
	var flag = (obj === this);
	return flag;
}*/


