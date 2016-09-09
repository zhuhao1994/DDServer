function validateEidt() {
	var ret = true;
	
	var oldpwd = window.document.getElementById("txtOldPwd").value;

	if(checkEmpty(oldpwd)) {
		window.document.getElementById("err_old_pwd").style.visibility="visible";
		window.document.getElementById("err_old_pwd").innerHTML = ERR_MSG_EMPTY_OLDPWD;
		ret = false;
	}

	if(!checkRange(oldpwd,6,20)) {
		window.document.getElementById("err_old_pwd").style.visibility="visible";
		window.document.getElementById("err_old_pwd").innerHTML = ERR_MSG_LENGTH_OLDPWD;
		ret = false;
	}
	
	if(ret)
		window.document.getElementById("err_old_pwd").style.visibility="hidden";
	
	var newpwd = window.document.getElementById("txtNewPwd").value;

	if(checkEmpty(newpwd)) {
		window.document.getElementById("err_new_pwd").style.visibility="visible";
		window.document.getElementById("err_new_pwd").className="err_panel";
		window.document.getElementById("err_new_pwd").innerHTML = ERR_MSG_EMPTY_NEWPWD;
		ret = false;
	}

	if(!checkRange(newpwd,6,20)) {
		window.document.getElementById("err_new_pwd").style.visibility="visible";
		window.document.getElementById("err_new_pwd").className="err_panel";
		window.document.getElementById("err_new_pwd").innerHTML = ERR_MSG_LENGTH_NEWPWD;
		ret = false;
	}

	if(ret) {
		window.document.getElementById("err_new_pwd").style.visibility="visible";
		window.document.getElementById("err_new_pwd").className="info_panel";
		window.document.getElementById("err_new_pwd").innerHTML = INFO_MSG_NEWPWD;
	}
	
	var again = window.document.getElementById("txtNewPwdAgain").value;

	if(checkEmpty(again)) {
		window.document.getElementById("err_pwd_again").style.visibility="visible";
		window.document.getElementById("err_pwd_again").innerHTML = ERR_MSG_EMPTY_AGAINPWD;
		ret = false;
	}
	
	if(newpwd != again) {
		window.document.getElementById("err_pwd_again").style.visibility="visible";
		window.document.getElementById("err_pwd_again").innerHTML = ERR_MSG_EQUAL_AGAINPWD;
		ret = false;
	}

	if(ret)
		window.document.getElementById("err_pwd_again").style.visibility="hidden";
	
	return ret;
}
