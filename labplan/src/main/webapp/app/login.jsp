<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ex" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title><ex:pageTitle /></title>
		<%@ include file="header.jspf" %>
		<link rel="stylesheet" type="text/css" href="<c:url value="/static/login.css" />" />
	</head>
	
	<body>
		<div class="login-wrapper">					
			<div class="content-wrapper">	
				<header>
					<img class="logo" src="<c:url value="/static/assets/logo.svg" />" alt="Platform logo" />
					
					<h1>Login to LabTest</h1>
				</header>
				
				<c:if test="${not empty login_failed}">
					<div class="message error">
						User name or password invalid.					
					</div>
				</c:if>
									
				<form class="login-form" method="post">
					<div class="row">
						<label for="username">User name:</label>
						<input type="text" name="username" class="fill" />
					</div>
					
					<div class="row">
						<label for="password">Password:</label>
						<input type="password" name="password" class="fill" />
					</div>
					
					<div class="row actions">
						<input type="submit" value="Login" />
					</div>
				</form>
			</div>
		</div>
	</body>
</html>