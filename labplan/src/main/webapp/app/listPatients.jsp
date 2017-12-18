<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Patients (${page.current}/${page.total}) &rsaquo; LabPlan</title>
		<link rel="stylesheet" type="text/css" href="<c:url value="/assets/style.css" />" />
	</head>
	
	<body>
		<aside>
			<header>
				<img class="logo" src="<c:url value="/assets/logo.svg" />" alt="Platform logo" />
				
				<h1>LabTest</h1>
			</header>
			
			<section class="patients">
				<header>
					<img src="<c:url value="/assets/icon-patients.png" />" />
					Patients
				</header>
				
				<ul>
					<li>
						<a href="patients.jsp">View all patients</a>
					</li>
				</ul>
			</section>
		</aside>
		
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
						<c:set var="pagination" value="${page.paginationUrlBuilder}" />
						
						<tr class="pagination">
							<td colspan="7">
								<table>
									<tr>
										<td><a href="${pagination.firstPageUrl}"><img src="<c:url value="/assets/pagination-first.png" />" alt="First" title="Go to the first page" /></a></td>
										<td><a href="${pagination.previousPageUrl}"><img src="<c:url value="/assets/pagination-previous.png" />" alt="Previous" title="Go to the previous page" /></a></td>
										
										<td class="page-indicator">Page ${page.current} out of ${page.total}</td>
										
										<td><a href="${pagination.nextPageUrl}"><img src="<c:url value="/assets/pagination-next.png" />" alt="Next" title="Go to the next page" /></a></td>
										<td><a href="${pagination.lastPageUrl}"><img src="<c:url value="/assets/pagination-last.png" />" alt="Last" title="Go to the last page" /></a></td>
									</tr>
								</table>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</main>
	</body>
</html>