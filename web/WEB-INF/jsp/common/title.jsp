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

<div id="navigate">
	<div id="rang">
		<ul class="login">
			<li>
				您好,&nbsp;
				<%
					Customer cus = (Customer) session
									.getAttribute(Constants.CUSTOMER_NAME);
							if (cus == null) {
				%>
				<a href="/index.jhtml">登录当当</a>&nbsp;&nbsp;
				<%
					} else {
				%>
				<%=cus.getName() + "&nbsp;&nbsp;"%>
				<!--  <a href="/MainLogonServlet">退出</a>-->
				<a href="/logon.jhtml">退出</a>
				<%
					}
				%>
				<a href="/reg.do?flag=init">免费注册</a>
			</li>
		</ul>
		<ul class="content">
			<li>
				<a href="/index.jhtml">联系我们</a>
			</li>
			<li>
				<s class="search">&nbsp;</s><a href="/index.jhtml">搜索</a>
			</li>
			<li>
				<a href="/myself.do?op=load">我的当当</a>
			</li>
			<li>
				<a href="/orders.do?op=load">我的订单</a>
			</li>
			<%  
				if(cus != null) {
					ShoppingCart cart = (ShoppingCart)session.getAttribute(Constants.SHOPPING_CART);
	            	
            		pageContext.setAttribute("current_cart",cart);
            	}
            %>
			<li class="sc">
				<s class="shoppingCart">&nbsp;</s>
				<a href="/cart.jhtml">
				<c:if test="${current_cart == null}">
				购物车<span id="mailNumber">(0)</span>
				</c:if>
				<c:if test="${current_cart != null}">
				购物车<span id="mailNumber">(${current_cart.quantity})</span>
				</c:if>
				</a>
			</li>
			<li>
				欢迎光临
			</li>
		</ul>
	</div>
</div>
