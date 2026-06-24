<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ショッピングカート内の商品を削除</title>
</head>
<body>
    <h3>以下の商品をショッピングカートから削除してよろしいですか？</h3>
        <br>
        <c:out value="${deleteTarget.itemsDto.name }"/><br>
        <c:out value="${deleteTarget.itemsDto.manufacturer }"/><br>
        <fmt:formatNumber value="${deleteTarget.itemsDto.price * deleteTarget.amount}" />円<br>
        数量<c:out value="${deleteTarget.amount }"/>個<br>
        <form action="${pageContext.request.contextPath}/cartDeleteCommit" method='POST'>
            <input type='hidden' name='itemId' value="${deleteTarget.item_id}" />
            <input type='submit' value='削除する' /><br>
        </form>
        <br>
        <a href="${pageContext.request.contextPath}/main">商品検索</a>へ<br>

</body>
</html>