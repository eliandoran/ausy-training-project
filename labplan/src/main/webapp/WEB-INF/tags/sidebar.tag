<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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