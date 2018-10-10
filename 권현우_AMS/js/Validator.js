/**
 * 입력문의 유효성 검증 
 */
/**공백체크*/
function checkSpace(str){
	return 	(str.search(/\s/g) != -1)? true:false;
}

/**특수문자 유무 체크*/
function checkSpaciel(str){
	var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
	return (special_pattern.test(str));
}

/**문자 유무 체크*/
function checkChar(str){
	var char_pattern = /[a-zA-Z]/g;
	return (char_pattern.test(str));
}

/**숫자 유무 체크*/
function checkNum(str){
	var num_pattern =/[0-9]/g;
	return (num_pattern.test(str));
}

/**한글 유무 체크*/

function checkKor(str){
	var kor_pattern = /[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/g;
	return (kor_pattern.test(str));
}

/**빈칸 체크*/
function checkNull(str){
	return (str=='')?true:false;
}