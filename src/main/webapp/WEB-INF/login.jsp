<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員ログイン</title>
</head>
<body>

<h3>ログインしてください。</h3>
		<br />
		<form action='/axis_b/LoginConfirmController' method='POST'>
			<table>
				<tr>
					<th>会員ID</th>
					<td><input type='text' class='id' name='会員Id' /></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type='password' class='password' name='パスワード' /></td>
				</tr>
				<tr>
					<td colspan='2'><input type='submit' value='ログイン' /></td>
				</tr>
			</table>
			<input type="hidden" name="遷移元" value="${ 遷移元 }">
			<input type="hidden" name="商品Id" value="${ 商品Id }">
			<input type="hidden" name="数量" value="${ 数量 }">
		</form>
	<%
		String message = (String)request.getAttribute("message");
		if(message != null){
	%>
		<%= message %>
	<%	
		}
	%>
		
</body>
</html>