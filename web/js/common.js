function show(type) {
	window.document.getElementById("mainPane").style.visibility = type;
	window.document.getElementById("msg").style.visibility = type;
}
function doSubmit(formName) {
	window.document.forms[formName].submit();
}
function refreshCode() {
	var refreshIndex = Math.random();
	window.document.getElementById("imgCode").src="/code.jhtml?" + refreshIndex;
}
function doHref(url) {
	window.location.href = url;
}
function editCart(name,id) {
	
	var number = window.document.getElementById(name).value;
	
	doHref('cart.jhtml?op=edit&id=' + id + '&number=' + number);
	
}

function myFloatParse(val) {
	
	var ret = parseFloat(val);
	
	if(isNaN(ret))
		return 0.0;
	
	return ret;
}