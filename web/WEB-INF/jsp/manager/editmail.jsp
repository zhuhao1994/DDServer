<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>

<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://globalite/dangdang/tld/core" prefix="d"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>" />

		<title>当当网-修改您的电子邮件</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

		<script type="text/javascript" src="/js/common.js" charset="gbk"></script>
		<script type="text/javascript" src="/js/validator.js" charset="gbk"></script>
		<script type="text/javascript" src="/js/msg.js" charset="gbk"></script>
		<script type="text/javascript" src="/js/editmail.js" charset="gbk"></script>
		<style type="text/css" media="screen">
			@import url("/css/global.css");
			@import url("/css/menu.css");
			@import url("/css/editmail.css");
		</style>
	</head>

	<body>
		<jsp:include page="../common/title.jsp"></jsp:include>
		<jsp:include page="../common/head.jsp"></jsp:include>
		<form action="/save_editmail.action" method="post" name="editmailForm">
			<div id="editmail">
				<div id="contextMenu">
					您现在的位置：
					<a href="/MainBooksServlet">当当网</a> &gt;
					<a href="/myself.do?op=load">我的当当</a> &gt; 编辑E-Mail地址
				</div>
				<d:leftMenu />
				<div id="pane">
					<h3>
						修改E-Mail(请填写合法的E-Mail地址)
					</h3>
					<div id="content">
						<ul id="mail_info">
							<li>
								<span class="cmp_title">我的E-mail地址：</span>
								<span class="cmp_mail"> <s:property value="currentMail" />
								</span>
							</li>
							<li>
								<span class="cmp_title">我的登录密码：</span>
								<span class="cmp_content"> <input type="password"
										id="txtPwd" name="password" class="mail_txt" maxlength="20" />
									<b id="err_pwd" class="err_panel"><s:property
											value="message" />
								</b> </span>
							</li>
							<li>
								<span class="cmp_title">我的新E-mail地址：</span>
								<span class="cmp_content"> <input type="text"
										id="txtNewMail" name="newMail" class="mail_txt" maxlength="20" />
									<b id="err_mail" class="err_panel"></b> </span>
							</li>
							<li class="btn">
								<input type="submit" value="保存信息修改" name="edit" class="edit_btn"
									onclick="return validateEidt();" />
							</li>
						</ul>
					</div>
				</div>
			</div>
		</form>
	</body>
</html>
