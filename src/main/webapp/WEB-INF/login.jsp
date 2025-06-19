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
		<form action='main.html' method='POST'>
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
			<input tyoe='hidden' name='リダイレクト' value='login'/>
		</form>
		<p>

</body>
</html>