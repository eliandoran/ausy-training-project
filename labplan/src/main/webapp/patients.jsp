<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.*" %>
<%@ page import="com.labplan.entities.Patient" %>
<%@ page import="com.labplan.services.PatientService" %>
<%@ page import="com.labplan.persistence.generic.PatientDao" %>
<%@ page import="com.labplan.persistence.sql.SqlPatientDao" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Insert title here</title>
	</head>
	
	<body>
		<%
		PatientDao patientDao = new SqlPatientDao();
		PatientService patientService = new PatientService(patientDao);
		
		int currentPage = 1;
		int entriesPerPage = 5;
		
		List<Patient> patients = patientService.getPage(currentPage, entriesPerPage);
		%>
		
		<table class="patients">
			<thead>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Age</th>
					<th>Weight</th>
					<th>Height</th>
				</tr>
			</thead>
		
			<tbody>
				<% for (Patient patient : patients) { %>
					<tr>
						<td><%= patient.getFirstName() %></td>
						<td><%= patient.getLastName() %></td>
						<td><%= patient.getAge() %></td>
						<td><%= patient.getWeight() %></td>
						<td><%= patient.getHeight() %></td>
					</tr>
				<% } %>
			</tbody>
		</table>
	</body>
</html>