/**
 *AMS의 기본기능
 */
function init() {
		
		/**전역변수*/
		/**생성된 계좌를 담아 관리하는 객체*/
		ams.accounts = new AccountManager();
		/**상황별로 검색된 계좌를 담을 Array*/
		ams.find_Accounts = new Array();
		/**wrapper*/
		ams.wrapper = document.querySelector('.wrapper');
		/**header*/
		ams.header = ams.wrapper.childNodes[1];
		/**body*/
		ams.body = ams.wrapper.childNodes[7];
		/**계좌등록Btn*/
		ams.regist_accout_btn = ams.header.childNodes[5].childNodes[1];
		/**전체조회 Btn*/
		ams.view_All_btn = ams.header.childNodes[5].childNodes[3];
		/**계좌번호로 조회 Btn*/
		ams.search_accountNum_btn = document.getElementById("button_account").childNodes[1];
		/**계좌 삭제 Btn*/
		ams.remove_accountNum_btn = document.getElementById("button_account").childNodes[3];
		/**이름으로 검색 Btn*/
		ams.find_Name_btn = document.getElementById("button_name").childNodes[1];
		/**계좌번호 유효성 검사 레이블*/
		ams.accountNum_validator = ams.remove_accountNum_btn.nextSibling.nextSibling;
		/**예금주명 유효성 검사 레이블*/
		ams.owner_validator = ams.find_Name_btn.nextSibling.nextSibling;
		/**비밀번호 유효성 검사 레이블*/
		ams.passwd_validator = document.getElementById('pass_label').childNodes[3];
		/**예치금액 유효성 검사 레이블*/
		ams.deposit_validator = document.getElementById('deposit_label').childNodes[3];
		/**대출금액 유효성 검사 레이블*/
		ams.borrow_validator = document.getElementById('borrow_label').childNodes[3];
		/**계좌목록 list영역*/
		ams.accountlist_body = document.getElementById('accountlist-body');
		/**입력 form 항목 리스트*/
		ams.form_list = ams.body.childNodes[1].childNodes[1];
		/**계좌정보 입력 리스트*/
		ams.list = ams.form_list.childNodes;
		/**계좌종류 선택공간*/
		ams.accountTyleFiled = ams.list[1].lastElementChild;
		ams.accountTyleFiled.focus();
		/**계좌번호 입력공간*/
		ams.accountNumFiled = ams.list[3].lastElementChild;
		/**예금주 입력공간*/
		ams.ownerFiled = ams.list[5].lastElementChild;
		/**비밀번호 입력공간*/
		ams.passFiled = ams.list[7].lastElementChild;
		/**예치금액 입력공간*/
		ams.depositFiled = ams.list[9].lastElementChild;
		/**대출금액 입력공간*/
		ams.borrowFiled = ams.list[11].lastElementChild;
		/**상단오류알림창*/
		ams.alertFlame = document.getElementById('popUp');
		/**상단오류알림창*/
		ams.alertFlameD = document.getElementById('popUpBotton');
		
		/**textField 유효성 검사 결과 변수*/
		ams.accountNumValidator = true;
		ams.ownerValidator = true;
		ams.passwdValidator = true;
		ams.depositValidator = true;
		ams.borrowValidator = true;
		
		/**테스트용 account개체*/
		var account = new Account("입출금","1111-1111-1111", "박지성", 1111, 50000);
		var account2 = new Account("입출금","1111-1111-2222", "박태환", 2222, 40000);
		var account3 = new Account("입출금","1111-1111-3333", "권현우", 3333, 30000);
		var mAccount = new MinusAccount("마이너스","1111-2222-1111", "김용현", 4444, 30000,
				30000);
		var mAccount2 = new MinusAccount("마이너스","1111-2222-2222", "이철우", 5555, 20000,
				30000);
		var mAccount3 = new MinusAccount("마이너스","1111-2222-3333", "박찬호", 6666, 10000,
				30000);
		var mAccount4 = new MinusAccount("마이너스","1111-2222-4444", "서인국", 6666, 10000,
				30000);
		/**테스트 개체 추가*/
 		ams.accounts.add(account);
		ams.accounts.add(account2);
		ams.accounts.add(account3);
		ams.accounts.add(mAccount);
		ams.accounts.add(mAccount2);
		ams.accounts.add(mAccount3); 
	}

	function eventRegist() {
		/**전체 계좌 리스트 출력*/ 
		ams.view_All_btn.onclick = inquairedTypeView;
		/**계좌번호로 검색*/
		ams.search_accountNum_btn.onclick = inquairedAccount;
		/**계좌삭제*/
		ams.remove_accountNum_btn.onclick = removeAccount;
		/**이름으로 검색*/
		ams.find_Name_btn.onclick = searchAccount;
		/**계좌등록*/
		ams.regist_accout_btn.onclick = registAccount;
		
		/**textField 가 focus를 얻었을시*/
		ams.accountNumFiled.onfocus = textFieldOnFocus;
		ams.ownerFiled.onfocus = textFieldOnFocus;
		ams.passFiled.onfocus = textFieldOnFocus;
		ams.depositFiled.onfocus = textFieldOnFocus;
		ams.borrowFiled.onfocus = textFieldOnFocus;
		
		/**계좌번호 입력란 focusout*/
		ams.accountTyleFiled.onchange = selectFieldOutFocus;
		ams.accountNumFiled.onchange = textFieldOutFocus;
		ams.ownerFiled.onchange = textFieldOutFocus;
		ams.passFiled.onchange = textFieldOutFocus;
		ams.depositFiled.onchange = textFieldOutFocus;
		ams.borrowFiled.onchange = textFieldOutFocus;
		
		/**textField에서 enter 입력되었을시*/
		ams.accountNumFiled.onkeyup = textFieldEnter;
		ams.ownerFiled.onkeyup = textFieldEnter;
		ams.passFiled.onkeyup = textFieldEnter;
		ams.depositFiled.onkeyup = textFieldEnter;
		ams.borrowFiled.onkeyup = textFieldEnter;
	}
	
	//**텍스트 필드가 포커스되었을때 함수*/
	function textFieldOnFocus(){
		this.value=''
		textFieldAlertOff(this);
	}
	
	/** textField 초기화 함수*/
	function resetFeild(){
		ams.list[1].lastElementChild.value = '입출금';
		for (var i=2; i<ams.list.length; i++) {
			if(ams.list[i].constructor == Text)
				continue;
			ams.list[i].lastElementChild.value = '';
			ams.list[i].lastElementChild.style.borderColor="#ccc";
			ams.list[i].lastElementChild.style.borderWidth="thin";
		}
		ams.list[9].lastElementChild.value = '0';
		ams.list[11].lastElementChild.value = '0';
		
	}
	/**textField에서 enter입력되었을때 */
	function textFieldEnter(){
		var event = window.event;
	if(event.keyCode == 13){
		if(this == ams.accountNumFiled){
			ams.ownerFiled.focus();
		}else if(this == ams.ownerFiled ){
			ams.passFiled.focus();
		}else if(this == ams.passFiled){
			ams.depositFiled.focus();
		}else if(this == ams.depositFiled){
			if(ams.borrowFiled.getAttribute('disabled') == 'disabled'){
				ams.regist_accout_btn.focus();
			}
			ams.borrowFiled.focus();
		}else if(this == ams.borrowFiled ){
			ams.regist_accout_btn.focus();
		}
	}
	}
	
	/**textField를  알맞는 형식으로 바꾸어주는 함수*/
	function textFieldOutFocus(){
		validator(this);
	}
	/**selecField를 알맞는 형식으로 바꾸어주는 함수*/
	function selectFieldOutFocus(){
		if(this.value == '입출금'){
			textFieldCollerctAlertOn(this);
			ams.borrowFiled.setAttribute('disabled', 'disabled');
		}else if(this.value='마이너스'){
			textFieldCollerctAlertOn(this);
			ams.borrowFiled.removeAttribute('disabled');
		}
		ams.accountNumFiled.focus();

	}
	
	/**textField 유효성 검사*/
	function validator(textField){
		switch(textField){
		/**계좌번호 입력랸*/
		case ams.accountNumFiled :
			if(checkSpace(ams.accountNumFiled.value) || checkChar(ams.accountNumFiled.value) || checkSpaciel(ams.accountNumFiled.value) || checkKor(ams.accountNumFiled.value) || ams.accountNumFiled.value.length !=12 ){	
			console.log(ams.accountNumFiled.value);
				textFieldAlertOn(ams.accountNumFiled);
				this.value = this.value;
				ams.accountNumValidator=false;
			}else{
				var transedAccountNum = accountNumTransform(ams.accountNumFiled.value);
				textFieldCollerctAlertOn(ams.accountNumFiled);
				ams.accountNumFiled.value = transedAccountNum;
				ams.accountNumValidator=true;
			}	
				break;
		/**예금주 입력랸*/	
		case ams.ownerFiled:
			if(checkSpace(ams.ownerFiled.value) || checkSpaciel(ams.ownerFiled.value) || checkNum(ams.ownerFiled.value) ){
				textFieldAlertOn(ams.ownerFiled);
				ams.ownerValidator=false;
			}else{
				textFieldCollerctAlertOn(ams.ownerFiled);
				ams.ownerValidator=true;	
			}
			break;
		/**암호 입력랸*/			
		case ams.passFiled:
			if(checkSpace(ams.passFiled.value) || checkSpaciel(ams.passFiled.value) || checkChar(ams.passFiled.value) || checkKor(ams.passFiled.value) || ams.passFiled.value.length != 4){
				textFieldAlertOn(ams.passFiled);
				ams.passwdValidator=false;
			}else{
				textFieldCollerctAlertOn(ams.passFiled);
				ams.passwdValidator=true;	
			}
			break;
		/**예금액 입력랸*/		
		case ams.depositFiled:
			if(checkSpace(ams.depositFiled.value) || checkSpaciel(ams.depositFiled.value) || checkChar(ams.depositFiled.value) || checkKor(ams.depositFiled.value)){
				textFieldAlertOn(ams.depositFiled);
				ams.depositValidator=false;
			}else{
				textFieldCollerctAlertOn(ams.depositFiled);
				ams.depositFiled.value = moneyTransform(ams.depositFiled.value);
				ams.depositValidator=true;	
			}
			break;
		/**대출액 입력랸*/			
		case ams.borrowFiled:
			if(checkSpace(ams.borrowFiled.value) || checkSpaciel(ams.borrowFiled.value) || checkChar(ams.borrowFiled.value) || checkKor(ams.borrowFiled.value)){
				textFieldAlertOn(ams.borrowFiled);
				ams.borrowValidator=false;
			}else{
				textFieldCollerctAlertOn(ams.borrowFiled);
				ams.borrowFiled.value = moneyTransform(ams.borrowFiled.value);
				ams.borrowValidator=true;
			}	
			break;
		}
	}
	/**계좌등록완료 후 유효성검사*/
	function createAccountValidator(){
		for (var i=0; i<ams.list.length; i++) {
			if(ams.list[i].constructor == Text)
				continue;
			if(checkNull(ams.list[i].lastElementChild.value)){
				return false;
			};
		}
		return true;
	}
	
	/**textField & 유효성검사 lable 경고켜기
	* 유효성검사 위반시 태두리와 경고레이블 빨간색으로
	*/
	function textFieldAlertOn(Field){
		var nodes = Field.previousSibling.previousSibling.childNodes;
			for (var i = 0; i < nodes.length; i++) {
				if(nodes[i].nodeType == 1){
					node = nodes[i]
				}
			}
		node.style.color="red";
		Field.style.borderColor="red";
		Field.style.borderWidth="thick";
	}
	
	/**textField & 유효성검사 lable 일치등 켜기
	* 유효성 통과시 태두리와 경고레이블 초록색으로
	*/
	function textFieldCollerctAlertOn(Field){
		Field.style.borderColor="#006666";
		Field.style.borderWidth="thick";
	}
	
	/**textField & 유효성검사 후
	필드 수정시(textField enter)
	lable,테두리 경고끄기*/
	function textFieldAlertOff(textField){
		var nodes = textField.previousSibling.previousSibling.childNodes;
		for (var i = 0; i < nodes.length; i++) {
			if(nodes[i].nodeType == 1){
				node = nodes[i]
			}
		}
		node.style.color="black";
		textField.style.borderColor="#ccc";
		textField.style.borderWidth="thin";
	}
	
	/**지정된 타입(입출금,마이너스)에 맞는 계좌를 보여주는 함수*/
	function inquairedTypeView() {
		if(ams.accountTyleFiled.value =='입출금'){
			ams.find_Accounts = inquiredType('입출금');	
		}else{
			ams.find_Accounts = inquiredType('마이너스');	
		}
		removeList();
		appendList(ams.find_Accounts);
		if(ams.find_Accounts.length ==0){
			ams.alertFlameD.childNodes[1].innerHTML="계좌가 존재하지 않습니다.";
		}else{
			ams.alertFlameD.childNodes[1].innerHTML="조회되었습니다.";
		}
		ams.alertFlameD.style.display='block';
		setTimeout(function() {
			ams.alertFlameD.style.display='none';
			}, 2000)
	
		window.scrollTo(0, 700);	
	}
	
	/**전체 계좌를 종류에 따라 분류하는 함수*/
	function inquiredType(type){
		var Accounts = ams.accounts.list();
		var findAccounts = Array();
		for ( var i in Accounts) {
			if(Accounts[i].accountType == type){
				findAccounts.push(Accounts[i]);
			}
		}
		return findAccounts;
	}
	
	/**등록된 전체 계좌를 보여주는 함수*/
	function inquairedAll() {
		ams.find_Accounts = ams.accounts.list();
		removeList();
		appendList(ams.find_Accounts);
		window.scrollTo(0, 700);
	}

	/**계좌번호로 검색된 계좌를 보여주는 함수 */
	 function inquairedAccount() {
		 ams.accountNum = accountNumTransform(ams.accountNumFiled.value);
		 ams.find_Accounts = ams.accounts.get(ams.accountNum);
		 removeList();
	    resetFeild();
	    if(ams.find_Accounts.length ==0){
			ams.alertFlameD.childNodes[1].innerHTML="계좌가 존재하지 않습니다.";
		}else{
			ams.alertFlameD.childNodes[1].innerHTML="조회되었습니다.";
		}
		ams.alertFlameD.style.display='block';
		setTimeout(function() {
			ams.alertFlameD.style.display='none';
			}, 2000)
	    appendList(ams.find_Accounts);
	    window.scrollTo(0, 900);
	} 
	
	/**계좌번호를 통해 계좌를 삭제한후 리스트를 보여주는 함수*/
	function removeAccount(){
		/**계좌번호 입력값*/
		ams.accountNum = accountNumTransform(ams.accountNumFiled.value);
		ams.alertFlameD.style.display='block';
		setTimeout(function() {
			ams.alertFlameD.style.display='none';
			}, 2000)
	
		var flag = ams.accounts.remove(ams.accountNum);
		if(flag){
			ams.alertFlameD.childNodes[1].innerHTML="삭제되었습니다.";
		}else{
			ams.alertFlameD.childNodes[1].innerHTML="계좌가 존재하지 않습니다.";
		}
		resetFeild();
		inquairedAll();
	}
	
	/**이름을 통한 검색 결과를 보여주는 함수*/
	function searchAccount(){
		ams.ownerName = ams.ownerFiled.value;
		var nameArray = ams.accounts.search(ams.ownerName);
		ams.alertFlameD.style.display='block';
		setTimeout(function() {
			ams.alertFlameD.style.display='none';
			}, 2000)
		if(nameArray.length ==0){
			ams.alertFlameD.childNodes[1].innerHTML="계좌가 존재하지 않습니다.";
		}else{
			ams.alertFlameD.childNodes[1].innerHTML="검색되었습니다."
		}
		removeList();
		resetFeild();
		appendList(nameArray);
		window.scrollTo(0, 700);
	}
	
	/**계좌등록 함수
	*/
	function registAccount(){
		/**유효성 검사를 위한 변수 선언*/
		var validator = createAccountValidator();
		ams.createCheck = true;
		ams.allCheck = true; 
		/**모든 text항목들이 유효성검사 통과 할 시*/
		if(ams.allCheck = validator && ams.accountNumValidator && ams.ownerValidator && ams.passwdValidator && ams.depositValidator && ams.borrowValidator){
		var registArray = Array();
		for (var i=0; i<ams.list.length; i++) {
			if(ams.list[i].constructor == Text)
				continue;
			registArray.push(ams.list[i].lastElementChild.value);
		}
		/**입출금 계좌 생성*/
		if(registArray[0]=='입출금'){
			ams.createCheck = ams.accounts.add(new Account(registArray[0], accountNumTransform(registArray[1]), registArray[2], registArray[3], registArray[4]));
			var result = alertError(ams.createCheck);
			if(result){
				return;
			}
		/**마이너스 계좌 생성*/	
		}else{
			ams.createCheck = ams.accounts.add(new MinusAccount(registArray[0], accountNumTransform(registArray[1]), registArray[2], registArray[3], registArray[4],registArray[5]));
			var result = alertError(ams.createCheck);
			if(result){
				return;
			}
			
		}
		/**생성완료 메세지 띄워줌*/
		ams.checkComplete = false;
		resetFeild();
		inquairedAll();
		alertError(ams.checkComplete);
		console.log(ams.allCheck);
		/**통과 못할시 해당 함수 수행*/
		}else{
			alertError(ams.allCheck);
		}
	
	}
	/**상단 계좌중복, 입력란 공백 확인 메서드*/
	function alertError(check){
		if(!check){
			if(check == ams.checkComplete){
				ams.alertFlameD.style.display='block';
			}else{
			ams.alertFlame.style.display='block';
			}
			setTimeout(function() {
				if(check == ams.checkComplete){
					ams.alertFlame.style.display='none';
					ams.alertFlameD.style.display='none';
				}else{
				ams.alertFlame.style.display='none';
				}
				}, 2000)
				/**이미 등록된 계좌번호 일시*/
				if(check == ams.createCheck ){
					ams.alertFlame.childNodes[1].innerHTML="중복된 계좌번호입니다.";
					textFieldAlertOn(ams.accountNumFiled);
				/**입력란에 공란이 있을 시*/
				}else if (check == ams.allCheck){
					ams.alertFlame.childNodes[1].innerHTML="입력란을 다시 확인해주세요.";
				/**계좌생성완료시*/
				}else if(check == ams.checkComplete){
					ams.alertFlameD.childNodes[1].innerHTML="계좌생성완료.";
				}
					
				return true;
		}
		return false;
	}
	
	/**계좌 리스트를 문서에 append하는 함수*/
	function appendList(accountList){
		for ( var i in accountList) {
			/**ul 태그 생성*/
			var ul = document.createElement('ul');
			/**ul 클래스 생성*/
			ul.setAttribute('class', 'accountlist-body-colunm');
			/**구분선 생성*/
			var vline = document.createElement('hr');
			vline.setAttribute('style', 'background-color: LightGray; height: 1px');
			/**계좌정보를 accountList으로 부터 받아옴*/
			var accountInfo = accountList[i].toString().split("***");
			for ( var i in accountInfo) {
				/**ul tag에 li로 개별 정보 append*/
				var textNode = document.createTextNode(accountInfo[i]);
				var li = document.createElement('li');
				li.appendChild(textNode);
				ul.appendChild(li);
			}
			/**문서에 ul append*/
			ams.accountlist_body.appendChild(ul);
			ams.accountlist_body.appendChild(vline);
		}	
	}
	
	/**문서에서 계좌리스트를 전부 제거하는 함수*/
	function removeList(){
		ams.accountlist_body.innerHTML="";
	}
	
	/**accountNum을 지정된 형식으로 변경해주는 함수*/
	function accountNumTransform(originNum){
		/**숫자를 -를 가진 형식으로 변경*/
		if(originNum.length != 12){
			return originNum
		}
		var partOne = originNum.slice(0,4);
		var partTwo = originNum.slice(4,8);
		var partThree = originNum.slice(8,12);
		var result = partOne + '-' + partTwo + '-' + partThree; 
		return result;
	}
	/**예금액, 대출액 을 콤마(,)로 구분할 수 있게하는 메서드 */
	function moneyTransform(money){
		var intMoney = parseInt(money);
		return intMoney.toLocaleString();
	}