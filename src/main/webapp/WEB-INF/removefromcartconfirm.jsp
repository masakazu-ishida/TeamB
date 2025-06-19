<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>以下の商品をショッピングカートから削除してよろしいですか？</h3>
		<br />
	    <c:out value="${dto.items.name}"/><br />
		<c:out value="${dto.items.manufacturer}"/><br />
		<c:out value="${dto.items.price*dto.amount}"/>円<br />
		<p>数量<c:out value="${dto.amount}"/>個<br /> 
		<form action='removeFromCartCommit.html' method='POST'>
			<input type='hidden' name='itemId' value='123' />
			<input type='submit' value='削除する' /><br />
		</form><br>
		
		<a href='/SearchController'>商品検索</a>へ<br />

</body>
</html>