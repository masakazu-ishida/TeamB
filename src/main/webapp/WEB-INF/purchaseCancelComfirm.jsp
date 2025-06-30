<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>注文キャンセル確認画面</title>
<style type="text/css">
body {
	text-align: center;
    font-family:serif;
    font-size: 30px;
}

table{
	margin: 0 auto;
	background-color: white;
}

th{
	background-color: aqua;
}

input{
	font-family: serif;
	font-size: 20px;
}
select {
  font-size: 18px; /* ルート要素と同じサイズ */
  font-family:serif;
}
</style>
	</head>
<body>
<h3>以下の注文をキャンセルしてよろしいですか？</h3>
		<br />
		<table border="1">
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
					<table border="1">
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
					<form action="/axis_b/adminPurchaseCancelCommitController" method="POST">
						<input type='hidden' name='purchaseId' value='${dto.purchaseId}' />
						<input type='submit' value='キャンセル' /><br />
					</form>
				</td>
			</tr>
		</table>
		<br />
<p><a href="/axis_b/mainController">管理者メインページ</a>へ</p>
</body>
</html>