<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="ex" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Lab Lists (${page.current}/${page.total}) &rsaquo; LabPlan</title>
		<%@ include file="../header.jspf" %>
	</head>
	
	<body>
		<ex:sidebar />
		
		<main>
			<div class="content-wrapper">
				<h1>Lab Lists</h1>
				
				<ex:message message="${message}" />
			
				<table class="tests">
					<thead>
						<tr>
							<th>#</th>
							<th>Patient</th>
							<th>Creation date</th>
							<th>Actions</th>
						</tr>
					</thead>
				
					<tbody>
						<c:set var="listIndex" value="${page.startIndex}" />
						<c:forEach items="${lists}" var="list">
						<tr>
							<td class="right-aligned">${listIndex}</td>
							<td>${list.patient.entity.fullName}</td>
							<td>${list.creationDate}</td>
							<td class="actions">
								<a href="<c:url value="/lists/edit?id=${list.id}" />" title="Edit this lab list">
									<img src="<c:url value="/assets/action-edit.png" />" alt="Edit" />
								</a>
							</td>
							
							<c:set var="testIndex" value="${listIndex + 1}" />
						</tr>
						</c:forEach>
					</tbody>
					
					<tfoot>												
						<tr class="pagination">
							<td colspan="4">
								<ex:pagination pageInfo="${page}" />
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</main>
	</body>
</html>