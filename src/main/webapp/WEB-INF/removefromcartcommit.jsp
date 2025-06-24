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
<h3>以下の商品をショッピングカートから削除しました。</h3>
		<br />
		<c:out value="${dto.items.name}"/><br />
		<c:out value="${dto.items.manufacturer}"/><br />
		<c:out value="${dto.items.price*dto.amount}"/>円<br />
		<p>数量<c:out value="${dto.amount}"/>個<br />
		<a href='/axis_b/CartController'>ショッピングカート内一覧</a>へ<br /></body>
		<a href='/axis_b/mainController'>商品検索</a>へ<br /></body>
</html>