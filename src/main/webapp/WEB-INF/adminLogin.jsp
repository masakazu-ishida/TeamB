<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員ログイン</title>
</head>
<body>
	<h3>管理者ログイン</h3>
		<br />
		<form action='/axis_b/AdminMainController' method='POST'>
			<table>
				<tr>
					<th>管理者ID</th>
					<td><input type='text' class='id' name='id' /></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type='password' class='password' name='password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input type='submit' value='ログイン' /></td>
				</tr>
			</table>
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