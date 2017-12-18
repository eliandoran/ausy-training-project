<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="ex" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Patients (${page.current}/${page.total}) &rsaquo; LabPlan</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/assets/style.css" />" />
		
		<link rel="apple-touch-icon" sizes="180x180" href="<c:url value="/assets/favicon/apple-touch-icon.png" />"/>
		<link rel="icon" type="image/png" sizes="32x32" href="<c:url value="/assets/favicon/favicon-32x32.png" />"/>
		<link rel="icon" type="image/png" sizes="16x16" href="<c:url value="/assets/favicon/favicon-16x16.png" />"/>
		<link rel="manifest" href="<c:url value="/assets/favicon/manifest.json" />" />
		<link rel="mask-icon" href="<c:url value="/assets/favicon/safari-pinned-tab.svg" />" color="#000000" />
		<link rel="shortcut icon" href="<c:url value="/assets/favicon/favicon.ico" />" />
		<meta name="apple-mobile-web-app-title" content="LabPlan" />
		<meta name="application-name" content="LabPlan" />
		<meta name="msapplication-config" content="<c:url value="/assets/favicon/browserconfig.xml" />" />
		<meta name="theme-color" content="#ffffff" />	
	</head>
	
	<body>
		<ex:sidebar />
		
		<main>
			<div class="content-wrapper">
				<h1>Patients</h1>
			
				<table class="patients">
					<thead>
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
								<a href="patient.jsp?id=${patient.id}" title="Edit this patient">
									<img src="<c:url value="/assets/action-edit.png" />" alt="Edit" />
								</a>
							</td>
							
							<c:set var="patientIndex" value="${patientIndex + 1}" />
						</tr>
						</c:forEach>
					</tbody>
					
					<tfoot>												
						<tr class="pagination">
							<td colspan="7">
								<ex:pagination pageInfo="${page}" />
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</main>
	</body>
</html>