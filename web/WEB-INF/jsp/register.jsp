<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html lang="true">
  <head>
    <html:base />

	<title>当当网新用户注册页面</title>	

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	
	<script type="text/javascript" src="/js/common.js?201208091223" charset="gbk"></script>
	<script type="text/javascript" src="/js/msg.js?201404161519" charset="gbk"></script>
	<script type="text/javascript" src="/js/validator.js?201208091223" charset="gbk"></script>
	<script type="text/javascript" src="/js/reg.js?201404161519" charset="gbk"></script>
	<style type="text/css" media="screen">
		@import url("/css/global.css?201208091223");
		@import url("/css/menu.css?201208091223");
		@import url("/css/reg.css?201404161519");
	</style>
  </head>
  
  <body>
  	<jsp:include page="common/title.jsp"></jsp:include>
	<jsp:include page="common/head.jsp"></jsp:include>
    <html:form action="reg.do" method="post" >
	<div id="reg">
    	<p class="title">欢迎注册当当网！</p>
        <span class="caption">
        	<i>1、填写注册信息</i><s>2、注册成功</s>
        </span>
		<ul id="frame" class="frame">
        	<li class="tr">
            	<ul class="content">
                	<li class="label data_number">Email地址：</li>
                    <li class="td data_char">
                    	<html:text styleId="txtMail" property="email" styleClass="reg_txt" 
                        onfocus="selectTr(0,INFO_MSG_EMAIL);" 
						onblur="validateMail(0);"/>
                    </li>
                    <li class="msg data_char info_msg"></li>
                    <li class="msg data_char err_msg"></li>
                </ul>
            </li>
            <li class="tr">
            	<ul class="content">
                	<li class="label data_number">登录密码：</li>
                    <li class="td data_char">
                    	<html:password styleId="txtPassword"
                        	property="password" styleClass="reg_txt" 
                        	onfocus="selectTr(5,INFO_MSG_PWD);" 
                            onblur="validatePassword(5)"/>
                    </li>
                    <li class="msg data_char info_msg"></li>
                    <li class="msg data_char err_msg"></li>
                </ul>
            </li>
            <li class="tr">
            	<ul class="content">
                	<li class="label data_number">确认密码：</li>
                    <li class="td data_char">
                    	<html:password styleId="txtRePassword"
                        	property="repassword" styleClass="reg_txt" 
                        	onfocus="selectTr(10,INFO_MSG_REPWD);" 
                            onblur="validateRePassword(10)"/>
                    </li>
                    <li class="msg data_char info_msg"></li>
                    <li class="msg data_char err_msg"></li>
                </ul>
            </li>
            <li class="tr">
            	<ul class="content">
                	<li class="label data_number">登陆名(昵称)：</li>
                    <li class="td data_char">
                    	<html:text property="regname" styleClass="reg_txt" styleId="txtRegName"
                        	onfocus="selectTr(15,INFO_MSG_REGNAME);"
                            onblur="validateRegName(15);"/>
                    </li>
                    <li class="msg data_char info_msg"></li>
                    <li class="msg data_char err_msg"></li>
                </ul>
            </li>            
            <li class="tr">
            	<ul class="content">
                	<li class="label data_number">真实姓名：</li>
                    <li class="td data_char">
                    	<html:text property="name" styleClass="reg_txt" styleId="txtName"
                        	onfocus="selectTr(20,INFO_MSG_NAME);"
                            onblur="validateName(20);"/>
                    </li>
                    <li class="msg data_char info_msg"></li>
                    <li class="msg data_char err_msg">
                    	<html:errors property="reg_err_regname"/>
                    </li>
                </ul>
            </li>
            <li class="tr">
            	<ul class="content">
                	<li class="label data_number">手机号码：</li>
                    <li class="td data_char">
                    	<html:text property="mobile" styleClass="reg_txt" styleId="txtMobile"
                        	onfocus="selectTr(25,INFO_MSG_MOBILE);"
                            onblur="validateMobile(25);"/>
                    </li>
                    <li class="msg data_char info_msg"></li>
                    <li class="msg data_char err_msg">
                    </li>
                </ul>
            </li>
            <li class="tr">
            	<ul class="content">
                	<li class="label data_number">验证码：</li>
                    <li class="td data_char">
                    	<html:text property="code" styleClass="reg_txt" styleId="txtCode"
                        style="width:150px;" onfocus="selectTr(30,INFO_MSG_CODE);"
                        onblur="validateCode(30);"/>
                        <img onclick="refreshCode();" id="imgCode" 
                    	title="看不清楚?点我换一张" src="/code.jhtml" 
                   		width="80px" height="20px" />
                    </li>
                    <li class="msg data_char info_msg"></li>
                    <li class="msg data_char err_msg"></li>
                </ul>
            </li>
            <li class="tr">
            	<span class="btn_reg" onclick="regSubmit('regForm');"></span>
            </li>
            <li class="tr">
				<span class="agree">
                <html:checkbox styleId="agree" property="agree" value="1"/>
                <label for="agree">我已阅读并同意《当当网交易条款》和《当当网社区条款》</label>
                </span>
            </li>
        </ul>
    </div>
    <c:if test="${reg_err_code != null}">
    	<script type="text/javascript">
    		showErrMsg(30,'${reg_err_code}');
    	</script>
    </c:if>
    
    
    <c:if test="${requestScope['org.apache.struts.action.ERROR']!= null}">
    	<script type="text/javascript">
    		showErrMsg(15,null);
    	</script>
    </c:if>
    </html:form>
  </body>
</html:html>
