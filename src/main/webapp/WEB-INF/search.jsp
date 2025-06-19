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
	<h3><c:out value1="${categoriesId}"/><c:out value2="${keyword}"/>の検索結果</h3> 
	<table>
	<tr><th>商品名</th><th>商品の色</th><th>メーカー名</th><th>価格</th></tr>
	<tr><c:forEach var="dto" items="${dtoList}">
		<p><a href="/axis_b/SearchController?itemId=${dto.itemid }"><c:out value="${dto.name}" /></a></p>
    	<p><c:out value="${dto.color}"/></p>
    	<p><c:out value="${dto.manufacturer}"/></p>
    	<p><c:out value="${dto.price}"/></p>
		</c:forEach>
	</tr>
	</table><br>
	
		<a href='searchResult.html?keyword=something&category=all&page=2'>2</a> 
		<a href='searchResult.html?keyword=something&category=all&page=3'>3</a> 
		<a href='searchResult.html?keyword=something&category=all&page=4'>4</a> 
		<a href='searchResult.html?keyword=something&category=all&page=2'>次へ</a> 
		
		
	<p><a href ="メインに飛ばすパスを入れる">商品検索</a>へ</p>
	
</body>
</html>