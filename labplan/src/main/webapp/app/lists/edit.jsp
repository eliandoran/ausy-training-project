<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ex" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title><ex:pageTitle title="Edit a lab list" /></title>
		<%@ include file="../header.jspf" %>
	</head>
	
	<body>
		<ex:sidebar />
		
		<main>
			<div class="content-wrapper">
				<header>
					<h1>Edit a lab list</h1>
				</header>
				
				<ex:message message="${message}" />
				
				<%@ include file="addEditForm.jspf" %>
			</div>
		</main>
		
		<script src="<c:url value="/static/own_dropdown.js" />"></script>
		<script src="<c:url value="/static/lab_list.js" />"></script>
	</body>
</html>