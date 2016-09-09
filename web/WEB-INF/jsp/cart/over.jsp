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
	<title>恭喜您订单已提交-当当网</title>
	<style type="text/css" media="screen">
		@import url("css/global.css");
		@import url("css/menu.css");
		@import url("css/over.css");
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
    
    <div id="over">
    	<div class="hr"></div>
        <div id="status"></div>
        <div id="content">
        	<p class="info">
            	<span class="price">
                	恭喜您，您的订单已提交。 
                    预计1天内出库并发货。<a href="#"> 查看订单状态&gt;&gt;</a>
                </span>
                <span class="confirm">
            	您需要在收货时向送货员支付<s>￥${total}</s>
	           	</span>
            </p>
            <p class="summary"><s>*</s> 您可以在<a href="/orders.do?op=load">“我的订单”</a>中查看或取消您的订单，由于系统需进行订单预处理，您可能不会立刻查询到刚提交的订单
            </p>
        </div>
    </div>    
  </body>
</html>
