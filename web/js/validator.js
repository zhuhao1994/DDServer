String.prototype.trim = function()
{
	return this.replace(/(^\s*)|(\s*$)/g, "");
};
String.prototype.ltrim = function()
{
	return this.replace(/(^\s*)/g, "");
};
String.prototype.rtrim = function()
{
	return this.replace(/(\s*$)/g, "");
};
//==========================================================================
//                        ���ü��ж�
//==========================================================================
function canInputKey() {
	var code = window.event.keyCode;
	//��֤����ʹ��shift
	if(window.event.shiftKey)
		return false;
	//��֤�ܹ�ʹ��CTRL��V
	if(window.event.ctrlKey)
		return true;	
	if(code == 46 || code == 8 || code == 9 || (code >= 37 && code <= 40)) 
		return true;	
		
	return false;
}
//==========================================================================
//                        �����������ָ�ʽ
//==========================================================================
function requestNumber() {
	
	if(canInputKey())
		return true;
		
	var code = window.event.keyCode;
	
	if (code < 48 || code > 57)
		return false;
		
	return true;
}

//==========================================================================
//                        ��������绰��ʽ
//==========================================================================
function requestPhone() {

	if(canInputKey())
		return true;		
	
	var code = window.event.keyCode;

	if(code==189)
		return true;
	
	if ((code < 48 || code > 57))
		return false;

	return true;
}

//==========================================================================
//                        �����������ڸ�ʽ
//==========================================================================
function requestDate() {

	if(canInputKey())
		return true;		
	
	var code = window.event.keyCode;
	
	if(code==189 || code==191 || code == 190)
		return true;

	if ((code < 48 || code > 57))
		return false;

	return true;
}


//==========================================================================
//                        ����������/�����ʽ
//==========================================================================
function requestFloat(txt) {
	
	if(canInputKey())
		return true;		
	
	var code = window.event.keyCode;
	
	if(code==190 && txt.value.indexOf(".") >=0)
		return false;
	
	if(code==190 && txt.value.indexOf(".") < 0)
		return true;
	
	if ((code < 48 || code > 57))
		return false;

	return true;
}
//==========================================================================
//                        ��������XX-XX���ʺ����䣩��ʽ
//==========================================================================
function requestFitCrowd(txt) {
	if(canInputKey())
		return true;		
	
	var code = window.event.keyCode;
	
	if(code==189 && txt.value.indexOf("-") >=0)
		return false;
	
	if(code==189 && txt.value.indexOf("-") < 0)
		return true;
	
	if ((code < 48 || code > 57))
		return false;

	return true;
}
//==========================================================================
//                        ��������ʱ���ʽ
//==========================================================================
function requestTime(txt) {
	if(canInputKey())
		return true;		
	
	var code = window.event.keyCode;
	
	if(code==186 && txt.value.indexOf(":") >=0)
		return false;
	
	if(code==186 && txt.value.indexOf(":") < 0)
		return true;
	
	if ((code < 48 || code > 57))
		return false;

	return true;
}



//==========================================================================
//                        ����ʽ������
//==========================================================================
function moneyFormatString(value) {
	
	var val = value.toString();

	if(val == "") return "0.00";
	
	if(val.indexOf(".") < 0)  return val + ".00";
	
	if(val.indexOf(".") == 0) return "0" + val;
	
	var params = val.split('.');

	if(params[1].length == 0) return val + "00";
	
	if(params[1].length == 1) return val + "0";
	
	if(params[1].length == 2) return val;
	
	if(params[1].length > 2)  return (params[0] + "." + params[1].substring(0,2));
}
//==========================================================================
//           ����ʽ�������뱣֤�Ѿ�������requestFloat
//==========================================================================
function moneyFormat(txt) {
	
	var val =  txt.value;

	if(val == "") {
		txt.value = "0.00";
		return;
	}
	
	if(val.indexOf(".") < 0) {
		txt.value = val + ".00";
		return;
	}
	
	if(val.indexOf(".") == 0)
		txt.value = "0" + val;

	
	var params = txt.value.split('.');	
	
	if(params[1].length == 0) {
		txt.value = val + "00";
		return;
	}
	
	if(params[1].length == 1) {
		txt.value = val + "0";
		return;
	}
	
	if(params[1].length == 2){
		txt.value = val;
		return;
	}
	
	if(params[1].length > 2) {
		
		txt.value = params[0] + "." + params[1].substring(0,2);
		return;
	}
	
}
//==========================================================================
//           �Զ������ʽ������
//==========================================================================
function myMoneyParse(val) {
		
	var ret = parseFloat(val);
		
	if(isNaN(ret))
		return 0;
			
	return ret;
}

//==========================================================================
//           �Զ������ָ�ʽ������
//==========================================================================
function myIntParse(val) {
		
	var ret = parseInt(val);
		
	if(isNaN(ret))
		return 0;
			
	return ret;
}

function dateDiff(fromStr,toStr) {
	
	if(fromStr == "" || toStr == "") {
		return 0;
	}

	if(!checkDate(fromStr) || !checkDate(toStr)) {
		return 0; 
	}
	
	var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;    
	var from =  fromStr.match(reg);
	var to =  toStr.match(reg);  
	var fromDate = new Date(from[1],from[3]-1,from[4]); 
	var toDate = new Date(to[1],to[3]-1,to[4]);
	
	var day = ((toDate - fromDate) / (24*60*60*1000));
	
	if(day <=0 ) {
		return 0;
	} else {
		return day;
	}
}

function addDay(fromStr,day) {
	if(fromStr == "" || day == "")  {
		return "";
	}
	
	if(!checkDate(fromStr)) {
		return "";
	}
	
	var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;    
	var from =  fromStr.match(reg);
	var fromDate = new Date(from[1],from[3]-1,from[4]); 
	
	fromDate.setDate(fromDate.getDate() + myIntParse(day));
		
	return (fromDate.getFullYear() + "-" + 
		(fromDate.getMonth() + 1) + "-" + 
		(fromDate.getDate()));

}



//������ʽ��֤����
function check( reg, str ){
	if(reg.test(str))
  		return true;
 	
 	return false;
}

//�ַ�������Ч����֤
function checkLength(str,size){

	return (str.length > size); 
}

//�ַ���Χ��Ч����֤
function checkRange(str,minValue,maxValue){

	return (str.length >= minValue && str.length <= maxValue); 
}

//���ַ���Ч����֤
function checkEmpty(str){
	return (str.trim() == "");
}


//������Ч����֤
function checkNumber(str){
 	var reg = /^\d*(?:$|\.\d*$)/;

 	return check(reg, str);
}

//��ֵ�߼���С��Ч����֤
function compareNum(num1, num2){
 	if(num1 > num2)
  		return 1;
 	if(num1 == num2)
  		return 0;
 	return -1;
}
//�ʱ���Ч����֤
function checkZip(str){
	var reg = /^\d{6}$/;
	return check(reg, str);
}

//�ֻ���/С��ͨ����Ч����֤
function checkMobile(str){
 	var regMobile = /^\d{11,12}$/;
 	return check(regMobile, str);
}

//15λ���֤����Ч����֤
function checkNum15(str){
 	var reg = /^\d{15}$/;
 	return check(reg, str);
}

//18λ���֤����Ч����֤
function checkNum18(str){
	var reg = /^\d{17}(?:\d|x)$/;
	return check(reg, str);
}

//��ĸ��������Ч����֤
function checkCharOrNum(str){
	var reg = /^\w+$/;
	return check( reg, str );
}

//Email��Ч����֤
function checkEmail(str){
	var reg = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
	return check( reg, str );
}



//�绰������Ч����֤
function checkPhone(str) {
	if(str == "")
		return true;
		
	var reg = /^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/;
	
	var r =  str.match(reg);  

	if(r==null)return false; 
	
	return true;
}

//������Ч����֤
function checkDate(str){
		
		if(str == "")
			return true;
		var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;    
		var r =  str.match(reg);  

		if(r==null)return false;    
		var d = new Date(r[1],r[3]-1,r[4]);  
		
		var year = d.getFullYear();
		var month = d.getMonth()+1;
		if(month < 10)
			month = "0" + month;
		var day = d.getDate();
		if(day < 10)
			day = "0" + day;

		var newStr = year+r[2]+month+r[2]+day;
		var oldStr = r[1]+r[2]+(r[3].length<2?"0"+r[3]:r[3]) + 
		             r[2]+(r[4].length<2?"0"+r[4]:r[4]);
		
		return  (newStr==oldStr);
}
//ʱ����Ч����֤
function checkTime(str) {

	var value = str.match(/^(\d{1,2}):(\d{1,2})$/);
	
	if (value == null) return false;
	
	if (value[1]>=24 || value[2]>=60)
		return false;
	return true;
}