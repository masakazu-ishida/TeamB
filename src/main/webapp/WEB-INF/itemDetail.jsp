<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細</title>
</head>
<body>
	<h3>商品の詳細表示</h3>
		<br>
		<table>
			<tr>
				<th>商品名</th>
				<td><c:out value="${item.name}"/></td>
			</tr>
			<tr>
				<th>商品の色</th>
				<td><c:out value="${item.color}"/></td>
			</tr>
			<tr>
				<th>メーカー名</th>
				<td><c:out value="${item.manufacturer}"/></td>
			</tr>
			<tr>
				<th>価格</th>
				<td><c:out value="${item.price}"/></td>
			</tr>
			<tr>
				<th>在庫数</th>
				<td><c:out value="${item.stock}"/>個</td>
			</tr>
		</table>
		<form action="cart.jsp" method="POST">
			数量
			<select name="amount">
				<option selected value='1'>1</option>
				<option value='2'>2</option>
				<option value='3'>3</option>
				<option value='4'>4</option>
				<option value='5'>5</option>
			</select><br />
			<input type="hidden" name="itemId" value="${item.item_id}">
			<input type="submit" value="ショッピングカートに入れる"><br>
			※ログインしていない状態では、ボタンのクリック後、
			<c:if test="${empty loginUser}">
			<a href="${pageContext.request.contextPath}/login"></a><!--ログイン画面に転送される。--><br></c:if>
		</form>
		<a href="${pageContext.request.contextPath}/main">商品検索へ</a><br>

</body>
</html>