<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<title>ショッピングカート内の商品を削除</title>
</head>
<body>

	<h3>以下の商品をショッピングカートから削除しました。</h3>
		<br>
		<c:out value="${itemDelete.itemsDto.name}" /><br>
		<c:out value="${itemDelete.itemsDto.manufacturer}" /><br>
		<fmt:formatNumber value="${itemDelete.itemsDto.price * itemDelete.amount}" />円<br>
		数量<c:out value="${itemDelete.amount}" />個<br>
		
		<br>
		<a href="${pageContext.request.contextPath}/main">商品検索</a>へ<br>

</body>
</html>