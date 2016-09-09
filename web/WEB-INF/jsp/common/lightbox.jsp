<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!--页面平铺遮挡层-->
<div id="mainPane"></div>
<form action="/MainLoginServlet" method="post" name="loginForm">
	<!--登录层-->
	<div id="msg">
		<p class="title">
			+ LOGIN WINDOW
			<span class="close" onclick="show('hidden');"></span>
		</p>
		<c:if
			test='${requestScope["com.globalite.dangdang.constants.errorkey"] == null}'>
			<p class="info">
				请输入登陆的用户和密码
			</p>
		</c:if>
		<c:if
			test='${requestScope["com.globalite.dangdang.constants.errorkey"] != null}'>
			<p class="errorinfo">
				${requestScope["com.globalite.dangdang.constants.errorkey"]}
			</p>
		</c:if>
		<p class="text">
			<label for="user">
				Username:
			</label>
			<input type="text" name="uid" class="loginTxt username" id="user" />
		</p>
		<p class="text">
			<label for="pwd">
				Password:
			</label>
			<input type="password" name="pwd" class="loginTxt password" id="pwd" />
		</p>
		<p class="text">
			<label for="code">
				Code:
				<img onclick="refreshCode();" id="imgCode" title="看不清楚?点我换一张"
					src="/ImageServlet" width="80px" height="20px" />
			</label>
			<input type="text" name="code" class="loginTxt code" id="code" />
		</p>
		<p class="btn">
			<img src="/img/login/loginin.gif" width="70PX" height="21PX"
				onclick="doSubmit('loginForm');"/>
		</p>
	</div>
	<input type="hidden" name="com.globalite.dangdang.constants.okurl" value="${param.okurl}"/>
	<input type="hidden" name="com.globalite.dangdang.constants.errurl" value="${param.errurl}"/>
</form>
<c:if
	test='${requestScope["com.globalite.dangdang.constants.errorkey"] != null}'>
	<script type="text/javascript">
	show('visible');
</script>
</c:if>

