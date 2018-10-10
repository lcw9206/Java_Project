/**
 * 예금 출금 기능을 가진 은행 계좌 객체
 * 
 * author 권현우
 */

/** 
 * Constructor Account Object 생성 
 * param number type의 passwd,restMoney
 * 		 string type의 accountNum,accountOwner
 * */
function Account(accountType,accountNum, accountOwner, passwd, restMoney) {
	this.accountType = accountType;
	this.accountNum = accountNum;
	this.accountOwner = accountOwner;
	this.passwd = passwd;
	this.restMoney = restMoney;
}

/*Account.prototype.getAccountNum = function(){
	return _accountNum;
}*/

/** 공용변수 */
/**은행 명*/
Account.BANKNAME = "AMS - Main";

/**function -입금 기능 
 * param  Number type의 money
 * return restMoney + money
 * 
 * */
Account.prototype.deposit = function(money) {
	if (money <= 0) {
		alert('입금하고자 하는 금액은 음수일 수 없습니다, -1')
		return false;
	}
	this.restMoney += money;
	return this.restMoney;
}

/** function - 출금 기능
 * param  Number type의 money
 * return restMoney - money
 * */
Account.prototype.withdraw = function(money) {
	if (money <= 0) {
		alert('출금하고자하는 금액은 음수일 수 없습니다. , -2')
	return false;
	}
	if(money > this.restMoney){
		//alert('잔액이 부족합니다., -3');
	return false;
	}
	this.restMoney -= money;
	return this.restMoney
}

/**function  Password 확인
 * param - Number type의 passwd
 * return boolean
 * */
Account.prototype.chekPasswd = function(passwd){
	return this.passwd == passwd;
}

/**function 계좌정보 확인 
 * param
 * return String type의 계좌정보를 담은 문자열
 * */
Account.prototype.toString = function(){
		var impArray = [this.accountType,this.accountNum,this.accountOwner,this.restMoney]
		var result = impArray.join('***');
		return result;
	}

/**function  중복계좌 유무 확인
 * param  String type의 accountNum
 * return boolean type 의 flag 변수
 * */
Account.prototype.equals= function(accountNum){
	var flag = (accountNum == this.accountNum);
	return flag;
}
