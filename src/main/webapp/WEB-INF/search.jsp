<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="style.css">
<meta charset="UTF-8">
<title>検索結果</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h3>カテゴリ"<c:out value="${japanesecategories}"/>"
		/キーワード"<c:out value="${keyword}"/>"の検索結果</h3> 
	
	<table border="1">
	<tr><th>商品名</th><th>商品の色</th><th>メーカー名</th><th>価格</th></tr>
	<c:forEach var="dto" items="${list}">
	<tr>
		<td><p><a href="/axis_b/itemDetailController?itemId=${dto.itemId }"><c:out value="${dto.name}" /></a></p></td>
    <td>	<p><c:out value="${dto.color}"/></p></td>
    <td>	<p><c:out value="${dto.manufacturer}"/></p></td>
    <td>	<p><c:out value="${dto.price}"/></p></td>
    	</tr>
		</c:forEach>
	
	</table><br>
	
	

	<a href='/axis_b/SearchNextPageController?page=${page - 1}&keyword=${ keyword}&categoriesId=${categoriesId}'>前へ</a> 
		<a href='/axis_b/SearchController?page=1&keyword=${ keyword}&categoriesId=${categoriesId}'>1</a> 
		<a href='/axis_b/SearchNextPageController?page=2&keyword=${ keyword}&categoriesId=${categoriesId}'>2</a> 
		<a href='/axis_b/SearchNextPageController?page=3&keyword=${ keyword}&categoriesId=${categoriesId}'>3</a> 
		<a href='/axis_b/SearchNextPageController?page=4&keyword=${ keyword}&categoriesId=${categoriesId}'>4</a> 
		<a href='/axis_b/SearchNextPageController?page=${page + 1}&keyword=${ keyword}&categoriesId=${categoriesId}'>次へ</a> 
		
		<input type="hidden" value="${page}" name ="current">
		
	<a href='/axis_b/mainController'>商品検索</a>へ<br />

	
</body>
</html>