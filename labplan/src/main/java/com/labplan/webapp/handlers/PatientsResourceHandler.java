package com.labplan.webapp.handlers;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.labplan.helpers.IntUtils;
import com.labplan.persistence.generic.PatientDao;
import com.labplan.persistence.sql.SqlPatientDao;
import com.labplan.services.PatientService;
import com.labplan.webapp.HandlerParameters;
import com.labplan.webapp.LabPlanServlet;
import com.labplan.webapp.PageInformation;
import com.labplan.webapp.ResourceHandler;

public class PatientsResourceHandler implements ResourceHandler {
	private static final Logger LOGGER = Logger.getLogger(LabPlanServlet.class.getName());
	
	@Override
	public boolean doGet(HandlerParameters params) throws ServletException, IOException {
		if (params.getPath().length == 1) {
			listPatients(params);
			return true;
		}
		
		if (params.getPath().length >= 2) {
			String action = params.getPath()[1];
			
			if (action.equals("edit")) {
				editPatient(params, 1);
				return true;
			}
			
			LOGGER.warning("Unknown action: " + action);
		}
		
		return false;
	}

	// GET /patients/
	void listPatients(HandlerParameters params) throws ServletException, IOException {
		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/listPatients.jsp");
		HttpServletRequest request = params.getRequest();

		PatientDao patientDao = new SqlPatientDao();
		PatientService patientService = new PatientService(patientDao);

		Integer entriesPerPage = 3;
		Integer pageCount = patientService.getPageCount(entriesPerPage);
		Integer page = IntUtils.tryParse(params.getRequest().getParameter("page"));

		page = (page != null ? page : 1);
		page = Math.max(page, 1);
		page = Math.min(page, pageCount);

		request.setAttribute("page", new PageInformation(page, pageCount, entriesPerPage));
		request.setAttribute("patients", patientService.getPage(page, entriesPerPage));

		dispatcher.forward(params.getRequest(), params.getResponse());
	}
	
	// GET /patients/edit/{id}
	void editPatient(HandlerParameters params, Integer id) throws ServletException, IOException {
		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/addEditPatient.jsp");
		dispatcher.forward(params.getRequest(), params.getResponse());
	}
}
