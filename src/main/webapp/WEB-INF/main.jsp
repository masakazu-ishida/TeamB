<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索</title>
</head>
<body>
	<h3>商品の検索を行います。</h3><br>
		<form action="searchResult.jsp" method="GET">
			キーワード<br>
			<input type="text" name="keyword"><br>
			カテゴリ<br>
			<select name="category">
				<option selected value='1'>すべて</option>
				<option value='2'>帽子</option>
				<option value='3'>鞄</option>
			</select><br>
			<input type="submit" value="検索"><br>
		</form>
		
		
		<a href="cart.jsp">ショッピングカートを見る</a><br>
		<c:if test="${empty SESSION}">
		<!-- ※ログインしていない場合 --><br>
		<a href="login.jsp">ログイン</a><br></c:if>
		<c:if test="${not empty SESSION}">
		<!-- ※ログイン済の場合には以下を表示--><br>
		<a href="updateUser.jsp">会員情報の変更</a><br></c:if>

</body>
</html>