function cal(rdo) {
	
	var value = rdo.value;
	
	window.document.getElementById("dPrice").innerHTML = value;
	
	calTotalPrice();
	
	/*var sPrice = myFloatParse(window.document.getElementById("sPrice").innerHTML);
	var dPrice = myFloatParse(value);
	
	window.document.getElementById("tPrice").innerHTML = (sPrice + dPrice);*/
}

function calTotalPrice() {
	var sPrice = window.document.getElementById("sPrice").innerHTML;
	var dPrice = window.document.getElementById("dPrice").innerHTML;
	var tPrice = window.document.getElementById("tPrice");
	
	tPrice.innerHTML = myFloatParse(sPrice) + myFloatParse(dPrice); 
}

function newAddress(rdo) {
	
	if(rdo.value == "-1")
	{
		window.document.getElementById("confEntry").style.visibility="visible";
		window.document.getElementById("confEntry").style.height="auto";
	}else {
		window.document.getElementById("confEntry").style.visibility="hidden";
		window.document.getElementById("confEntry").style.height="0px";
	}
}

function btn_click() {
	var rdos = window.document.getElementsByName("address");
	
	var rdo = null;

	for(var i = 0;i<rdos.length;i++) {
		if(rdos[i].checked) {
			rdo = rdos[i];
			break;
		}
	}
	if(rdo.value !="-1") {
		alert("�뵥�������µ�ַ��ѡ��");
		return;
	}
	
	if(!validateAdd()) {	
		if(window.document.getElementById("confEntry").style.visibility=="hidden"
			||window.document.getElementById("confEntry").style.visibility=="") {
			getCountries();
		}
		
		newAddress(rdo);
		
		return;
	}
	
	
	
	saveData();
}
/*��֤��ַ���*/
function validateAdd() {
	var ret = true;
	
	var consignmentName = window.document.getElementById("txtConsignmentName").value;
	
	if(checkEmpty(consignmentName)) {
		window.document.getElementById("errMessage").style.visibility="visible";
		window.document.getElementById("errMessage").style.height="20px";
		window.document.getElementById("errMessage").innerHTML = ERR_MSG_EMPTY_CONSIGNMENT_NAME;
		ret = false;
		return;
	}
	
	var country = window.document.getElementById("sltCountry").value;

	if(country === "000") {
		window.document.getElementById("errMessage").style.visibility="visible";
		window.document.getElementById("errMessage").style.height="20px";
		window.document.getElementById("errMessage").innerHTML = ERR_MSG_EMPTY_COUNTRY;
		ret = false;
		return;
	}
	
	var province = window.document.getElementById("sltProvince").value;

	if(province === "000") {
		window.document.getElementById("errMessage").style.visibility="visible";
		window.document.getElementById("errMessage").style.height="20px";
		window.document.getElementById("errMessage").innerHTML = ERR_MSG_EMPTY_PROVINCE;
		ret = false;
		return;
	}
	
	var city = window.document.getElementById("sltCity").value;

	if(city === "000") {
		window.document.getElementById("errMessage").style.visibility="visible";
		window.document.getElementById("errMessage").style.height="20px";
		window.document.getElementById("errMessage").innerHTML = ERR_MSG_EMPTY_CITY;
		ret = false;
		return;
	}
	
	var district = window.document.getElementById("sltDistrict").value;

	if(district === "000") {
		window.document.getElementById("errMessage").style.visibility="visible";
		window.document.getElementById("errMessage").style.height="20px";
		window.document.getElementById("errMessage").innerHTML = ERR_MSG_EMPTY_DISTRICT;
		ret = false;
		return;
	}
	
	var address = window.document.getElementById("txtAddress").value;

	if(checkEmpty(address)) {
		window.document.getElementById("errMessage").style.visibility="visible";
		window.document.getElementById("errMessage").style.height="20px";
		window.document.getElementById("errMessage").innerHTML = ERR_MSG_EMPTY_ADDRESS;
		ret = false;
		return;
	}
	
	var zip = window.document.getElementById("txtPostCode").value;
	
	if(checkEmpty(zip)) {
		window.document.getElementById("errMessage").style.visibility="visible";
		window.document.getElementById("errMessage").style.height="20px";
		window.document.getElementById("errMessage").innerHTML = ERR_MSG_EMPTY_ZIP;
		ret = false;
		return;
	}

	if(!checkZip(zip) ) {
		window.document.getElementById("errMessage").style.visibility="visible";
		window.document.getElementById("errMessage").style.height="20px";
		window.document.getElementById("errMessage").innerHTML = ERR_MSG_FORMATE_ZIP;
		ret = false;
		return;
	}
	
	var phone = window.document.getElementById("txtPhone").value;

	if(!checkEmpty(phone) && !checkPhone(phone) ) {
		window.document.getElementById("errMessage").style.visibility="visible";
		window.document.getElementById("errMessage").style.height="20px";
		window.document.getElementById("errMessage").innerHTML = ERR_MSG_FORMATE_PHONE;
		ret = false;
		return;
	}
	
	var mobile = window.document.getElementById("txtMobile").value;

	if(!checkEmpty(mobile) && !checkMobile(mobile) ) {
		window.document.getElementById("errMessage").style.visibility="visible";
		window.document.getElementById("errMessage").style.height="20px";
		window.document.getElementById("errMessage").innerHTML = ERR_MSG_FORMATE_MOBILE;
		ret = false;
		return;
	}
	if(ret) {
		window.document.getElementById("errMessage").style.visibility="hidden";
		window.document.getElementById("errMessage").style.height="0px";
		window.document.getElementById("errMessage").innerHTML = "";
	}
	return ret;
}
/*������е�ַ*/
function removeAllLi() {
	var ul = window.document.getElementById("ulContent");	

	while(ul.childNodes.length > 0) {
		var node = ul.childNodes.item(0);
		ul.removeChild(node);
	}
}
/*�������½���ַ����ѡ��*/
function createNewAddress(ul) {
	var li = document.createElement("LI");

	var rdo = document.createElement("INPUT");
	rdo.setAttribute("id","rdoAddress");
	rdo.setAttribute("type","radio");
	rdo.setAttribute("name","address");
	rdo.setAttribute("value","-1");
	rdo.onclick = function() {
		 newAddress(this);
	};
	
	var textNode = document.createTextNode(" �����µ�ַ");
	
	li.appendChild(rdo);
	li.appendChild(textNode);
	
	ul.appendChild(li);
}
/*���ݷ����Ajax���ص����ݴ���һ����ַ*/
function createAddress(ul,data) {
	var li = document.createElement("LI");

	var rdo = document.createElement("INPUT");
	rdo.setAttribute("id","rdoAddress");
	rdo.setAttribute("type","radio");
	rdo.setAttribute("name","address");
	rdo.setAttribute("value",data.id);

	if(data.default_ == 0)
		rdo.checked = true;
		
	rdo.onclick = function() {
		 newAddress(this);
	};
	var phone = data.phone;
	var mobile = data.mobile;
	
	if(phone == null)
		phone="";
	
	if(mobile == null)
		mobile="";
		
	var textNode = document.createTextNode(" " + 
	data.customer.name + "��" + 
	data.cityByCountry.name + " " +
	data.cityByProvince.name + " " +
	data.cityByCity.name + " " +
	data.cityByDistrict.name + " " +
	data.address + " " +
	data.postcode  + " " +
	phone  + " " + mobile);
	
	li.appendChild(rdo);
	li.appendChild(textNode);
	
	ul.appendChild(li);
}
/*���ݷ����Ajax���ص����ݴ������е�ַ*/
function createAllAddress() {
	var cusid = dwr.util.getValue("txtCusid");
	
	addressDao.findAllCustomerAddress(cusid,function (datas) {

		var ul = window.document.getElementById("ulContent");
		
		for(var i = 0;i<datas.length;i++) {
			
			createAddress(ul,datas[i]);
		}
		
		createNewAddress(ul);
	});

}
/*�����û�������µ�ַ*/
function saveData() {
	var address = {cityByCountry:{id:dwr.util.getValue("sltCountry")},
		cityByProvince:{id:dwr.util.getValue("sltProvince")},
		cityByCity:{id:dwr.util.getValue("sltCity")},
		cityByDistrict:{id:dwr.util.getValue("sltDistrict")},
		customer:{id:dwr.util.getValue("txtCusid")},
		consignmentname:dwr.util.getValue("txtConsignmentName"),
		address:dwr.util.getValue("txtAddress"),
		postcode:dwr.util.getValue("txtPostCode"),
		phone:dwr.util.getValue("txtPhone"),
		mobile:dwr.util.getValue("txtMobile")
	};			
		
	addressDao.save(address,function (datas) {
		alert("�µ�ַ�Ѿ����棬����Ϊ�����µ�ַ�б�");
		showAddressList();
	});
}
/*��ʾ���еĵ�ַ�б�*/
function showAddressList() {
	removeAllLi();
	createAllAddress();
}
/*Ajax���Ҳ�ѯ*/
function getCountries() {

	cityDao.findCountries(function (datas) {
		var sltCountry = window.document.getElementById("sltCountry");
		var sltProvince = window.document.getElementById("sltProvince");
		var sltCity = window.document.getElementById("sltCity");
		var sltDistrict = window.document.getElementById("sltDistrict");
		
		clearOptions(sltCountry);
		clearOptions(sltProvince);
		clearOptions(sltCity);
		clearOptions(sltDistrict);
		
		createOptions(sltCountry,datas);
	});
}
/*Ajaxʡ�ݲ�ѯ*/
function getProvinces() {
	var countryValue = window.document.getElementById("sltCountry").value;
	
	if("000"===countryValue)
		return;
	
	cityDao.findProvinces(countryValue,function (datas) {
		var sltProvince = window.document.getElementById("sltProvince");
		var sltCity = window.document.getElementById("sltCity");
		var sltDistrict = window.document.getElementById("sltDistrict");

		clearOptions(sltProvince);
		clearOptions(sltCity);
		clearOptions(sltDistrict);
		
		createOptions(sltProvince,datas);
	});
}
/*Ajax���в�ѯ*/
function getCities() {
    var countryValue = window.document.getElementById("sltCountry").value;
	var proValue = window.document.getElementById("sltProvince").value;
	
	if("000"===countryValue)
		return;
		
	if("000"===proValue)
		return;
		
	cityDao.findCities((proValue + "-" + countryValue),function (datas) {
		
		var sltCity = window.document.getElementById("sltCity");
		var sltDistrict = window.document.getElementById("sltDistrict");
		
		clearOptions(sltCity);
		clearOptions(sltDistrict);
		
		createOptions(sltCity,datas);
	});
}
/*Ajax���ز�ѯ*/
function getDistricts() {
	var countryValue = window.document.getElementById("sltCountry").value;
	var proValue = window.document.getElementById("sltProvince").value;
	var cityValue = window.document.getElementById("sltCity").value;

	if("000"===countryValue || "000" === proValue || "000" === cityValue)
		return;
		
	var cityId = cityValue + "-" + proValue + "-" + countryValue;
	
	if(proValue === cityValue)
		cityId = proValue + "-" + countryValue;
	
	cityDao.findDistricts(cityId,function (datas) {

		var sltDistrict = window.document.getElementById("sltDistrict");
		
		clearOptions(sltDistrict);
		
		createOptions(sltDistrict,datas);
	});
}
/*���������*/
function clearOptions(slt) {
	while (slt.options.length > 0)
		slt.options.remove(0);
		
	var op = document.createElement("OPTION");
	op.text="-��ѡ��-";
	op.value="000";
	slt.options.add(op);
}
/*����������ѡ��*/
function createOptions(slt,datas) {
			
	for(var i = 0;i<datas.length;i++) {
		op = document.createElement("OPTION");
		op.text = datas[i].name;
		op.value = datas[i].id;
		slt.options.add(op);
	}
}