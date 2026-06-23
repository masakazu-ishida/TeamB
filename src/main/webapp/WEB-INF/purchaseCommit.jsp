<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>taka商品の購入確認</title>

</head>

<body>

<h3>以下の商品を購入しました</h3>
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
				<td><c:out value="${cart.itemsDto.price }"></c:out></td>
				<td><c:out value="${cart.amount }"></c:out></td>			
			</tr>
			<c:set var="total" value="${total + cart.itemsDto.price * cart.amount}" />
		</c:forEach>
	</table>
	合計 <c:out value="${total}"> </c:out> 円<br>
	
	<p>清算方法 <c:out value="${payment}"></c:out></p>
	<p>配送先 <c:choose>
        <%-- ラジオボタンで「配送先を指定」を選んだ時（another）は、入力された住所を出す --%>
        <c:when test="${param.destination == 'another'}">
            <c:out value="${address}" />
        </c:when>
        
        <%-- それ以外（初期選択の自宅の時）は、文字通り「ご自宅」と表示する --%>
        <c:otherwise>
            ご自宅
        </c:otherwise>
    </c:choose></p>
	
	
	
	
	<a href="${pageContext.request.contextPath}/main">商品検索へ</a><br>
	


</body>
</html>