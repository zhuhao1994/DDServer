<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://globalite/dangdang/tld/core" prefix="d"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html lang="true">
  <head>
    <html:base />
    
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	<title>当当网-我的当当</title>
	<script type="text/javascript" src="/js/common.js" charset="gbk"></script>
	<script type="text/javascript" src="/js/myself.js"></script>
	<style type="text/css" media="screen">
		@import url("/css/global.css");
		@import url("/css/menu.css");
		@import url("/css/myself.css");
	</style>

  </head>
  
  <body>
    <jsp:include page="../common/title.jsp"></jsp:include>
	<jsp:include page="../common/head.jsp"></jsp:include>
    <div id="myself">
    	<div id="contextMenu">
        	您现在的位置：<a href="/books.jhtml">当当网</a> &gt; 我的当当
        </div>
        <d:leftMenu/>
        <div id="pane">
        	<div id="leftPane">
            	<img src="/${myselfForm.customer.head.path}${myselfForm.customer.head.name}" 
            		width="96" height="96" id="photo" onerror="noFind();"/>
            </div>
            <div id="rightPane">
            	<h2>欢迎，${myselfForm.customer.regname}</h2>
                <span class="cus_edit"><a href="/editown.do?op=load">编辑个人档案</a></span>
                <ul class="cus_info">
                	<li>
                    	<label>会员级别：</label>
                        <span class="cus_content cus_member">${myselfForm.customer.customerlevel.name}
                   		</span>
                        <span class="cus_member_img">
	                    	<img src="/img/cus/icon_mydd_question.gif" width="14" height="14"/>
	                    	<img src="/img/cus/icon_mydd_unmobile.gif" width="6" height="15"/>
	                    	<img src="/img/cus/icon_mydd_unshop.gif" width="16" height="14"/>
                        </span>
                    </li>
                    <li>
                    	<label>您的邮箱：</label>
                        <span class="cus_content">${myselfForm.customer.email}
                        <a href="/load_editmail.action">更改</a>
                        </span>
                    </li>
                    <li>
                    	<label>您的手机：</label>
                        <span class="cus_content">${myselfForm.customer.mobile}
                        <a href="/editown.do?op=load">更改</a>
                        </span>
                    </li>
                </ul>
                <ul class="order_info">
                	<li class="ord_status">
                    	<h3>交易提醒：</h3>
                        <span class="ord_content">审核订单<a href="/orders.do?op=load">(${myselfForm.type_verify})</a>
                       	</span>
                        <span class="ord_content">配货订单<a href="/orders.do?op=load">(${myselfForm.type_store})</a></span>
                        <span class="ord_content">已发货订单<a href="/orders.do?op=load">(${myselfForm.type_send})</a></span>
                    </li>
                    <li class="ord_status" style="border:none;">
                    	<h3>订单信息：</h3>
                        <span class="ord_content">成功订单<a href="/orders.do?op=load">(${myselfForm.type_over})</a></span>
                        <span class="ord_content">被取消订单<a href="/orders.do?op=load">(${myselfForm.type_cancel})</a></span>
                    </li>
                </ul>
            </div>
        </div>
    </div>
  </body>
</html:html>
