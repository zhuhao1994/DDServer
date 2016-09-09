<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>

<%@ page import="com.globalite.dangdang.common.Constants"%>
<%@ page import="com.globalite.dangdang.entity.Customer"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<title>当当网书籍查询页面</title>
		<link rel="stylesheet" href="/css/global.css" type="text/css"
			media="all" />
		<link rel="stylesheet" href="/css/books.css" type="text/css"
			media="all" />
		<link rel="stylesheet" href="/css/menu.css" type="text/css"
			media="all" />
		<script type="text/javascript" src="/js/common.js" charset="gbk"></script>
	</head>

	<body>
		<jsp:include page="../common/title.jsp"></jsp:include>
		<jsp:include page="../common/head.jsp"></jsp:include>
		<div id="dataBar">
			<c:forEach items="${booksForm.books}" var="book">
				<div class="tr">
					<div class="imgTd">
						<img src="${book.fullImage}" width="154px" height="200px" />
					</div>
					<ul>
						<li class="title">
							<a href="/detail.jhtml?id=${book.own.id}">${book.own.name}</a>
						</li>
						<li>
							${book.own.author} / ${book.own.publisher}
						</li>
						<li class="content">
							${book.introduction}
						</li>
						<li class="btn">
							<ul class="cell">
								<li>
									<img src="img/list/btn_keep.gif"  style="cursor:pointer"/>
								</li>
								<li>
									<img src="img/list/btn_buy.gif"  style="cursor:pointer" 
										onclick="doHref('cart.jhtml?op=add&id=${book.own.id}')"/>
									
								</li>
								<li class="discount">
									折扣：${book.discount}折
								</li>
								<li class="oldprice">
									￥${book.commonprice}
								</li>
								<li class="price">
									￥${book.price}
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</c:forEach>
		</div>
	</body>
</html>
