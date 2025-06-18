<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ショッピングカート</title>
</head>
<body>
	<table>
		<c:forEach var="dto" items="${dtoList}">
    	<p>商品ID: ${dto.itemId}</p>
    	
		</c:forEach>
	</table>
	
</body>
</html>