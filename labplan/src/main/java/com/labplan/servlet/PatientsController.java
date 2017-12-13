package com.labplan.servlet;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;

/**
 * Servlet implementation class PatientsController
 */
@Path("/patients")
public class PatientsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@GET
	public String listPatients() {
		return "Hi";
	}
}
