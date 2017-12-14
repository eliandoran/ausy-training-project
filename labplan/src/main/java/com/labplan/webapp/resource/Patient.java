package com.labplan.webapp.resource;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.glassfish.jersey.server.mvc.*;

import com.labplan.persistence.generic.PatientDao;
import com.labplan.persistence.sql.SqlPatientDao;
import com.labplan.services.PatientService;

@Template
@Produces(MediaType.TEXT_HTML)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Path("/patients/")
@Singleton
public class Patient {
	@GET
    @Produces({MediaType.APPLICATION_XML, MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
    public List<com.labplan.entities.Patient> listPatients() {
		PatientDao dao = new SqlPatientDao();
		PatientService service = new PatientService(dao);
        List<com.labplan.entities.Patient> patients = service.getPage(1, 5);
        return patients;
    }
}
