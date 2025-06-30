<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
<h3>以下の商品をショッピングカートから削除してよろしいですか？</h3>
		<br />
	    <c:out value="${dto.items.name}"/><br />
		<c:out value="${dto.items.manufacturer}"/><br />
		<c:out value="${dto.items.price*dto.amount}"/>円<br />
		<p>数量<c:out value="${dto.amount}"/>個<br /> 
		<form action='/axis_b/RemoveFromCartCommitController' method='POST'>
			<input type='hidden' name='itemId' value='123' />
			<input type='submit' value='削除する' /><br />
		</form><br>
		
		<a href='/axis_b/CartController'>ショッピングカート内一覧</a>へ<br /></body>
		<a href='/axis_b/mainController'>商品検索</a>へ<br />

</body>
</html>