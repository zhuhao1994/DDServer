<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<div id="logoBar">
	<div id="logo">
	</div>
	<div id="circularMenu">
		<div id="circularMenuTop">
			网上购物享当当
		</div>
		<div id="circularMenuTitle">
			<ul>
				<li>
					<a href="/books.jhtml">首页</a>
				</li>
				<li>
					<a href="/books.jhtml">图书</a>
				</li>
				<li>
					<a href="#">百货</a>
				</li>
				<li>
					<a href="#">品牌</a>
				</li>
				<li>
					<a href="#">促销</a>
				</li>
			</ul>
		</div>
	</div>
	<form action="/MainBooksServlet" method="post" name="booksForm">
		<div id="search">
			<s class="imgSearch">&nbsp;</s>
			<input type="text" class="textSeatch" name="condition" />
			<p class="btnSearch" onclick="doSubmit('booksForm');"></p>
		</div>
	</form>
</div>

<div id="menu">
	<div id="rang">
		<ul class="items">
			<li>
				小说
			</li>
			<li>
				青春
			</li>
			<li>
				历史
			</li>
			<li>
				心理学
			</li>
			<li>
				保健
			</li>
			<li>
				少儿
			</li>
			<li>
				外语
			</li>
			<li class="img">
				考试
				<s></s>
			</li>
			<li>
				中小学教辅
			</li>
			<li>
				图书畅销榜
			</li>
			<li>
				新书热卖榜
			</li>
			<li class="img">
				特价书
				<s></s>
			</li>
			<li>
				图书畅销
			</li>
			<li>
				所有图书分类
			</li>
			<li>
				精品套装图书
			</li>
		</ul>
	</div>
</div>