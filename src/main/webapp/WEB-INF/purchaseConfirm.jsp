<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品の購入確認</title>
<style>
.error{color: red;}
</style>
</head>
<body>
	<p class=error>
	<c:if test="${not empty error }">
	<c:out value="${error}" />
	</c:if>
	</p>

	<h3>以下の商品を購入しますか？</h3>
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
	<p>合計<c:out value="${sum}"/>円</p><br><br>
	<form action = "/axis_b/purchaseCommitController" method="post">
	<p>清算方法</p>
	<select name="payment">
		<option value="daikin" selected>代金引換</option>
	</select><br><br>
	
	<p>配送先</p>
	<input type ="radio" name="distination" value="home" checked>ご自宅<br><br>
	<input type= "radio" name="distination" value="another">配送先を指定<br>
	ご住所<br>
	<input type="text" name="address"><br><br>
	
	<p>購入しますか？</p>
	
		<input type="submit" value="購入する">
	</form>
	
	
	<br>
	<br>
	<p><a href ="/axis_b/mainController">商品検索</a>へ</p>
</body>
</html>