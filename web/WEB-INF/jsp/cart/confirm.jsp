<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <base href="<%=basePath%>" />
	<title>当当网订单确认页面</title>
	<script type='text/javascript' src='/dwr/interface/cityDao.js'></script>
	<script type='text/javascript' src='/dwr/interface/addressDao.js'></script>
	<script type='text/javascript' src='/dwr/engine.js'></script>
	<script type='text/javascript' src='/dwr/util.js'></script>
	
	<script type="text/javascript" src="/js/confirm.js" charset="gbk"></script>
	<script type="text/javascript" src="/js/common.js" charset="gbk"></script>
	<script type="text/javascript" src="/js/msg.js" charset="gbk"></script>
	<script type="text/javascript" src="/js/validator.js" charset="gbk"></script>
	<style type="text/css" media="screen">
		@import url("css/global.css");
		@import url("css/menu.css");
		@import url("css/confirm.css");
	</style>
	
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />
	<meta http-equiv="expires" content="0" />    

  </head>
  
  <body>
    <jsp:include page="../common/title.jsp"></jsp:include>
    
    <div id="logoBar">
   	  <div id="logo"></div>
    </div>
    <form action="cartConfirm.do" method="post" name="cartConfirmForm">
    <div id="confirm">
    	<div class="hr"></div>
		<div id="status"><a href="/cart.jhtml">我的购物车</a></div>
        <p id="caption">请确认以下信息，然后提交订单</p>
        <div id="orderPane">
       	  	<div class="pane">
            	<p>收货人信息 &nbsp;&nbsp;&nbsp; <a href="#">[关闭]</a></p>
                <ul id="ulContent" class="content">
                	<c:forEach items="${cartConfirmForm.addresses}" var="address">
                	<li>
                		<c:if test="${address.default_ == 0}">
                    		<input id="rdoAddress" type="radio" name="address" value="${address.id}" onclick="newAddress(this);" checked="checked"/>
                    	</c:if>
                    	<c:if test="${address.default_ != 0}">
                    		<input id="rdoAddress" type="radio" name="address" onclick="newAddress(this);" value="${address.id}"/>
                    	</c:if>
                        ${address.consignmentname}，${address.cityByCountry.name} ${address.cityByProvince.name} ${address.cityByCity.name} ${address.cityByDistrict.name}${address.address} ${address.postcode} ${address.phone} ${address.mobile}
                    </li>
                    </c:forEach>
                    <li>
                    	<input id="rdoAddress" type="radio" name="address" onclick="getCountries();newAddress(this);" value="-1" />创建新地址
                    </li>
                </ul>
                <div id="confEntry">
                	<span id="errMessage">添加错误验证错误消息</span>
                	<ul class="confAddress">
                    	<li class="addressLabel">收货人:</li>
                        <li class="addressContenxt">
                        <input type="text" class="txt" name="consignmentName" 
                               id="txtConsignmentName" maxlength="50" size="25" />
                        <input type="hidden" name="cusid" id="txtCusid"
                        value="${sessionScope['com.globalite.dangdang.constants.customername'].id}"/>
                        </li>
                    </ul>
                    <ul class="confAddress">
                    	<li class="addressLabel">详细地址:</li>
                        <li class="addressOptions">
                    	国家:<select id="sltCountry" name="country" onchange="getProvinces();">
                        	<option value="000" selected="selected">-请选择-</option>
                        </select>&nbsp;&nbsp;
                        省份:<select id="sltProvince" name="province" onchange="getCities();">
                        	<option value="000" selected="selected">-请选择-</option>
                        </select>&nbsp;&nbsp;
                        城市:<select id="sltCity" name="city" onchange="getDistricts();">
                        	<option value="000" selected="selected">-请选择-</option>
                        </select>&nbsp;&nbsp;
                        区县:<select id="sltDistrict" name="district">
                        	<option value="000" selected="selected">-请选择-</option>
                        </select>
                        </li>
                    </ul>
                    <ul class="confAddress">
                    	<li class="addressLabel">详细地址:</li>
                        <li class="addressDetail">
                        <input type="text" class="txt" name="address" 
                               id="txtAddress"  maxlength="200" size="50" />
                        </li>
                    </ul>
                    <ul class="confAddress">
                    	<li class="addressLabel">邮政号码:</li>
                        <li class="addressContenxt">
                        <input type="text" class="txt" name="postCode" 
                               id="txtPostCode" maxlength="10" size="25" />
                        </li>
                        <li class="addressLabel">家庭电话:</li>
                        <li class="addressContenxt">
                        <input type="text" class="txt" name="phone" 
                               id="txtPhone" maxlength="15" size="25" />
                        </li>
                        <li class="addressLabel">移动电话:</li>
                        <li class="addressContenxt">
                        <input type="text" class="txt" name="mobile" 
                               id="txtMobile" maxlength="20" size="25" />
                        </li>
                    </ul>
                </div>
                <div class="confBtn">
                	<span class="btn_confirm" onclick="btn_click();">确认收货地址</span>
                </div>
            </div>

            <div class="pane">
            	<p>支付方式&nbsp;&nbsp;&nbsp;<a href="#">[关闭]</a></p>
                <ul class="content">
                	<li>
                    	<input type="radio" name="payType" value="0" checked="checked"/>
                        货到付款<s>您需要在收货时用现金向送货员支付订单款项。</s>
                    </li>
                   	<li>
                    	<input type="radio" name="payType" value="1" />
                        网上支付<s>您需要先拥有一张已开通网上支付功能的银行卡。</s>
                    </li>
                    <li>
                    	<input type="radio" name="payType" value="2" />
                        银行汇款<s>您需要先去银行转帐，所购商品将在款项到达当当网帐户后发出，到款时间一般为1-5个工作日。</s>
                    </li>
                </ul>
                <div class="confBtn">
                	<span class="btn_confirm">确认支付方式</span>
                </div>
            </div>
            
            <div class="pane">
            	<p>送货方式&nbsp;&nbsp;&nbsp;<a href="#">[关闭]</a></p>
                <ul class="content">
                	<li>
                    	<input type="radio" name="deliverPrice" value="5.00" checked="checked"
                        onclick="cal(this);" />
                        运费：05.00元<s>普通运送方式 全天运送。</s>
                    </li>
                    <li>
                    	<input type="radio" name="deliverPrice" value="12.00" 
                    	onclick="cal(this);" />
                        运费：12.00元<s>EMS全国运送统一价格。</s>
                    </li>
                    <li>
                    	<input type="radio" name="deliverPrice" value="22.00" 
                    	onclick="cal(this);" />
                        运费：22.00元<s>加急，当天送达(只限江浙沪地区用户)</s>
                    </li>
                    <li>
                    	<input type="radio" name="deliverPrice" value="0.00" 
                        onclick="cal(this);"/>
                        运费：免费<s>自提，当当目前全国拥有1997个自提点</s>
                    </li>
                </ul>
                <div class="confBtn">
                	<span class="btn_confirm">确认送货方式</span>
                </div>
            </div>
            
			<div class="pane">
            	<p>开具发票&nbsp;&nbsp;&nbsp;<a href="#">[关闭]</a></p>
                <ul class="content">
                	<li>
                        <b>发票类型:</b>
       					
                        <input id="rdonone" class="invoiceRdo"
                        type="radio" name="invoiceType" value="0" checked="checked" />								
                        <label for="rdonone">无需发票</label>
                        
                        <input id="rdoPerson" class="invoiceRdo"
                        type="radio" name="invoiceType" value="1" />
                        <label for="rdoPerson">个人发票</label>

                        <input id="rdoCompany" class="invoiceRdo" 
                        type="radio" name="invoiceType" value="2" />
                        <label for="rdoCompany">公司发票</label>
                    </li>
                    <li>
                    	<b>发票类型:</b>
                        <select name="invoiceContent">
                        	<option value="0">图书</option>
                            <option value="1">影像制品</option>
                            <option value="2">办公用品</option>
                            <option value="3">日用品</option>
                            <option value="4">其他</option>
                        </select>
                    </li>
                   
                </ul>
                <div class="confBtn">
                	<span class="btn_confirm">确认发票信息</span>
                </div>
            </div>
            
            <div class="pane" style="border:none;">
            	<p>您的商品信息&nbsp;&nbsp;&nbsp;<a href="#">[关闭]</a></p>
                <ul class="content">
                	<li>
                    	商家：当当网  预计3-5天送达
                    </li>
                </ul>
                <div id="shopping">
                    <ul class="th">
                        <li class="bookname">商品名</li>
                        <li class="td">数量</li>
                        <li class="td">价格</li>
                        <li class="td">折扣</li>
                        <li class="td">小计</li>
                    </ul>
                    <c:forEach items="${requestScope['com.globalite.dangdang.constants.shoppingcart'].items}" var="item">
                    <ul class="tr">
                        <li class="data_char bookname">
                            ${item.book.own.name}
                        </li>
                        <li class="data_date">${item.quantity}</li>
                        <li class="data_date">￥${item.book.own.price}</li>
                        <li class="data_date">${item.book.discount}折</li>
                        <li class="data_date">￥${item.subtotal}</li>
                    </ul>
                    </c:forEach>
                    <div class="price">商品金额总计：￥<span id="sPrice">${requestScope['com.globalite.dangdang.constants.shoppingcart'].totalPrice}</span></div>
                    <div class="price">商品运费：<s>￥</s><s id="dPrice">5.00</s></div>
                    <div class="price"><b>您需支付(含运费)：</b><s>￥</s><s id="tPrice">450.60</s>
                    </div>
                   	<script type="text/javascript" >
                   		calTotalPrice();
                   	</script>
                    <div class="shopping_btn">
                    	<span class="btn_submit" onclick="doSubmit('cartConfirmForm');"></span>
                    	<input type="hidden" name="op" value="confirm" />
                    </div>
            	</div>                    
            </div>
        </div>
    </div>
    </form>
  </body>
</html>
