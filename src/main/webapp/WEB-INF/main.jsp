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

.button {
  display       : inline-block;
  border-radius : 6%;          /* 角丸       */
  font-size     : 9pt;        /* 文字サイズ */
  text-align    : center;      /* 文字位置   */
  cursor        : pointer;     /* カーソル   */
  padding       : 12px 12px;   /* 余白       */
  background    : rgba(26, 26, 255, 0.75);     /* 背景色     */
  color         : #ffffff;     /* 文字色     */
  line-height   : 1em;         /* 1行の高さ  */
  opacity       : 1;           /* 透明度     */
  transition    : .3s;         /* なめらか変化 */
  box-shadow    : 6px 6px 3px #666666;  /* 影の設定 */
}
.button:hover {
  box-shadow    : none;        /* カーソル時の影消去 */
  opacity       : 0.8;         /* カーソル時透明度 */
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