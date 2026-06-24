<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報</title>
</head>
<body>
	<h3>会員情報</h3>
		<br />
		<form action="${pageContext.request.contextPath}/main" method='POST'>
			<table border="1">
				<tr>
					<th>会員ID</th>
					<td><c:out value="${user.userId}" /></td>
				</tr>
				<tr>
					<th>お名前</th>
					<td><c:out value="${user.name}" /></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td></td>
				</tr>
				<tr>
					<th>パスワード(確認)</th>
					<td></td>
				</tr>
				<tr>
					<th>ご住所</th>
					<td><c:out value="${user.address}" /></td>
				</tr>
				<tr>
					<td colspan='2'><input type='submit' value='変更' /></td>
				</tr>
			</table>
		</form>
		
		<a href="${pageContext.request.contextPath}/purchaseHistory">購入履歴</a>へ<br>
		<a href="${pageContext.request.contextPath}/main">商品検索</a>へ<br>

</body>
</html>