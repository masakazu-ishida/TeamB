<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>ログインしてください。</h3><br>
	
	<c:if test="${not empty errorMsg}">
		<!-- ※エラーメッセージが存在する場合 --><br>
		<c:out value="${errorMsg}"></c:out><br>
	</c:if>
		
		
	<form action="${pageContext.request.contextPath}/LoginServlet" method="GET">
		<table border="1">
			<tr>
				<th>会員ID</th>
				<td><input type='text' class='id' name='id' /></td>
			</tr>
			<tr>
				<th>パスワード</th>
				<td><input type='password' class='password' name='password' /></td>
			</tr>
			<tr>
				<td colspan='2' style="text-align: center;">
					<input type='submit' value='ログイン' />
				</td>
			</tr>
		</table>
				<input type="hidden" name="itemId" value="${itemId}">
				<input type="hidden" name="order"value="${order}">
				<input type="hidden" name="requestFrom"value="${requestFrom}">
			
		</form>
		<a href="${pageContext.request.contextPath}/UpdateUserServlet">会員登録</a><br>

</body>
</html>