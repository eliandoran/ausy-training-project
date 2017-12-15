package com.labplan.webapp.resource;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.glassfish.jersey.server.mvc.*;

import com.labplan.entities.Patient;
import com.labplan.exceptions.PageOutOfRangeError;
import com.labplan.persistence.DatabaseConnectionFactory;
import com.labplan.persistence.generic.PatientDao;
import com.labplan.persistence.sql.SqlPatientDao;
import com.labplan.services.PatientService;

@Template
@Produces(MediaType.TEXT_HTML)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PatientListing {
	private List<Patient> patients;
	
	public PatientListing(int pageNumber) throws PageOutOfRangeError {
		DatabaseConnectionFactory.setProfile("production");
		PatientDao dao = new SqlPatientDao();
		PatientService service = new PatientService(dao);
        patients = service.getPage(pageNumber, 3);
	}
	
	public List<Patient> getPatients() {
		return patients;
	}
	
	@GET
    @Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
    public PatientListing listPatients() {
        System.out.println("Hi");
        return this;
    }
	
	@Override
	public String toString() {
		if (patients == null) {
			return "Patients is null.";
		}
		
		return "Patients is not null.";
	}
}
