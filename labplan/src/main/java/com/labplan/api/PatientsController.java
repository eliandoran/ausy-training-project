package com.labplan.api;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.labplan.api.exceptions.EntityNotFoundException;
import com.labplan.entities.Patient;
import com.labplan.persistence.DatabaseConnectionFactory;
import com.labplan.persistence.generic.PatientDao;
import com.labplan.persistence.sql.SqlPatientDao;

@Path("/patients")
public class PatientsController {
	private static final PatientDao patientDao = new SqlPatientDao();
	
	public PatientsController() {
		DatabaseConnectionFactory.setProfile("production");
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Patient> getPatients() {
		Set<Patient> patientSet = patientDao.readAll();
		List<Patient> patientList = new LinkedList<>();
		patientList.addAll(patientSet);
		return patientList;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{id}")
	public Patient getPatientById(@PathParam("id") Integer id) {
		Patient patient = patientDao.read(id);
		
		if (patient == null) {
			throw new EntityNotFoundException();
		}
		
		return patient;
	}
}
