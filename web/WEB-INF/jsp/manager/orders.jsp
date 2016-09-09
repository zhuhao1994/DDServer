<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://globalite/dangdang/tld/core" prefix="d"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html:html lang="true">
<head>
	<html:base />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />
	
	<title>当当网订单管理页面</title>
	<script type="text/javascript" src="/js/common.js" charset="gbk"></script>
	<script src="/js/jquery-1.4.4.js" type="text/javascript"></script>
	<script type="text/javascript" src="/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		$(document).ready(
			function(){
				$("#txtDateFrom").focus(
				function(){
					WdatePicker({
						dateFmt:'yyyy-MM-dd',lang:'zh-cn',maxDate:"#F{$dp.$D('txtDateFrom')}"
					})
				});
				
				$("#txtDateTo").focus(
				function(){
					WdatePicker({
						dateFmt:'yyyy-MM-dd',lang:'zh-cn',maxDate:"#F{$dp.$D('txtDateTo')}"
					})
				});
			}
		);
	</script>
	<style type="text/css" media="screen">
		@import url("/css/global.css");
		@import url("/css/menu.css");
		@import url("/css/orders.css");
	</style>
</head>

<body>
	<jsp:include page="../common/title.jsp"></jsp:include>
	<jsp:include page="../common/head.jsp"></jsp:include>
	
	<div id="orders">
    	<div id="contextMenu">
        	您现在的位置：<a href="/books.jhtml">当当网</a> &gt; <a href="/myself.do?op=load">我的当当</a> &gt; 我的订单
        </div>
       	<d:leftMenu/>
   		<div id="pane">
        	<html:form action="/orders.do" method="post">
            <div id="condition">
            	订单编号:&nbsp;<html:text property="ordid" styleId="txtOrdid" 
                	styleClass="txt" maxlength="10" size="20" />&nbsp;&nbsp;&nbsp;
               	消费金额:&nbsp;<html:text property="priceFrom" styleId="txtPriceFrom" 
                	styleClass="txt" maxlength="10" size="10" />&nbsp;～&nbsp;
                    <html:text property="priceTo" styleId="txtPriceTo" 
                	styleClass="txt" maxlength="10" size="10" />&nbsp;&nbsp;&nbsp;
                               下单时间:&nbsp;<html:text property="dateFrom" styleId="txtDateFrom" 
                	styleClass="txt" maxlength="10" size="10" />&nbsp;～&nbsp;
                    <html:text property="dateTo" styleId="txtDateTo" 
                	styleClass="txt" maxlength="10" size="10" />
                	<html:submit property="query" styleClass="btn" value=" 查 询 " />
                	<html:hidden property="op" value="query"/>
            </div>
            </html:form>
            
        	<ul id="caption">
            	<li><span>订单号</span></li>
                <li><span>收货人</span></li>
                <li><span>付款方式</span></li>
                <li><span>订单总金额</span></li>
                <li><span>订单状态</span></li>
                <li><span>下单时间</span></li>
                <li><span style="border:none;">操作</span></li>
            </ul>
            
            <c:if test="${fn:length(ordersForm.orders) == 0}">
	            <div id="no_order_data">
	            	${ordersForm.msg} &nbsp;&nbsp;&nbsp;
	                <a href="/books.jhtml">去促销看看&gt;&gt;</a>
	            </div>
            </c:if>
            <c:forEach items="${ordersForm.orders}" var="order">
	            <ul class="tr">
	            	<li>${order.own.ordid}</li>
	                <li>${order.own.receiver}</li>
	                <li>${order.payString}</li>
	                <li>￥${order.own.price}</li>
	                <li>${order.typeString}</li>
	                <li>${order.createDate}</li>
	                <li>
	                	<c:choose>
	                		<c:when test="${order.own.ordertype == 0 || order.own.ordertype == 1}">
	                			<a href="/orders.do?op=cancel&id=${order.own.id}&orderstype=2">取消</a>
	                		</c:when>
	                		<c:when test="${order.own.ordertype == 2}">
	                			<a href="/orders.do?op=undo&id=${order.own.id}&orderstype=0">恢复订单</a>
	                		</c:when>
	                		<c:otherwise>
	                			--
	                		</c:otherwise>
	                	</c:choose>
	                </li>
	            </ul>
            </c:forEach>
        </div>
    </div>
</body>

</html:html>
