<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.*" %>
<%@ page import="com.labplan.entities.Patient" %>
<%@ page import="com.labplan.services.PatientService" %>
<%@ page import="com.labplan.persistence.DatabaseConnectionFactory" %>
<%@ page import="com.labplan.persistence.generic.PatientDao" %>
<%@ page import="com.labplan.persistence.sql.SqlPatientDao" %>
<%
DatabaseConnectionFactory.setProfile("production");
PatientDao patientDao = new SqlPatientDao();
PatientService patientService = new PatientService(patientDao);

int currentPage = 1;
int entriesPerPage = 3;
int pageCount = patientService.getPageCount(entriesPerPage);

String pageQuery = request.getParameter("page");

if (pageQuery != null) {
	try {
		Integer parsedPageNum = Integer.parseInt(pageQuery);	
		
		if (parsedPageNum > 0 && parsedPageNum <= pageCount)
			currentPage = parsedPageNum;
	} catch (NumberFormatException e) {
		// No action needed.
	}
}

List<Patient> patients = patientService.getPage(currentPage, entriesPerPage);

if (pageCount == 0) {
	currentPage = 0;
}
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Patients (<%= currentPage %>/<%= pageCount %>) &rsaquo; LabPlan</title>
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
							<th>First Name</th>
							<th>Last Name</th>
							<th class="centered">Age</th>
							<th class="centered">Weight</th>
							<th class="centered">Height</th>
						</tr>
					</thead>
				
					<tbody>
						<%
						for (Patient patient : patients) { %>
						<tr>
							<td><%= patient.getFirstName() %></td>
							<td><%= patient.getLastName() %></td>
							<td class="centered"><%= patient.getAge() %></td>
							<td class="centered"><%= patient.getWeight() %></td>
							<td class="centered"><%= patient.getHeight() %></td>
						</tr>
						<% } %>
					
						<% if (patients.size() < 1) { %>
							<tr>
								<td colspan="5">No data.</td>
							</tr>
						<% } %>
					</tbody>
					
					<tfoot>
						<%
						String pageUrlFormat = "?page=%d";
						
						String paginationFirstUrl = (currentPage > 1 ? String.format(pageUrlFormat, 1) : "");
						String paginationPreviousUrl = (currentPage > 1 ? String.format(pageUrlFormat, currentPage - 1) : "");
						String paginationNextUrl = (currentPage < pageCount ? String.format(pageUrlFormat, currentPage + 1) : "");
						String paginationLastUrl = (currentPage < pageCount ? String.format(pageUrlFormat, pageCount) : "");
						%>
						<tr class="pagination">
							<td colspan="5">
								<table>
									<tr>
										<td><a href="<%= paginationFirstUrl %>"><img src="assets/pagination-first.png" alt="First" title="Go to the first page" /></a></td>
										<td><a href="<%= paginationPreviousUrl %>"><img src="assets/pagination-previous.png" alt="Previous" title="Go to the previous page" /></a></td>
										
										<td class="page-indicator">Page <%= currentPage %> out of <%= pageCount %></td>
										
										<td><a href="<%= paginationNextUrl %>"><img src="assets/pagination-next.png" alt="Next" title="Go to the next page" /></a></td>
										<td><a href="<%= paginationLastUrl %>"><img src="assets/pagination-last.png" alt="Last" title="Go to the last page" /></a></td>
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