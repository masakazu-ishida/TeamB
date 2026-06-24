<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>taka商品の購入確認</title>

</head>

<body>

<h3>以下の商品を購入しますか？</h3>
	<table border="1">
		<tr>
			<th>商品名</th>	<th>商品の色名</th>	<th>メーカー名</th> <th>単価</th> <th>数量</th>
		</tr>
		
		<c:set var="total" value="0"></c:set>
		
		<c:forEach var="cart" items="${cartList}">
			<tr>
				<td><c:out value="${cart.itemsDto.name }"></c:out></td>
	 			<td><c:out value="${cart.itemsDto.color }"></c:out></td>
				<td><c:out value="${cart.itemsDto.manufacturer }"></c:out></td>
				<td><fmt:formatNumber value="${cart.itemsDto.price }"/></td>
				<td><c:out value="${cart.amount }"></c:out></td>			
			</tr>
			<c:set var="total" value="${total + cart.itemsDto.price * cart.amount}" />
		</c:forEach>
	</table>
	合計 <fmt:formatNumber value="${total}"/>  円<br>
	
	
	<form action="${pageContext.request.contextPath}/purchaseCommit">
	<p>精算方法<br>
		<select name="payment">
  			<option selected>代金引換</option>
		</select> 
	</p>
	
	<p>配送先<br>
		<input type="radio" name="destination" value="${address}" checked>ご自宅<br><br>
		<input type="radio" name="destination" value="another">配送先を指定<br>
		ご住所<br>
		<input type="text" name="address"><br><br>
	</p>
	
	
	<p>購入しますか？<br>
		<input type="submit" value="購入する">
	</p><br>
	</form>
	
	<a href="${pageContext.request.contextPath}/main">商品検索</a>へ<br>
	


</body>
</html>