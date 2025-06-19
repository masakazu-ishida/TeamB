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
<h3>以下の注文をキャンセルしてよろしいですか？</h3>
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
						
						<tr>
							<td><c:out value="${dto.items.name}"/></td>
							<td><c:out value="${dto.items.color}"/></td>
							<td><c:out value="${dto.items.manufacturer}"/></td>
							<td><c:out value="${dto.items.price}"/>円</td>
							<td><c:out value="${dto.purchasesDetails.amount}"/>個</td>
						</tr>
						
					</table>
				</td>
			</tr>
			<tr>
				<th>配送先</th>
				<td><c:out value="${dto.items.destination}"/></td>
			</tr>
			<tr>
				<td colspan='2'>
					<form action="/axis_b/PurchaseConfirmController" method="POST">
						<input type='hidden' name='purchaseId' value='1234' />
						<input type='submit' value='キャンセル' /><br />
					</form>
				</td>
			</tr>
		</table>
		<br />
<a href="/axis_b/mainController">管理者メインページへ</a>
</body>
</html>