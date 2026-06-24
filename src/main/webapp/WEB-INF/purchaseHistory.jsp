<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入履歴</title>
</head>
<body>
	<h3>購入履歴の一覧</h3><br>
	
	
	<c:if test="${empty list}">
		<p>購入履歴はありません</p>
	</c:if>
	
	<c:if test="${not empty list }">
		<c:forEach var="p" items="${list}">
		<table border='1'>
			<tr>
				<th>注文日</th>
				<th>購入商品</th>
				<th>配送先</th>
				<th></th>
			</tr>
			<tr>
				<td><c:out value="${p.purchased_date}" /></td>
				<td>
				<table>
					<tr>
						<th>商品名</th>
						<th>色</th>
						<th>メーカー</th>
						<th>単価</th>
						<th>数量</th>
					</tr>
				<c:forEach var="d" items="${p.purchaseDetailsDto}">
				<tr>
					<td><c:out value="${d.idto.name}"/></td>
					<td><c:out value="${d.idto.color}"/></td>
					<td><c:out value="${d.idto.manufacturer}"/></td>
					<td><fmt:formatNumber value="${d.idto.price}" />円</td>
					<td><c:out value="${d.amount}"/>個</td>
				</tr>
				</c:forEach>
				</table>
				</td>
				<td><c:out value="${empty p.destination ? 'ご自宅' : p.destination}" /></td>
				<td>
					<c:choose>
					<c:when test="${p.cancel}">
					<p>キャンセル済み</p>
					</c:when>
					<c:otherwise>
					<form action="${pageContext.request.contextPath}/cancelConfirm" method="POST">
					<input type="hidden" name="purchaseId" value="${p.purchase_id}" />
					<button type="submit">キャンセル</button>
					</form>
					</c:otherwise>
					</c:choose>
				</td>
			</tr>
			</tr>
		</table>
		
		</c:forEach>
		</c:if>
		<br>
		<a href="${pageContext.request.contextPath}/main">商品検索</a>へ<br>

</body>
</html>