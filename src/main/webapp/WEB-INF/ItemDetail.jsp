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
	
	
	前へ
		<a href='/axis_b/SearchController?page=1'>1</a> 
		<a href='/axis_b/SearchNextPageController?page=2&keyword=${ keyword}&categoriesId=${categoriesId}'>2</a> 
		<a href='/axis_b/SearchNextPageController?page=3&keyword=${ keyword}&categoriesId=${categoriesId}'>3</a> 
		<a href='/axis_b/SearchNextPageController?page=4&keyword=${ keyword}&categoriesId=${categoriesId}'>4</a> 
		<a href='/axis_b/SearchNextPageController?page=0&keyword=${ keyword}&categoriesId=${categoriesId}'>次へ</a> 
		
		<input type="hidden" value="${page}" name ="current">
		
	<a href='/axis_b/mainController'>商品検索</a>へ<br />

<h3>商品の詳細表示</h3>
		<form action='/axis_b/CartAddController' method='POST'>
		<table birder = "1">
			<tr>
				<th>商品名</th>
				<td><c:out value="${item.name}"/></td>
			</tr>
			<tr>
				<th>商品の色</th>
				<td><c:out value="${item.color}"/></td>
			</tr>
			<tr>
				<th>メーカー名</th>
				<td><c:out value="${item.manufacturer}"/></td>
			</tr>
			<tr>
				<th>価格</th>
				<td><c:out value="${item.price}"/></td>
			</tr>
			<tr>
				<th>在庫数</th>
				<td><c:out value="${item.stock}"/></td>
			</tr>

	

			数量
			<select name='amount'>
				<option selected value='1'>1</option>
				<option value='2'>2</option>
				<option value='3'>3</option>
				<option value='4'>4</option>
				<option value='5'>5</option>
			</select><br />
			<input type='hidden' name='itemId' value='${item.itemId}'/>
			<input type='submit' value='ショッピングカートに入れる' /><br />
			<input tyoe='hidden' name='hidden' value='cartadd'>
			<a href='/axis_b/LoginController'>ログイン画面</a><br />
		</form>
	
	
</table>
	

</body>
</html>