// JavaScript Document
	
	var INFO_MSG_INDEX = 2;
	var ERROR_MSG_INDEX = 3;
	
	var LI_LENGTH = 4;
	
	var NODE_TYPE_ELEMENT = 1;
	
	var TR_CLASS_NAME = "tr";
	
	var SELECTED_TR_CLASS_NAME = "tr tr_selected";
	
	function init() {
		var ul = window.document.getElementById("frame");
		var liNodes = ul.getElementsByTagName("li");
		var len = liNodes.length;
		for(var i=0;i<len;i++) {
			var trNode =  liNodes.item(i);
			if(trNode.nodeType == NODE_TYPE_ELEMENT && 
				(trNode.className ==TR_CLASS_NAME || 
				 trNode.className ==SELECTED_TR_CLASS_NAME)) {
				
				trNode.className = TR_CLASS_NAME;
				var msgNodes = trNode.getElementsByTagName("li");
				if(msgNodes.length == LI_LENGTH) {
					msgNodes.item(INFO_MSG_INDEX).style.visibility="hidden";
				}
			}
		}
	}
	
	function selectTr(index,msg) {
		
		init();
		
		var ul = window.document.getElementById("frame");	
		var activedNode = ul.getElementsByTagName("li").item(index);
		activedNode.className = SELECTED_TR_CLASS_NAME;
		
		var activeMsgNodes = activedNode.getElementsByTagName("li");
		if(activeMsgNodes.length == LI_LENGTH) {
			activeMsgNodes.item(INFO_MSG_INDEX).style.visibility="visible";
			activeMsgNodes.item(INFO_MSG_INDEX).innerHTML = msg;
			activeMsgNodes.item(ERROR_MSG_INDEX).style.visibility="hidden";
		}
		
	}
	
	function validateMail(index) {

		var mail = window.document.getElementById("txtMail").value;

		if(checkEmpty(mail)) {
			showErrMsg(index,ERR_MSG_EMPTY_EMAIL);
			return false;
		}
		if(!checkEmail(mail)) {
			showErrMsg(index,ERR_MSG_FORMAT_EMAIL);
			return false;
		}
		
		return true;
	}
	
	function validatePassword(index) {

		var pwd = window.document.getElementById("txtPassword").value;

		if(checkEmpty(pwd)) {
			showErrMsg(index,ERR_MSG_EMPTY_PWD);
			return false;
		}
		if(!checkRange(pwd,6,20)) {
			showErrMsg(index,ERR_MSG_LENGTH_PWD);
			return false;
		}
		
		return true;
	}
	
	function validateRePassword(index) {

		var pwd = window.document.getElementById("txtPassword").value;
		var repwd = window.document.getElementById("txtRePassword").value;

		if(checkEmpty(repwd)) {
			showErrMsg(index,ERR_MSG_EMPTY_REPWD);
			return false;
		}
		if(repwd != pwd) {
			showErrMsg(index,ERR_MSG_EQUAL_REPWD);
			return false;
		}
		
		return true;
	}

	function validateRegName(index) {
		var reg = window.document.getElementById("txtRegName").value;

		if(checkEmpty(reg)) {
			showErrMsg(index,ERR_MSG_EMPTY_REGNAME);
			return false;
		}
		if(!checkRange(reg,6,20)) {
			showErrMsg(index,ERR_MSG_LENGTH_REGNAME);
			return false;
		}
		
		return true;
	}
	
	function validateName(index) {

		var name = window.document.getElementById("txtName").value;

		if(checkEmpty(name)) {
			showErrMsg(index,ERR_MSG_EMPTY_NAME);
			return false;
		}
		if(!checkRange(name,0,20)) {
			showErrMsg(index,ERR_MSG_LENGTH_NAME);
			return false;
		}
		
		return true;
	}
	
	function validateMobile(index) {

		var mobile = window.document.getElementById("txtMobile").value;

		if(checkEmpty(mobile)) {
			showErrMsg(index,ERR_MSG_EMPTY_MOBILE);
			return false;
		}
		
		if(!checkMobile(mobile)) {
			showErrMsg(index,ERR_MSG_FORMATE_MOBILE);
			return false;
		}
		
		return true;
	}
	
	function validateCode(index) {

		var code = window.document.getElementById("txtCode").value;

		if(checkEmpty(code)) {
			showErrMsg(index,ERR_MSG_EMPTY_CODE);
			return false;
		}
		
		return true;
	}

	function showErrMsg(index,msg) {
		var ul = window.document.getElementById("frame");
		
		var activedNode = ul.getElementsByTagName("li").item(index);
		var activeMsgNodes = activedNode.getElementsByTagName("li");
		
		if(activeMsgNodes.length == LI_LENGTH) {
			activedNode.className = "tr";
			activeMsgNodes.item(INFO_MSG_INDEX).style.visibility="hidden";
			activeMsgNodes.item(ERROR_MSG_INDEX).style.visibility="visible";
		}
	
		if(msg === null || msg == "") {
			return;
		}
			
		activeMsgNodes.item(ERROR_MSG_INDEX).innerHTML = msg;
	}
	
	function regSubmit(formName) {
		
		if(validateAll())
			doSubmit(formName);
	}
	
	function validateAll() {
		var ret = true;
		
		ret = validateMail(0);
		ret = validatePassword(5);
		ret = validateRePassword(10);
		ret = validateRegName(15);
		ret = validateName(20);
		ret = validateMobile(25);
		ret = validateCode(30);
		
		return ret;
	}