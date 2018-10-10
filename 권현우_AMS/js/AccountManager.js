/**
 * Map을 이용한 은행계좌 관리
 * 
 * @author 권현우
 * 
 */
/** 
 * Constructor AccountManager Object 생성 
 * param 
 * account 객체를 담은 배열 생성
 * */
function AccountManager() {
	this.accounts = new Array();
}



/**function Array에 계좌 추가 
 * param  Object type의 addAccount
 * return Array에 존재하는 객체 수 반환 
 * 
 * */
AccountManager.prototype.add = function(addAccount) {
	
	var flag = true;
	for ( var i in this.accounts) {
		if (addAccount.equals(this.accounts[i].accountNum)) {
			flag = false;
		}
	}
	if (!flag) {
		console.log('중복');
		return false;
	}
	this.accounts.push(addAccount);
	/** 계좌 갯수확인 */
	return this.accounts.length;
}

/**function Account 객체를 담은 Array 반환 
 * param  
 * return Array type의 accounts 반환
 * */
AccountManager.prototype.list = function() {
	/**잔여액 기준으로 오름차순 정렬*/
	this.accounts.sort(function(accountOne, accountTwo){
		return accountTwo.restMoney - accountOne.restMoney;
	});
	return this.accounts;
};

/**function 계좌번호로 계좌 검색
 * param  string type의 accountNum
 * return accountNum과 같은 accountNum을 가진 account 객체 반환 
 * */
AccountManager.prototype.get = function(accountNum) {
	var searchAccount = new Array();
	for ( var i in this.accounts) {
		if (accountNum == this.accounts[i].accountNum) {
			searchAccount.push(this.accounts[i]);
			
		}
	}
	return searchAccount;
}

/**function 이름으로 계좌 검색 
 * param  string type의 accountOwner
 * return accountOwner과 같은 accountOwner를 가진 account 객체를 담은 nameArray 반환  
 * */
AccountManager.prototype.search = function(accountOwner) {
	var nameArray = new Array();
	for ( var i in this.accounts) {
		if (accountOwner == this.accounts[i].accountOwner) {
			nameArray.push(this.accounts[i]);
		}
	}
	return nameArray;
}

/**function 계좌번호로 계좌 제거 
 * param  string type의 accountNum
 * return 제거 성공시 true 반환   
 * */
AccountManager.prototype.remove = function(accountNum) {
	var flag = false;
	for ( var i in this.accounts) {
		if (accountNum == this.accounts[i].accountNum) {
			for (i; i < this.accounts.length - 1; i++) {
				this.accounts[i] = this.accounts[parseInt(i) + 1];
				console.log(this.accounts[i]);
				//console.log(this.accounts[parseInt(i)+1]);
			}
			flag = true;
			this.accounts.pop();
		}

	}
	return flag;
}
