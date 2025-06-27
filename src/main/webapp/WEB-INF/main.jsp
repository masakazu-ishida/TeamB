<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<h3>商品の検索を行います。</h3>
		<br />
	<form action="/axis_b/SearchController" method='POST'>
		キーワード<br />
		<input type='text' name='keyword' /><br />
			カテゴリ<br />
			<select name='categoriesId'>
				<option selected value='0'>すべて</option>
				<option value='1'>帽子</option>
				<option value='2'>鞄</option>
			</select><br />
		<input type='submit' value='検索' /><br />
	</form>
	
	<a href="/axis_b/CartController?h=cart">ショッピングカートを見る</a><br /><br />
	<%
		String userId = (String)session.getAttribute("userId");
		if(userId == null){
	%>
		<div id="conn"><a href='/axis_b/LoginController?h=main'>ログイン</a></div>
	<%	
		}
	%>

</body>
</html>