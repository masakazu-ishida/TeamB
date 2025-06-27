<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索</title>




<style type="text/css">


table {
  border-collapse: collapse; /* 二重線を一本にする */
  border: 1px  black solid ; /* テーブル全体に線を引く */
}

th, td {
  border: 1px  black  solid; /* セルにも線を引く */
  padding: 8px; /* セルのpadding */
}
	
h1 {
  color: #6cb4e4;
  text-align: center;
  padding: 0.25em;
  border-top: solid 2px #6cb4e4;
  border-bottom: solid 2px #6cb4e4;
  background: -webkit-repeating-linear-gradient(-45deg, #f0f8ff, #f0f8ff 3px,#e9f4ff 3px, #e9f4ff 7px);
  background: repeating-linear-gradient(-45deg, #f0f8ff, #f0f8ff 3px,#e9f4ff 3px, #e9f4ff 7px);
}

.button{
  background-color: #fff;
  border: solid 2px #191970;
  color: #191970;
  border-radius: 20px;
  padding: 10px 30px;
  text-decoration: none;
  font-size: 1em;
  box-shadow: 0 5px 0 #191970;
  display: inline-block;
  transition: .3s;
}
.button:hover {
  color: #191970;
  transform: translateY(5px);
  box-shadow: 0 0 0 #191970;
}
	
	
</style>




</head>
<body>


	<h1>商品の検索を行います。</h1>
		<br />
	<form action="/axis_b/SearchController" method='POST'>
		キーワード<br />
		<input type='text' name='keyword' /><br />
			カテゴリ<br />
			<select name='categoriesId'>
				<option selected value='0'>すべて</option>
				<option value='1'>帽子</option>
				<option value='2'>鞄</option>
			</select><br />
		<input type='submit' value='検索' class = "button" /><br />
	</form>
	
	<a href="/axis_b/CartController?h=cart">ショッピングカートを見る</a><br /><br />
	<%
		String userId = (String)session.getAttribute("userId");
		if(userId == null){
	%>
		<div id="conn"><a href='/axis_b/LoginController?h=main'>ログイン</a></div>
	<%	
		}
	%>

</body>
</html>