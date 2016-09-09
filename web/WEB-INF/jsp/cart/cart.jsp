<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="com.globalite.dangdang.bo.ShoppingCart" %>
<%@ page import="com.globalite.dangdang.entity.Customer" %>
<%@ page import="com.globalite.dangdang.common.Constants" %>

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
	
	<title>当当网购物车页面</title>
	<script type="text/javascript" src="/js/common.js" charset="gbk"></script>
	<style type="text/css" media="screen">
		@import url("/css/global.css");
		@import url("/css/login.css");
		@import url("/css/cart.css");
		@import url("/css/menu.css");
	</style>
	<!-- IE7以及IE7以下版本可识别,css hack -->
	<!--[if lt IE 7]>  
    <style type="text/css" media="screen">
		html{overflow:hidden;}/*定义html为一屏大小，超出则hidden*/   
		/*body必须定义overflow:auto;否则不出现滚动条   
	   	如果不定义高度，浮层的高度也无从定义   
	   	另外，body还要去除margin，否则滚动条不在最右边，一般在定义css初就应设置这个属性*/   
		body{overflow:auto;height:100%;}   
		/*对IE 6要设置成绝对定位，如果设置right的话，注意右边界是包括滚动条的，试一下right:0;就看出来了*/   
		#mainPane{position:absolute;}
    </style>
	<![endif]-->
  </head>
  
  <body>
  	<div id="logoBar">
    	<div id="logo"></div>
    </div>
    
    <div id="cartPane">
    	<div class="hr"></div>
        <div id="status"></div>
        <div id="onsale">
        	<i></i>
            <span class="detail">
            	当当网&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;购物满29元免运费 
            </span>
        </div>
        <div id="cart">
        	<ul class="th">
            	<li class="bookname">商品名</li>
                <li>数量</li>
                <li>价格</li>
                <li>折扣</li>
                <li>小计</li>
                <li>操作</li>
            </ul>
            <% 
            		ShoppingCart cart = (ShoppingCart)request.getAttribute(Constants.SHOPPING_CART);
            	
            		pageContext.setAttribute("current_cart",cart);
            %>
           
           
            <c:if test="${current_cart == null || current_cart.quantity == 0}">
            <div id="nodata">
            	<s></s><h3>您还没有挑选商品,</h3>
                <span><a href="/books.jhtml">去促销看看&gt;&gt;</a></span>
            </div>
            <div class="hr"></div>
            </c:if>
            <c:if test="${current_cart.quantity > 0}">
            <c:forEach items="${current_cart.items}" var="item">
            <ul class="tr">
                <li class="bookname">
                	<img src="${item.book.fullImage}" />
                	<div class="content">
                    <div class="middle">
                    <div class="inner data_char">
                    	<a href="/detail.jhtml?id=${item.book.own.id}">
                        	${item.book.own.name}
                        </a>
                    </div>
                    </div>
                    </div>
                </li>
                <li class="data_date">
                	<div class="middle">
                    <div class="inner">
                		<input type="text" id="txt${item.book.own.id}"class="txt"
                		maxlength="5" size="5" value="${item.quantity}"/>
                		<a href="javascript:editCart('txt${item.book.own.id}',${item.book.own.id})">修改</a>
                	</div>
                	</div>
                </li>
                <li class="data_date">
	                <div class="middle">
                    <div class="inner">
                    	￥${item.book.own.price}
                    </div>
                	</div>
                </li>
              	<li class="data_date">
                	<div class="middle">
                    <div class="inner">
                    	${item.book.discount}折
                    </div>
                	</div>
                </li>
              	<li class="data_date">
                	<div class="middle">
                    <div class="inner">
                    	￥${item.subtotal}
                    </div>
                	</div>
                </li>
                <li class="data_date">
                    <div class="middle">
                    <div class="inner">
                    <a href="cart.jhtml?op=del&id=${item.book.own.id}">删除</a>
                    </div>
                	</div>
                </li>
            </ul>
            </c:forEach>
        	</c:if>
        </div>
        <c:if test="${current_cart.quantity > 0}">
        <div id="totalprice">
        	<b>商品金额总计：</b> <span>￥${current_cart.totalPrice}</span>
        </div>
        <div id="btn">
            <span class="confirm" onclick="doHref('orderconfirm.jhtml')" ></span>
            <span class="goon" onclick="doHref('books.jhtml')">继续购物</span>
        </div>
        <div id="summary">下单购买商品 -请<a href="javascript:show('visible');">登录</a>后再确认</div>
        </c:if>
    </div>
    <jsp:include page="../common/lightbox.jsp" >
    	<jsp:param value="/WEB-INF/jsp/cart/cart.jsp" name="errurl" />
    	<jsp:param value="/WEB-INF/jsp/cart/cart.jsp" name="okurl"/>
    </jsp:include>
</body>
</html>