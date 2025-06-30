<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>購入履歴一覧</title>
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
	<h3>購入履歴の一覧</h3>
	<table border="1">
	<tr>
				<th>購入者ID</th>
				<th>注文日</th>
				<th>購入商品</th>
				<th>配送先</th>
				<th></th>
	</tr>
	<c:forEach var="list" items="${list}">
	<tr>
		<td><c:out value="${list.purchasedUser}"/></td>
	    <td><c:out value="${list.purchasedDate}"/></td>
    	<td>
    	<table border="1">
    		<tr>
	    		<th>商品名</th>
				<th>色</th>
				<th>メーカー</th>
				<th>単価</th>
				<th>数量</th>
			</tr>
			<c:forEach var="list2" items="${list.purchasesDetails}">
				<tr>
					<td><c:out value="${list2.getItems().name}"/></td>
					<td><c:out value="${list2.getItems().color}"/></td>
					<td><c:out value="${list2.getItems().manufacturer}"/></td>
					<td><c:out value="${list2.getItems().price}"/></td>
					<td><c:out value="${list2.amount}"/></td>
				</tr>
			</c:forEach>
    	</table>
    	</td>
    	
    	<td><c:out value="${list.destination}"/></td>
    	<td>
    	
    		<a href="/axis_b/adminPurchasesCancelController">キャンセル</a>
    	
    	
    	</td>
    	</tr>
    </c:forEach>
</table>
    <br>

	<a href='/axis_b/AdminMainController'>管理者メインページ</a>へ<br />
	
</body>
</html>