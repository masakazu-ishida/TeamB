<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品の購入完了</title>
</head>
<body>
	<h3>以下の商品の購入を完了しました。</h3><br>
	<table border = "1">
	<tr><th>商品名</th><th>商品の色</th><th>メーカー名</th><th>単価</th><th>数量</th></tr>

		<c:forEach var="dto" items="${dtoList}">
		<tr>
    	<td><c:out value="${dto.getItems().name}" /></td>
    	<td><c:out value="${dto.getItems().color}"/></td>
    	<td><c:out value="${dto.getItems().manufacturer}"/></td>
    	<td><c:out value="${dto.getItems().price}"/></td>
    	<td><c:out value="${dto.amount}"/></td>
		</tr>
		</c:forEach>
	
	</table>
	<p>合計<c:out value="${sum}"/>円</p><br>
	<br>
	<br>
	<p>清算方法　<c:out value="${payment}" /></p><br>
	<p>配送先　<c:out value="${setdestination}" /></p>
	<c:if test="${not empty address }">
	<c:out value="${address}" />
	</c:if>
	<p><a href ="/axis_b/mainController">商品検索</a>へ</p>
	
</body>
</html>