<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="ex" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Patients (${page.current}/${page.total}) &rsaquo; LabPlan</title>
		<%@ include file="../header.jspf" %>
	</head>
	
	<body>
		<ex:sidebar />
		
		<main>
			<div class="content-wrapper">
				<header>
					<h1>Patients</h1>
					<a class="button" href="<c:url value="/patients/add" />">Add a new patient</a>
				</header>
				
				<ex:message message="${message}" />
			
				<table class="patients">
					<thead>
						<ex:pagination pageInfo="${page}" colspan="7" />
					
						<tr>
							<th>#</th>
							<th>First Name</th>
							<th>Last Name</th>
							<th class="centered">Age</th>
							<th class="centered">Weight</th>
							<th class="centered">Height</th>
							<th>Actions</th>
						</tr>
					</thead>
				
					<tbody>
						<c:set var="patientIndex" value="${page.startIndex}" />
						<c:forEach items="${patients}" var="patient">
						<tr>
							<td class="right-aligned">${patientIndex}</td>
							<td>${patient.firstName}</td>
							<td>${patient.lastName}</td>
							<td class="centered">${patient.age} yrs</td>
							<td class="centered">${patient.weight} kg</td>
							<td class="centered">${patient.height} cm</td>
							<td class="actions">
								<a href="<c:url value="/patients/edit?id=${patient.id}" />" title="Edit this patient">
									<img src="<c:url value="/assets/action-edit.png" />" alt="Edit" />
								</a>
								
								<a href="<c:url value="/lists/?patient=${patient.id}" />" title="View this patient's lab lists">
									<img src="<c:url value="/assets/action-view-list.png" />" alt="View lists" />
								</a>
							</td>
							
							<c:set var="patientIndex" value="${patientIndex + 1}" />
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