<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索結果</title>
</head>
<body>
	<h3>カテゴリ"<c:out value="${categoriesId}"/>"
		/キーワード"<c:out value="${keyword}"/>"の検索結果</h3> 
	
	<table border="1">
	<tr><th>商品名</th><th>商品の色</th><th>メーカー名</th><th>価格</th></tr>
	<c:forEach var="dto" items="${list}">
	<tr>
		<td><p><a href="/axis_b/ItemDetailController?itemId=${dto.itemId }"><c:out value="${dto.name}" /></a></p></td>
    <td>	<p><c:out value="${dto.color}"/></p></td>
    <td>	<p><c:out value="${dto.manufacturer}"/></p></td>
    <td>	<p><c:out value="${dto.price}"/></p></td>
    	</tr>
		</c:forEach>
	
	</table><br>
	
	
	前へ
		<a href='SearchController?page=1'>1</a> 
		<a href='SearchNextPageController?page=2'>2</a> 
		<a href='SearchNextPageController?page=3'>3</a> 
		<a href='SearchNextPageController?page=4'>4</a> 
		<a href='SearchNextPageController?next=0'>次へ</a> 
		
		<input type="hidden" value="${page}" name ="current">
		
	<a href='/axis_b/mainController'>商品検索</a>へ<br />

	
</body>
</html>