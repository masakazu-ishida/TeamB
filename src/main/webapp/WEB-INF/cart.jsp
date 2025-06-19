<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ショッピングカート</title>
</head>
<body>
	<h3>ショッピングカート内の商品一覧</h3><br>
	<table>
	<tr><th>商品名</th><th>商品の色</th><th>メーカー名</th><th>単価</th><th>数量</th><th>　</th></tr>
	<tr><c:forEach var="dto" items="${dtoList}">
    	<p><c:out value="${dto.name}" /></p>
    	<p><c:out value="${dto.color}"/></p>
    	<p><c:out value="${dto.manufacturer}"/></p>
    	<p><c:out value="${dto.price}"/></p>
    	<p><c:out value="${dto.amount}"/></p>
    	<p><a href="/axis_b/RemoveFromCartConfirmController?itemId=${dto.itemid }">削除</a></p>
		</c:forEach>
	</tr>
	</table><br>
	<p>合計<c:out value="${sum}"/>円</p><br>
	<form action = "/axis_b/PurchaseConfirmController" method="post">
		<input type="submit" value="購入する">
	</form>
	<br>
	<br>
	<p><a href ="/axis_b/mainController">商品検索</a>へ</p>
	
</body>
</html>