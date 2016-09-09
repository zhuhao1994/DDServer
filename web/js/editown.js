function validateEidt() {
	var ret = true;
	
	var name = window.document.getElementById("txtName").value;
	
	window.document.getElementById('hdOp').value='save';
		
	if(checkEmpty(name)) {
		window.document.getElementById("err_name").style.visibility="visible";
		window.document.getElementById("err_name").innerHTML = ERR_MSG_EMPTY_NAME;
		ret = false;
	}

	if(!checkRange(name,0,20)) {
		window.document.getElementById("err_name").style.visibility="visible";
		window.document.getElementById("err_name").innerHTML = ERR_MSG_LENGTH_NAME;
		ret = false;
	}
	
	if(ret)
		window.document.getElementById("err_name").style.visibility="hidden";
		
	var birthday = window.document.getElementById("txtBirthday").value;
	
	if(!checkDate(birthday)) {
		window.document.getElementById("err_birthday").style.visibility="visible";
		window.document.getElementById("err_birthday").innerHTML = ERR_MSG_FORMATE_BIRTHDAY;
		ret = false;
	}

	if(ret)
		window.document.getElementById("err_birthday").style.visibility="hidden";
	
	return ret;
}
//��Ʒ��ͼƬԤ��
function preview() {
	var filename = document.getElementById("file");
	var photo = document.getElementById("photo");
	
	if (!filename || !filename.value || !photo) {
		return;
	}
	
	var patn = /\.jpg$|\.jpeg$|\.gif$|\.png$/i;
	if (!patn.test(filename.value)) {
		filename.value = "";
		return;
	}
	
	if (navigator.userAgent.indexOf("MSIE")!=-1) {
    	//photo.src = "file:///" + filename.value.replace("/\\/g","/");
		if(document.getElementById("img_big_head").filters) {
			filename.select();
			
	        var imgSrc = document.selection.createRange().text;
	        document.getElementById("img_big_head").filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = imgSrc;
	        photo.style.display = 'none';
        }else {
        	photo.src = "file:///" + filename.value.replace("/\\/g","/");
        }
   	} else if (navigator.userAgent.indexOf("Firefox")!=-1 || navigator.userAgent.indexOf("Mozilla")!=-1){
   		try {
   			if (filename.files && filename.files[0]) {
	   			netscape.security.PrivilegeManager.enablePrivilege('UniversalXPConnect');
	   			photo.src=filename.files[0].getAsDataURL();
   			}
   		} 
   		catch (e) 
   		{ 
   			alert("!!�������ܾ�\n����������ַ8����about:config���س�\nȻ��signed.applets.codebase_principal_support����Ϊtrue"); 
   		} 
  		
   } else { 
  	return;   
   }

	
}
//�ȱ������Ԥ��ͼƬ��ʾ��ʽ
function previewImage(img, fitWidth, fitHeight) {
	var image = new Image();
	
	image.src = img.src;

	if (image.width > 0 && image.height > 0) {
		if (image.width / image.height >= fitWidth / fitHeight) {
			if (image.width > fitWidth) {
				img.width = fitWidth;
				img.height = (image.height * fitWidth) / image.width;
			} else {
				img.width = image.width;
				img.height = image.height;
			}
		} else {
			if (image.height > fitHeight) {
				img.height = fitHeight;
				img.width = (image.width * fitHeight) / image.height;
			} else {
				img.width = image.width;
				img.height = image.height;
			}
		}
	}
}

function noFind() {
	window.document.getElementById("photo").src="/img/heads/img_big.bmp";
	window.document.getElementById("photo").onerror=null;
}