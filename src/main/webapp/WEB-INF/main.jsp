<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索</title>
<script type="text/javascript">
function login(){
	if()
}
</head>
<body>
	<h3>商品の検索を行います。</h3>
		<br />
	<form action='' method='POST'>
		キーワード<br />
		<input type='text' name='keyword' /><br />
			カテゴリ<br />
			<select name='category'>
				<option selected value=1'>すべて</option>
				<option value='2'>帽子</option>
				<option value='3'>鞄</option>
			</select><br />
		<input type='submit' value='検索' /><br />
	</form>
	
	<a href='cart.html'>ショッピングカートを見る</a><br /><br />
	<%
		String a = request.getParameter();
		if(a == null){
			<div id="conn"><a href='login.html'>ログイン</a></div>
		}

		<a href='updateUser.html'>会員情報の変更</a><br />
	%>

</body>
</html>