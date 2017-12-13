<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.*" %>
<%@ page import="com.labplan.entities.Patient" %>
<%@ page import="com.labplan.services.PatientService" %>
<%@ page import="com.labplan.persistence.DatabaseConnectionFactory" %>
<%@ page import="com.labplan.persistence.generic.PatientDao" %>
<%@ page import="com.labplan.persistence.sql.SqlPatientDao" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>LabPlan</title>
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
			</div>
		</main>
	</body>
</html>