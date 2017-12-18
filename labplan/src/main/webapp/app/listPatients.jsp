<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>Patients (${page.current}/${page.total}) &rsaquo; LabPlan</title>
		<link rel="stylesheet" type="text/css" href="style.css" />
	</head>
	
	<body>
		<aside>
			<header>
				<img class="logo" src="assets/logo.svg" alt="Platform logo" />
				
				<h1>LabTest</h1>
			</header>
			
			<section class="patients">
				<header>
					<img src="assets/icon-patients.png" />
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
						<c:forEach items="${patients}" var="patient">
						<tr>
							<td class="right-aligned">0</td>
							<td>${patient.firstName}</td>
							<td>${patient.lastName}</td>
							<td class="centered">${patient.age} yrs</td>
							<td class="centered">${patient.weight} kg</td>
							<td class="centered">${patient.height} cm</td>
							<td class="actions">
								<a href="patient.jsp?id=${patient.id}" title="Edit this patient">
									<img src="assets/action-edit.png" alt="Edit" />
								</a>
							</td>
						</tr>
						</c:forEach>
					</tbody>
					
					<tfoot>
					</tfoot>
				</table>
			</div>
		</main>
	</body>
</html>