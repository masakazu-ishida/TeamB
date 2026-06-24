<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>不正アクセスエラー</title>
</head>
<body>
	<h3>ログインに失敗しました。</h3><br>
	
			
	<form action="${pageContext.request.contextPath}/LoginErroServlet" method="GET">
		
				<input type="hidden" name="itemId" value="${itemId}">
				<input type="hidden" name="order"value="${order}">
				<input type="hidden" name="requestFrom" value="${requestFrom}">
				<input type="submit" value="ログイン画面に戻る">
			
	</form>
		

</body>
</html>