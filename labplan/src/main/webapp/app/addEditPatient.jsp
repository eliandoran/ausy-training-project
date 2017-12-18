<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="ex" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<title>LabPlan</title>
		<%@ include file="header.jspf" %>
	</head>
	
	<body>
		<ex:sidebar />
		
		<main>
			<div class="content-wrapper">
				<h1>Edit a patient</h1>
				
				<form class="patient-form" method="post">
					<input type="hidden" name="commit" value="" />
				
					<div class="two-columns">
						<div class="row">
							<label for="first_name">First Name</label>
							<input type="text" name="first_name" value="" class="fill" />
						</div>
						
						<div class="row">
							<label for="last_name">Last Name</label>
							<input type="text" name="last_name" value="" class="fill" />
						</div>
					</div>
					
					<div class="three-columns">
						<div class="row">
							<label for="age">Age</label>
							
							<div class="has-unit" data-unit="years">
								<input type="text" name="age" value="" class="fill" />
							</div>
						</div>
						
						<div class="row">
							<label for="weight">Weight</label>
							
							<div class="has-unit" data-unit="kg">
								<input type="text" name="weight" value="" class="fill" />
							</div>
						</div>
						
						<div class="row">
							<label for="height">Height</label>
							
							<div class="has-unit" data-unit="cm">
								<input type="text" name="height" value="" class="fill" />
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