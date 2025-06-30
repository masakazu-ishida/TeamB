<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員ログイン</title>
<<<<<<< HEAD
<style type="text/css">
body {
	text-align: center;
    font-family:serif;
    font-size: 30px;
}

table{
	margin: 0 auto;
	background-color: white;
}

th{
	background-color: aqua;
}

input{
	font-family: serif;
	font-size: 20px;
}
select {
  font-size: 18px; /* ルート要素と同じサイズ */
  font-family:serif;
}
</style>
=======

>>>>>>> branch 'master' of https://github.com/masakazu-ishida/TeamB.git
</head>
<body>
	<h3>管理者ログイン</h3>
		<br />
		<form action='/axis_b/AdminLoginConfirmController' method='POST'>
			<table>
				<tr>
					<th>管理者ID</th>
					<td><input type='text' class='id' name='adminId' /></td>
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