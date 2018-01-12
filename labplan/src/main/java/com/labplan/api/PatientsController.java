package com.labplan.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.labplan.persistence.generic.PatientDao;
import com.labplan.persistence.sql.SqlPatientDao;

@Path("/patients")
public class PatientsController {
	private static PatientDao patientDao = new SqlPatientDao();
	
	@GET
	public String getPatients() {
		return "patients";
	}
}
