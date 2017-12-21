<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="patient" required="true" type="com.labplan.entities.Patient" %>

<table>
	<thead>
		<tr>
			<th colspan="5">Patient information</th>
		</tr>
	
		<tr>
			<th class="centered">First Name</th>
			<th class="centered">Last Name</th>
			<th class="centered">Age</th>
			<th class="centered">Weight</th>
			<th class="centered">Height</th>
		</tr>
	</thead>
	
	<tbody>
		<tr>
			<td class="centered">${patient.firstName}</td>
			<td class="centered">${patient.lastName}</td>
			<td class="centered">${patient.age} yrs</td>
			<td class="centered">${patient.weight} kg</td>
			<td class="centered">${patient.height} cm</td>
		</tr>
	</tbody>
</table>