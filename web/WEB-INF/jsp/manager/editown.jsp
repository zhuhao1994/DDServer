<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://globalite/dangdang/tld/core" prefix="d"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html lang="true">
<head>
	<html:base />
	<script type="text/javascript" src="/js/common.js" charset="gbk"></script>
	<script type="text/javascript" src="/js/jquery-1.4.4.js"></script>
	<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="/js/validator.js" charset="gbk"></script>
	<script type="text/javascript" src="/js/msg.js" charset="gbk"></script>
	<script type="text/javascript" src="/js/editown.js" charset="gbk"></script>

	<script type='text/javascript' src='/dwr/interface/cityDao.js'></script>
	<script type='text/javascript' src='/dwr/engine.js'></script>

	<script type="text/javascript">
	$(document).ready(function() {
		$("#txtBirthday").focus(function() {
			WdatePicker( {
				dateFmt : 'yyyy-MM-dd',
				lang : 'zh-cn',
				maxDate : "#F{$dp.$D('txtBirthday')}"
			})
		});
	});

	function getCities() {

		var proValue = window.document.getElementById("sltProvince").value;

		if("000"===proValue)
			return;
			
		dwr.engine.setPreHook(function () {
  			
  				document.getElementById("waitImg").style.visibility="visible";
  			
  		});
  			
  		dwr.engine.setPostHook(function () {
  			
  			document.getElementById("waitImg").style.visibility="hidden";
  			
  		});
			
		cityDao.findDistrictsByProvince((proValue + "-001"),function (datas) {

			var sltCity = window.document.getElementById("sltCity");
			
			clearOptions(sltCity);
			
			var op = document.createElement("OPTION");
			op.text="-请选择-";
			op.value="000";
			sltCity.options.add(op);
			
			for(var i = 0;i<datas.length;i++) {
				op = document.createElement("OPTION");
				op.text = datas[i].name;
				op.value = datas[i].id;
				sltCity.options.add(op);
			}
		});
	}
	function clearOptions(slt) {
		while (slt.options.length > 0)
			slt.options.remove(0);
	}
</script>

</head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<title>当当网订单管理页面</title>

<style type="text/css" media="screen">
	@import url("/css/global.css");
	@import url("/css/menu.css");
	@import url("/css/editown.css");
</style>

<body>
	<jsp:include page="../common/title.jsp"></jsp:include>
	<jsp:include page="../common/head.jsp"></jsp:include>
	<div id="editOwn">
		<div id="contextMenu">
			您现在的位置：
			<a href="/books.jhtml">当当网</a> &gt;
			<a href="/myself.do?op=load">我的当当</a> &gt; 编辑个人档案
		</div>
		<d:leftMenu />
		<html:form action="/editown.do" enctype="multipart/form-data">
			<div id="pane">
				<h3>
					编辑个人档案 (带
					<s class="red_star">*</s>号的项目为必填项)
				</h3>
				<ul id="own_info">
					<li class="big_head">
						<span id="img_big_head" class="img_big_head"> <img
								src="/${editownForm.image}" width="96" height="96" id="photo"
								name="photo" onerror="noFind();"
								onload="previewImage(this,96,96);" /> </span>
						<span class="upload"> <b>从您的电脑中上传图片作为头像：(建议尺寸96*96像素，300k以内)</b>
							<html:file property="file" styleId="file" onchange="preview();"
								maxlength="200" size="50"></html:file> <c:if
								test="${editownForm.uploadMsg != null}">
								<s id="err_photo" style="visibility: visible" class="err_panel">${editownForm.uploadMsg}</s>
							</c:if> </span>
					</li>

					<li class="component">
						<span class="cmp_title"> <s class="red_star">*</s>真实姓名： </span>
						<span class="cmp_content"> <html:text styleId="txtName"
								property="name" styleClass="edti_txt" maxlength="20" /> <b
							id="err_name" class="err_panel"></b> </span>

					</li>

					<li class="component">
						<span class="cmp_title"> 居住地： </span>
						<span class="cmp_content"> <%--<html:select styleId="sltProvince" property="province" onchange="javascript:document.getElementById('hdOp').value='select';doSubmit('editownForm');">--%>
							<html:select styleId="sltProvince" property="province"
								onchange="getCities();">
								<option value="000">
									-请选择-
								</option>
								<html:optionsCollection property="provinces" label="name"
									value="id" />
							</html:select>&nbsp;&nbsp; <input type="hidden" name="op" id="hdOp" /> <html:select
								styleId="sltCity" property="city">
								<option value="000">
									-请选择-
								</option>
								<html:optionsCollection property="cities" label="name"
									value="id" />
							</html:select>
							<img id="waitImg" src="/img/list/wait.gif" style="visibility:hidden"/>
						</span>
					</li>

					<li class="component">
						<span class="cmp_title"> 生日： </span>
						<span class="cmp_content"> <html:text styleId="txtBirthday"
								property="birthday" styleClass="edti_txt" maxlength="10" /> <b
							id="err_birthday" class="err_panel"></b> </span>
					</li>

					<li class="component">
						<span class="cmp_title"> 性别： </span>
						<span class="cmp_content"> <html:radio styleId="rdoMale"
								property="sex" value="0" /> <label for="rdoMale">
								男
							</label> <html:radio styleId="rdoFemale" property="sex" value="1" /> <label
								for="rdoFemale">
								女
							</label> </span>
					</li>

					<li class="component">
						<span class="cmp_title"> 身份： </span>
						<span class="cmp_content"> <html:radio styleId="rdoStudent"
								property="identity" value="0" /> <label for="rdoStudent">
								在校学生
							</label> <html:radio styleId="rdoTeacher" property="identity" value="1" />
							<label for="rdoFemale">
								教师
							</label> <html:radio styleId="rdoEmployee" property="identity" value="2" />
							<label for="rdoEmployee">
								上班族
							</label> <html:radio styleId="rdoFreelance" property="identity" value="2" />
							<label for="rdoFreelance">
								自由职业者
							</label> </span>
					</li>

					<li class="btn">
						<html:submit value="保存信息修改" property="edit" styleClass="edit_btn"
							onclick="return validateEidt();" />
					</li>
				</ul>
			</div>
		</html:form>
	</div>
</body>
</html:html>
