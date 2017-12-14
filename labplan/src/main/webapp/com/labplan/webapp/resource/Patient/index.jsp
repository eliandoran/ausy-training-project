<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
	<head>
	
	</head>
	
	<body>
		<c:forEach var="item" items="${it}">
			<li>
				${item.firstName}
			</li>
		</c:forEach>
	</body>
</html>