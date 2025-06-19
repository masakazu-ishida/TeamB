<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索</title>

</head>
<body>
	<h3>商品の検索を行います。</h3>
		<br />
	<form action='/TeamB/axisb/controller/SearchController' method='POST'>
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
	
	<a href="/axis_b/CartController">ショッピングカートを見る</a><br /><br />
	<%
		String userId = (String)request.getParameter("userId");
		if(userId == null){
	%>
		<div id="conn"><a href='/SearchController'>ログイン</a></div>
	<%	
		}
	%>

</body>
</html>