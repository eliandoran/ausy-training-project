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
		<title>Edit a patient &rsaquo; LabPlan</title>
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
				<h1>Edit a patient</h1>
				
				<form class="patient-form">
					<div class="two-columns">
						<div class="row">
							<label for="first_name">First Name</label>
							<input type="text" name="first_name" class="fill" />
						</div>
						
						<div class="row">
							<label for="last_name">Last Name</label>
							<input type="text" name="last_name" class="fill" />
						</div>
					</div>
					
					<div class="three-columns">
						<div class="row">
							<label for="age">Age</label>
							
							<div class="has-unit" data-unit="years">
								<input type="text" name="age" class="fill" />
							</div>
						</div>
						
						<div class="row">
							<label for="weight">Weight</label>
							
							<div class="has-unit" data-unit="kg">
								<input type="text" name="weight" class="fill" />
							</div>
						</div>
						
						<div class="row">
							<label for="height">Height</label>
							
							<div class="has-unit" data-unit="cm">
								<input type="text" name="height" class="fill" />
							</div>
						</div>
					</div>
					
					<div class="row actions">
						<input type="submit" value="Update" />
					</div>
				</form>
			</div>
		</main>
	</body>
</html>