<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://globalite/dangdang/tld/core" prefix="d"%>

<%@ taglib uri="/struts-tags" prefix="s" %>
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
    
    <title>当当网-修改您的密码</title>
    
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

	<script type="text/javascript" src="/js/common.js" charset="gbk"></script>
	<script type="text/javascript" src="/js/validator.js" charset="gbk"></script>
	<script type="text/javascript" src="/js/msg.js" charset="gbk"></script>
	<script type="text/javascript" src="/js/editpwd.js" charset="gbk"></script>
	<style type="text/css" media="screen">
		@import url("/css/global.css");
		@import url("/css/menu.css");
		@import url("/css/editpwd.css");
	</style>
  </head>
  
  <body>
    <jsp:include page="../common/title.jsp"></jsp:include>
	<jsp:include page="../common/head.jsp"></jsp:include>
    <form action="editpwd.action" method="post" name="editpwdForm">
    <div id="editpwd">
    	<div id="contextMenu">
        	您现在的位置：<a href="/MainBooksServlet">当当网</a> &gt; <a href="/myself.do?op=load">我的当当</a> &gt; 修改个人密码
        </div>
        <d:leftMenu/>
        <div id="pane">
        	<h3>修改密码(修改成功后请重新登录当当网)</h3>
            <div id="content">
            	<ul id="pwd_info">
                <li>
                	<span class="cmp_title">原有密码：</span>
                    <span class="cmp_content">
                    	<input type="password" id="txtOldPwd" name="oldPwd" 
                                           class="pwd_txt" maxlength="20" />
						<c:if test="${requestScope['struts.valueStack'].root[0].message == null}">
							<b id="err_old_pwd" class="err_panel"></b>
						</c:if>
						<c:if test="${requestScope['struts.valueStack'].root[0].message != null}">
							<b id="err_old_pwd" class="err_panel" style="visibility:visible"><s:property value="message"/></b>
						</c:if>
						
                    </span>
                </li>
                <li>
                	<span class="cmp_title">您想要的新密码</span>
                    <span class="cmp_content">
                    	<input type="password" id="txtNewPwd" name="newPwd" 
                                           class="pwd_txt" maxlength="20" />
						<b id="err_new_pwd" class="info_panel">密码可由大小写英文字母、数字组成，长度6－20位。</b>
                    </span>
                </li>
                <li>
                	<span class="cmp_title">请再次输入新密码：</span>
                    <span class="cmp_content">
                    	<input type="password" id="txtNewPwdAgain" 
                        	name="newPwdAgain" class="pwd_txt" maxlength="20" />
						<b id="err_pwd_again" class="err_panel"></b>
                    </span>
                </li> 
                <li class="btn">
                	<input type="submit" value="保存信息修改" name="edit" 
                           class="edit_btn" onclick="return validateEidt();"/>
                </li>
            </ul>
        	</div>
        </div>
    </div>
    </form>
  </body>
</html>
