<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="ex" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Lab Tests (${page.current}/${page.total}) &rsaquo; LabPlan</title>
		<%@ include file="../header.jspf" %>
	</head>
	
	<body>
		<ex:sidebar />
		
		<main>
			<div class="content-wrapper">
				<h1>Lab Tests</h1>
				
				<ex:message message="${message}" />
			
				<table class="tests">
					<thead>
						<ex:pagination pageInfo="${page}" colspan="7" />
					
						<tr>
							<th>#</th>
							<th>Name</th>
							<th>Description</th>
							<th class="centered">Min. Value</th>
							<th class="centered">Max. Value</th>
							<th>Actions</th>
						</tr>
					</thead>
				
					<tbody>
						<c:set var="testIndex" value="${page.startIndex}" />
						<c:forEach items="${tests}" var="test">
						<tr>
							<td class="right-aligned">${testIndex}</td>
							<td>${test.name}</td>
							<td>${test.description}</td>
							<td class="centered">${test.valueMin}</td>
							<td class="centered">${test.valueMax}</td>
							<td class="actions">
								<a href="<c:url value="/tests/edit?id=${test.id}" />" title="Edit this lab test">
									<img src="<c:url value="/assets/action-edit.png" />" alt="Edit" />
								</a>
							</td>
							
							<c:set var="testIndex" value="${testIndex + 1}" />
						</tr>
						</c:forEach>
					</tbody>
					
					<tfoot>												
						<ex:pagination pageInfo="${page}" colspan="7" />
					</tfoot>
				</table>
			</div>
		</main>
	</body>
</html>