<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>注文キャンセル確認画面</title>
	</head>
<body>
<h3>以下の注文をキャンセルしました。</h3>
		<br />
		<table>
			<tr>
				<th>注文者ID</th>
				<td><c:out value="${dto.purchasedUser}"/></td>
			</tr>
			<tr>
				<th>注文日</th>
				<td><c:out value="${dto.purchasedDate}"/></td>
			</tr>
			<tr>
				<th>購入商品</th>
				<td>
					<table>
						<tr>
							<th>商品名</th>
							<th>色</th>
							<th>メーカー</th>
							<th>単価</th>
							<th>数量</th>
						</tr>
						<c:forEach var="detailItem" items="${dto.purchasesDetails}">
						<tr>
							<td><c:out value="${detailItem.getItems().name}"/></td>
							<td><c:out value="${detailItem.getItems().color}"/></td>
							<td><c:out value="${detailItem.getItems().manufacturer}"/></td>
							<td><c:out value="${detailItem.getItems().price}"/>円</td>
							<td><c:out value="${detailItem.amount}"/>個</td>
						</tr>
						</c:forEach>
						
					</table>
				</td>
			</tr>
			<tr>
				<th>配送先</th>
				<td><c:out value="${dto.destination}"/></td>
			</tr>
			<tr>
				<td colspan='2'>
					
				</td>
			</tr>
		</table>
		<br />
<p><a href="/axis_b/AdminMainController">管理者メインページ</a>へ</p>
</body>
</html>