<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ex" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title><ex:pageTitle title="Edit a lab list" /></title>
		<%@ include file="../header.jspf" %>
		<link rel="stylesheet" type="text/css" href="<c:url value="/static/own_dropdown.css" />" />
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
		
		<div id="own-dropdown--shade">
			<div id="own-dropdown--container">
				<div id="own-dropdown--search">
					<input type="search" placeholder="Search" autocomplete="off" />
				</div>
			
				<ul id="own-dropdown--options">
					
				</ul>			
			</div>
		</div>
		
		<script src="<c:url value="/static/own_dropdown.js" />"></script>
		<script src="<c:url value="/static/lab_list.js" />"></script>
	</body>
</html>