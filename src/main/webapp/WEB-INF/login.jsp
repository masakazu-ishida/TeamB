<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員ログイン</title>

<style type="text/css">
body {
	background-color: #dedede;
	background-image:
	 linear-gradient(#ecebeb 50%, transparent 50%, transparent);
	background-size: 10px 10px;
	text-align: center;
    font-family:serif;
    font-size: 30px;
}

table{
	margin: 0 auto;
	background-color: white;
}

th{
	background-color: orange;
}

input{
	font-family: serif;
	font-size: 20px;
}
</style>

</head>
<body>

<h3>ログインしてください。</h3>
		<br />
		<form action='/axis_b/LoginConfirmController' method='POST'>
			<table>
				<tr>
					<th>会員ID</th>
					<td><input type='text' class='id' name="userId" /></td>
				</tr>
				<tr>
					<th>パスワード</th>
					<td><input type='password' class='password' name="password" /></td>
				</tr>
				<tr>
					<td colspan='2'><input type='submit' value='ログイン' /></td>
				</tr>
			</table>
			<input type="hidden" name="h" value="${ h }">
			<input type="hidden" name="itemId" value="${ itemId }">
			<input type="hidden" name="amount" value="${ amount }">
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