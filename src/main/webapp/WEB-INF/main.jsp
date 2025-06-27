<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品検索</title>




<style type="text/css">
    body{
      text-align: center;
      font-family:sans-serif;
    }

p {
  font-size: 30px; /* ルート要素と同じサイズ */
  
     font-family:serif;

}

select {
  font-size: 18px; /* ルート要素と同じサイズ */
  font-family:serif;
}

input {
  font-size: 18px; /* ルート要素と同じサイズ */
  font-weight: bold;
}

.cp_keyframes {
	margin: 60px auto;
	color: #ffffff;
	background: #29b6f6;
	border-radius: 6px;
	width: 100px;
	height: 100px;
	line-height: 100px;
	text-align: center;
	-webkit-animation: keyframes 3s 0.5s infinite alternate;
	        animation: keyframes 3s 0.5s infinite alternate;
}
@-webkit-keyframes keyframes {
	0% {
		-webkit-transform: translate3d(-135px, 0, 0) rotate3d(0, 0, 1, 0deg) scale3d(1, 1, 1);
		        transform: translate3d(-135px, 0, 0) rotate3d(0, 0, 1, 0deg) scale3d(1, 1, 1);
	}
	50% {
		-webkit-transform: translate3d(0, 0, 0) rotate3d(0, 0, 1, 180deg) scale3d(0.5, 0.5, 0.5);
		        transform: translate3d(0, 0, 0) rotate3d(0, 0, 1, 180deg) scale3d(0.5, 0.5, 0.5);
	}
	100% {
		-webkit-transform: translate3d(135px, 0, 0) rotate3d(0, 0, 1, 360deg) scale3d(1, 1, 1);
		        transform: translate3d(135px, 0, 0) rotate3d(0, 0, 1, 360deg) scale3d(1, 1, 1);
	}
}
@keyframes keyframes {
	0% {
		-webkit-transform: translate3d(-135px, 0, 0) rotate3d(0, 0, 1, 0deg) scale3d(1, 1, 1);
		        transform: translate3d(-135px, 0, 0) rotate3d(0, 0, 1, 0deg) scale3d(1, 1, 1);
	}
	50% {
		-webkit-transform: translate3d(0, 0, 0) rotate3d(0, 0, 1, 180deg) scale3d(0.5, 0.5, 0.5);
		        transform: translate3d(0, 0, 0) rotate3d(0, 0, 1, 180deg) scale3d(0.5, 0.5, 0.5);
	}
	100% {
		-webkit-transform: translate3d(135px, 0, 0) rotate3d(0, 0, 1, 360deg) scale3d(1, 1, 1);
		        transform: translate3d(135px, 0, 0) rotate3d(0, 0, 1, 360deg) scale3d(1, 1, 1);
	}
}

table {
  border-collapse: collapse; /* 二重線を一本にする */
  border: 1px  black solid ; /* テーブル全体に線を引く */
  background-color: white;
}

th, td {
  border: 1px  black  solid; /* セルにも線を引く */
  padding: 8px; /* セルのpadding */
}

th{
	background-color: orange;
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

.button04 {
  background-color: white;
  border: double 6px #71BFDD;
  color: #71BFDD;
  border-radius: 100px;
  padding: 10px 30px;
  text-decoration: none;
  font-size: 1em;
  font-family:sans-serif;
}
.button04:hover {
  color: #3CB6FC;
  background-color: #E0F1F9;
}

.cp_link {
  position: relative;
  padding: 5px;
  display: inline-block;
  transition: .3s;
  color: #3B91F9;
  text-decoration: none;/*元々のアンダーラインを非表示にしておく*/
}
/*hover時の表示*/
.cp_link::before,
.cp_link::after {
  position: absolute;
  content: '';
  width: 0;
  height: 1px;
  background-color: #3B91F9;
  transition: .3s;
}
/*hover時にどこを基準にスタートするかを設定*/
.cp_link::before {/*左上を基準に設定*/
  top: 0;
  left: 0;
}
.cp_link::after {/*右下を基準に設定*/
  bottom: 0;
  right: 0;
}
.cp_link:hover::before,
.cp_link:hover::after {
  width: 100%;
}

body {
	background-color: #dedede;
	background-image:
	 linear-gradient(#ecebeb 50%, transparent 50%, transparent);
	background-size: 10px 10px;
}


</style>




</head>
<body>


	<h1>商品の検索を行います。</h1>
	<form action="/axis_b/SearchController" method='POST'>
		<p class = "">キーワード</p>
		<input type='text' name='keyword' />
		
		<br>
			<p>カテゴリ</p>
			<select name='categoriesId'>
				<option selected value='0' class="cp_ipselect05">すべて</option>
				<option value='1'>帽子</option>
				<option value='2'>鞄</option>
			</select><br />
			<br>
		<input type='submit' value='検索' class = "button04" /><br />
	</form>
	
	<p><a href=  "/axis_b/CartController?h=cart " class = "cp_link">ショッピングカートを見る </a></p>
	<%
		String userId = (String)session.getAttribute("userId");
		if(userId == null){
	%>
		<div id="conn"><a href='/axis_b/LoginController?h=main' class = "cp_link">ログイン</a></div>
	<%	
		}
	%>
<div class="cp_keyframes">axis_b</div>
</body>
</html>