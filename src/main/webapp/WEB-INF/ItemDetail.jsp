<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<link href="style.css" rel="stylesheet" type="text/css">
<meta charset="UTF-8">
<title>検索結果</title>
</head>
<body>

		
	

<h3>商品の詳細表示</h3>
		<form action='/axis_b/CartAddController' method='POST'>
		<table border = "1">
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
</table>
	

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
	
			
			<input type='hidden' name='hidden' value='cartadd'>
			
		</form>
	
	

	
<a href='/axis_b/mainController'>商品検索</a>へ<br />
</body>
</html>