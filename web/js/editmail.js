function validateEidt() {
	var ret = true;
	
	var mail = window.document.getElementById("txtNewMail").value;

	if(checkEmpty(mail)) {
		window.document.getElementById("err_mail").style.visibility="visible";
		window.document.getElementById("err_mail").innerHTML = ERR_MSG_EMPTY_EMAIL;
		ret = false;
	}

	if(!checkEmail(mail)) {
		window.document.getElementById("err_mail").style.visibility="visible";
		window.document.getElementById("err_mail").innerHTML = ERR_MSG_FORMAT_EMAIL;
		ret = false;
	}
	
	if(ret)
		window.document.getElementById("err_mail").style.visibility="hidden";
	
	return ret;
}
