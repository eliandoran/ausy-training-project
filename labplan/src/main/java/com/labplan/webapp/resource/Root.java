package com.labplan.webapp.resource;

import javax.inject.Singleton;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;

import org.glassfish.jersey.server.mvc.Template;

import com.labplan.exceptions.PageOutOfRangeError;

@Path("/")
@Singleton
@Template
@Produces(MediaType.TEXT_HTML)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Root {
	/*
	 * Patients handling
	 */
	@Path("patients/page-{page}/")
	public PatientListing getPatientsByPage(@PathParam("page") Integer page) {
		System.out.println("Listing Patients page " + page.toString());
		
		try {
			return new PatientListing(page);
		} catch (PageOutOfRangeError e) {
			throw new NotFoundException(Response
					.status(Response.Status.NOT_FOUND)
					.entity("Page not found")
					.build());
		}
	}
	
	@Path("patients/")
	public PatientListing getPatientsFirstPage() {
		return getPatientsByPage(1);
	}
}
