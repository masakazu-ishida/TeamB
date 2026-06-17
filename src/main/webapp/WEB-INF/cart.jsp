<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
				<td><c:out value="${cart.itemsDto.price }"></c:out></td>
				<td><c:out value="${cart.amount }"></c:out></td>
				<td>
				<a href="${pageContext.request.contextPath}/cart/confirmDelete?itemId=${cart.itemsDto.item_id}">削除</a>
				</td>			
			</tr>
			<c:set var="total" value="${total + cart.itemsDto.price * cart.amount}" />
		</c:forEach>
	</table>
	合計 <c:out value="${total}"> </c:out> 円<br>
	
	

</body>
</html>