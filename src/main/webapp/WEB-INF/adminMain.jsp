<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理者メインページ</title>
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
</head>
<body>
	<h3>管理者メインページ</h3>
	<h4>購入履歴の検索</h4>
		<form action='/axis_b/PurchaseSearchController' method='GET'>
			<table>
				<tr>
					<th>会員ID</th>
					<td><input type='text' class='id' name='userId' /></td>
				</tr>
				<tr>
					<td colspan='2'><input type='submit' value='検索' /></td>
				</tr>
			</table>
		</form>
</body>
</html>