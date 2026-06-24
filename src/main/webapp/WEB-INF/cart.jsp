<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %> 
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ショッピングカート一覧</title>
</head>
<body>
<h3>ショッピングカート内の商品一覧</h3>

	<table border="1">
		<tr>
			<th>商品名</th>	<th>商品の色</th>	<th>メーカー名</th> <th>単価</th> <th>数量</th> <th>　　</th>
			
		</tr>
		
		<c:set var="total" value="0"></c:set>
		
		<c:forEach var="cart" items="${cartList}">
			<tr>
				<td><c:out value="${cart.itemsDto.name }"></c:out></td>
	 			<td><c:out value="${cart.itemsDto.color }"></c:out></td>
				<td><c:out value="${cart.itemsDto.manufacturer }"></c:out></td>
				<td><fmt:formatNumber value="${cart.itemsDto.price }"/></td>
				<td><c:out value="${cart.amount }"></c:out></td>
				<td>
				<a href="${pageContext.request.contextPath}/cartDeleteConfirm?itemId=${cart.itemsDto.item_id}">削除</a>
				</td>			
			</tr>
			<c:set var="total" value="${total + cart.itemsDto.price * cart.amount}" />
		</c:forEach>
	</table>
	合計 <fmt:formatNumber value="${total}"/>  円<br>
	<form action="${pageContext.request.contextPath}/purchaseConfirm" method="get">
    <button type="submit">購入する</button>
	</form>
	<br>
	<br>
	<a href="${pageContext.request.contextPath}/main">商品検索</a>へ
	
	
	
</body>
</html>