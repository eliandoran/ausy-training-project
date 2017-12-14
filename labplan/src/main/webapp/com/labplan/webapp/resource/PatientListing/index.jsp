<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	</head>
	
	<body>
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
				<c:forEach var="patient" items="${it.patients}">
					<tr>
						<td class="right-aligned">0</td>
						<td>${patient.firstName}</td>
						<td>${patient.lastName}</td>
						<td class="centered">${patient.age} yrs</td>
						<td class="centered">${patient.weight} kg</td>
						<td class="centered">${patient.height} cm</td>
						<td class="actions">
							<a href="patient.jsp?id=${patient.id} %>" title="Edit this patient"><img src="assets/action-edit.png" alt="Edit" /></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>