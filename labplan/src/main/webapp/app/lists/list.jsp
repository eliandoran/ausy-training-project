<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="ex" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title><ex:pageTitle title="Lab Lists" pageInfo="${page}" /></title>
		<%@ include file="../header.jspf" %>
	</head>
	
	<body>
		<ex:sidebar />
		
		<main>
			<c:set var="urlSuffix" value="?" />
			<c:if test="${not empty patient}">
				<c:set var="urlSuffix" value="?patient=${patient.id}" />
			</c:if>
					
			<div class="content-wrapper">
				<header>
					<h1>Lab Lists</h1>
					
					<a class="button" href="<c:url value="/lists/add${urlSuffix}" />">Add a new list</a>
				</header>
				
				<ex:message message="${message}" />
				
				<c:choose>
				<c:when test="${not empty patient}">
					<c:set var="baseUrl" value="${urlSuffix}&page=" />
					<section>
						<ex:patientBox patient="${patient}" />
					</section>
				</c:when>
				
				<c:otherwise>
					<c:set var="baseUrl" value="?page=" />
				</c:otherwise>
				</c:choose>
			
				<c:choose>
				<c:when test="${not empty lists}">
					<table class="tests">
						<thead>
							<ex:pagination pageInfo="${page}" colspan="4" baseUrl="${baseUrl}" />
							
							<tr>
								<th>#</th>
								
								<c:if test="${empty patient}">
								<th>Patient</th>
								</c:if>
								
								<th>Creation Date</th>
								<th>Actions</th>
							</tr>
						</thead>
					
						<tbody>
							<c:set var="listIndex" value="${page.startIndex}" />
							<c:forEach items="${lists}" var="list">
							<tr>
								<td class="right-aligned">${listIndex}</td>
								
								<c:if test="${empty patient}">
								<td><a href="?patient=${list.patient.entity.id}">${list.patient.entity.fullName}</a></td>
								</c:if>
								
								<td>${list.creationDate}</td>
								<td class="actions">
									<a href="<c:url value="/lists/edit?id=${list.id}" />" title="Edit this lab list">
										<img src="<c:url value="/static/assets/action-edit.png" />" alt="Edit" />
									</a>
								</td>
								
								<c:set var="listIndex" value="${listIndex + 1}" />
							</tr>
							</c:forEach>
						</tbody>
						
						<tfoot>												
							<ex:pagination pageInfo="${page}" colspan="4" baseUrl="${baseUrl}" />
						</tfoot>
					</table>
				</c:when>
				<c:otherwise>
					<div class="placeholder">
						No lab lists.
					</div>
				</c:otherwise>
				</c:choose>
			</div>
		</main>
	</body>
</html>