<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品詳細表示</title>
</head>
<body>
<h3>商品の詳細表示</h3>

<table>
			<tr>
				<th>商品名</th>
				<td><c:out value="${dto.name}"/></td>
			</tr>
			<tr>
				<th>商品の色</th>
				<td><c:out value="${dto.color}"/></td>
			</tr>
			<tr>
				<th>メーカー名</th>
				<td><c:out value="${dto.manufacturer}"/></td>
			</tr>
			<tr>
				<th>価格</th>
				<td><c:out value="${dto.price}"/></td>
			</tr>
			<tr>
				<th>在庫数</th>
				<td><c:out value="${dto.stock}"/></td>
			</tr>
		</table>
	
	
<form action='cart.html' method='POST'>
			数量
			<select name='amount'>
				<option selected value='1'>1</option>
				<option value='2'>2</option>
				<option value='3'>3</option>
				<option value='4'>4</option>
				<option value='5'>5</option>
			</select><br />
			<input type='hidden' name='itemId' value='123' />
			<input type='submit' value='ショッピングカートに入れる' /><br />
			<a href='/axis_b/LoginController'>ログイン画面</a><br />
		</form>
	
	
</table>
	

</body>
</html>