package com.labplan.api;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.labplan.entities.Patient;
import com.labplan.persistence.DatabaseConnectionFactory;
import com.labplan.persistence.generic.PatientDao;
import com.labplan.persistence.sql.SqlPatientDao;
import com.labplan.services.PatientService;

@Path("/patients")
public class PatientsController {
	private static final PatientDao patientDao = new SqlPatientDao();
	private static final PatientService patientService = new PatientService(patientDao);
	
	public PatientsController() {
		DatabaseConnectionFactory.setProfile("production");
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Patient> getPatients() {
		return patientService.getAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Patient getPatientById(@PathParam("id") Integer id) {
		return patientService.get(id);
	}
}
