<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="d" uri="http://globalite/dangdang/tld/core"%>
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
		<title>当当网书籍详细页面</title>
		<link rel="stylesheet" href="/css/global.css" type="text/css"
			media="all" />
		<link rel="stylesheet" href="/css/detail.css" type="text/css"
			media="all" />
		<link rel="stylesheet" href="/css/menu.css" type="text/css"
			media="all" />
		<script type="text/javascript" src="/js/common.js" charset="gbk"></script>
	</head>

	<body>
		<jsp:include page="../common/title.jsp"></jsp:include>
		<jsp:include page="../common/head.jsp"></jsp:include>
		<div class="dataBar divLine">
			<div class="title">
				${book.own.name}
			</div>
			<div class="content">
				<div class="img">
					<img src="${book.fullImage}" width="200px" height="200px" />
				</div>

				<ul class="info">
					<li>
						<p class="label">
							当 当 价：
						</p>
						<p class="data price">
							￥${book.price}
						</p>
					</li>
					<li>
						<p class="label">
							定 价：
						</p>
						<p class="data">
							￥${book.commonprice}
						</p>
						<p class="label">
							折 扣：
						</p>
						<p class="data discount">
							${book.discount}折
						</p>
					</li>
					<li>
						<p class="label">
							库 存：
						</p>
						<p class="data store">
							${book.stock}
						</p>
					</li>
					<li>
						<p class="label">
							作 者：
						</p>
						<p class="data author">
							${book.own.author}
						</p>
					</li>
					<li>
						<p class="label">
							出 版 社：
						</p>
						<p class="data publisher">
							${book.own.publisher}
						</p>
					</li>
					<li>
						<p class="label">
							页 数：
						</p>
						<p class="data">
							${book.own.pagecount}
						</p>
						<p class="label">
							字 数：
						</p>
						<p class="data">
							${book.own.wordcount}
						</p>
						<p class="label">
							纸 张：
						</p>
						<p class="data">
							${book.own.pagetype}
						</p>
					</li>
					<li>
						<p class="label">
							I S B N：
						</p>
						<p class="data isbn">
							${book.own.isbn}
						</p>
					</li>
				</ul>
				<form action="cart.jhtml" method="post" name="detail_scForm">
				<div class="buyer">
					<input type="hidden" name="id" value="${book.own.id}" />
					<input type="hidden" name="op" value="add" />
					<p class="txt">
						我要买：
						<input type="text" name="number" value="1" maxlength="4"
							size="4" />
						件
					</p>
					<p class="btn">
						<img src="img/list/but_buy.gif"  style="cursor:pointer"
						onclick="doSubmit('detail_scForm');"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<img src="img/list/but_collect.gif"  style="cursor:pointer"/>
					</p>
				</div>
				</form>
			</div>
		</div>

		<div class="bookDetailBar">
			<p class="title">
				编辑推荐
			</p>
			<p class="content">
				${book.own.editorialrecommend}
			</p>
			<p class="title">
				内容简介
			</p>
			<p class="content">
				${book.own.introduction}
			</p>
			<p class="title">
				目录
			</p>
			<p class="content">
				${book.own.catalog}
			</p>
		</div>
		<c:if test="${fn:length(book.own.bookcomments) > 0}">
			<div class="commentsBar">
				<div class="title">
					<span>商品评论（${fn:length(book.own.bookcomments)} 条）</span>
				</div>
				<div class="star">
					<h2>
						购买过的顾客评分
					</h2>
					<d:star redStar="img/list/star_red_big.gif"
						grayStar="img/list/star_gray_big.gif"
						comments="${book.own.bookcomments}" />
				</div>
				<c:forEach items="${book.own.bookcomments}" var="comment">
					<div class="comment">
						<ul>
							<li class="label">
								评论员:
							</li>
							<li class="data">
								${comment.customer.name}
							</li>
							<li class="label">
								评论时间:
							</li>
							<li class="data date">
								${comment.contentdate}
							</li>
							<li class="data userstar">
								${comment.star}星&nbsp;&nbsp;
								<c:forEach begin="0" end="${comment.star-1}">
									<img src="img/list/star_red_big.gif" width="10px" height="10px" />
								</c:forEach>
								<c:if test="${comment.star < 5}">
									<c:forEach begin="0" end="${5-comment.star-1}">
										<img src="img/list/star_gray_big.gif" width="10px"
											height="10px" />
									</c:forEach>
								</c:if>
							</li>
						</ul>
						<div class="content">
							${comment.content}
						</div>
					</div>
				</c:forEach>
			</div>
		</c:if>
	</body>
</html>
