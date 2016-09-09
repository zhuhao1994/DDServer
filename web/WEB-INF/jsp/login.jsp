<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
	
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	
	<title>欢迎登陆-当当网</title>
	<script type="text/javascript" src="/js/common.js" charset="gbk"></script>
	<script type="text/javascript" src="/js/jquery-1.4.4.js"  charset="gbk"></script>
	<style type="text/css" media="screen">
		@import url("css/global.css");
		@import url("css/menu.css");
		@import url("css/mainlogin.css");
	</style>
	<script type="text/javascript">
		$(function() {
			$(".button").hover(function() {
					//$(".button").attr("className","button_over");
					$(this).attr("className","button_over");
				}, function() {
					/*以下代码会发生错误，因为样式名已经改掉了*/
					//$(".button").attr("className","button");
					$(this).attr("className","button");
				}
			);
			$(".regbutton").hover(function() {
					//$(".regbutton").attr("className","regbutton_over");
					$(this).attr("className","regbutton_over");
				}, function() {
					/*以下代码会发生错误，因为样式名已经改掉了*/
					//$(".regbutton").attr("className","regbutton");
					$(this).attr("className","regbutton");
				}
			);
		});
	</script>
</head>
<body>
    <div id="logoBar">
    	<div id="logo"></div>
    </div>
    
    <form action="login.jhtml" method="post" name="loginForm" >
    <div id="loginBar">
    	<div id="login">
        	<div class="top">
            </div>
            <ul>
            	<li>
                	<h2>Email地址或昵称登录</h2>
                	<p class="errorinfo">
						${requestScope["com.globalite.dangdang.constants.errorkey"]}
					</p>
                </li>
                <li>
                	<label>登录账户:</label>
                    <span><input type="text" name="uid" class="txt" /></span>
                </li>
                <li>
                	<label>账户密码:</label>
                	<span><input type="password" name="pwd" class="txt" /></span>
                </li>
                <li>
                	<label><img onclick="refreshCode();" id="imgCode" title="看不清楚?点我换一张" src="/code.jhtml" width="80px" height="20px" /></label>
                    <span><input type="text" name="code" class="txt" /></span>
                </li>
               
	                <li class="btn">
	                	<span class="button" onclick="doSubmit('loginForm');"></span>
	                    <span class="link"><a href="#">忘记密码？</a></span>
	                </li>
               
                <li class="summary">
                	还不是当当网用户？快捷方便的免费注册，让你立刻尽享当当网提供的各项优惠及服务... 
                </li>
                <li class="btn">
                	<span class="regbutton" onclick="doHref('reg.do?flag=init');"></span>
                </li>
            </ul>
            <div class="bottom">
            </div>
        </div>
    </div>
    <input type="hidden" name="com.globalite.dangdang.constants.okurl" value="${requestScope['com.globalite.dangdang.constants.okurl']}"/>
	<input type="hidden" name="com.globalite.dangdang.constants.errurl" value="${requestScope['com.globalite.dangdang.constants.errurl']}"/>
	</form>
</body>
</html>
