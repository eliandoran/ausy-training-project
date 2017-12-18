package com.labplan.webapp.handlers;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.labplan.entities.Patient;
import com.labplan.exceptions.ValidationError;
import com.labplan.helpers.IntUtils;
import com.labplan.persistence.generic.PatientDao;
import com.labplan.persistence.sql.SqlPatientDao;
import com.labplan.services.PatientService;
import com.labplan.webapp.HandlerParameters;
import com.labplan.webapp.LabPlanServlet;
import com.labplan.webapp.Message;
import com.labplan.webapp.PageInformation;
import com.labplan.webapp.ResourceHandler;

public class PatientsResourceHandler implements ResourceHandler {
	private static final Logger LOGGER = Logger.getLogger(LabPlanServlet.class.getName());
	
	PatientDao patientDao;
	PatientService patientService;
	
	public PatientsResourceHandler() {
		patientDao = new SqlPatientDao();
		patientService = new PatientService(patientDao);
	}
	
	@Override
	public boolean doGet(HandlerParameters params) throws ServletException, IOException {
		if (params.getPath().length == 1) {
			listPatients(params);
			return true;
		}
		
		if (params.getPath().length >= 2) {
			String action = params.getPath()[1];
			
			if (action.equals("add"))
				return addPatient(params);
			
			if (action.equals("edit"))
				return editPatient(params);
			
			LOGGER.warning("Unknown action: " + action);
		}
		
		return false;
	}
	
	@Override
	public boolean doPost(HandlerParameters params) throws ServletException, IOException {
		if (params.getPath().length >= 2) {
			String action = params.getPath()[1];
			
			if (action.equals("edit"))
				return commitPatient(params);
			
			if (action.equals("add"))
				return commitAddPatient(params);
			
			LOGGER.warning("Unknown action: " + action);
		}
		
		return false;
	}

	// GET /patients/
	void listPatients(HandlerParameters params) throws ServletException, IOException {
		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/patients/list.jsp");
		HttpServletRequest request = params.getRequest();

		Integer entriesPerPage = 10;
		Integer pageCount = patientService.getPageCount(entriesPerPage);
		Integer page = IntUtils.tryParse(params.getRequest().getParameter("page"));

		page = (page != null ? page : 1);
		page = Math.max(page, 1);
		page = Math.min(page, pageCount);

		request.setAttribute("page", new PageInformation(page, pageCount, entriesPerPage));
		request.setAttribute("patients", patientService.getPage(page, entriesPerPage));

		dispatcher.forward(params.getRequest(), params.getResponse());
	}
	
	// GET /patients/add
	boolean addPatient(HandlerParameters params) throws ServletException, IOException {
		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/patients/add.jsp");
		dispatcher.forward(params.getRequest(), params.getResponse());
		
		return true;
	}
	
	// GET /patients/edit?id={id}
	boolean editPatient(HandlerParameters params) throws ServletException, IOException {
		Integer patientId = IntUtils.tryParse(params.getRequest().getParameter("id"));
		
		if (patientId == null)
			return false;
		
		Patient patient = patientDao.read(patientId);
		
		if (patient == null)
			return false;
		
		HttpServletRequest request = params.getRequest();
		request.setAttribute("first_name", patient.getFirstName());
		request.setAttribute("last_name", patient.getLastName());
		request.setAttribute("age", patient.getAge());
		request.setAttribute("weight", patient.getWeight());
		request.setAttribute("height", patient.getHeight());
		
		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/patients/edit.jsp");
		request.setAttribute("patient", patient);
		dispatcher.forward(params.getRequest(), params.getResponse());
		
		return true;
	}
	
	// POST /patients/edit?id={id}
	boolean commitPatient(HandlerParameters params) throws ServletException, IOException {
		HttpServletRequest request = params.getRequest();
		
		// Parse the `id` GET parameter
		Integer patientId = IntUtils.tryParse(params.getRequest().getParameter("id"));
		
		if (patientId == null)
			return false;
		
		// Read the pre-existing patient.
		Patient patient = patientDao.read(patientId);
		
		if (patient == null)
			return false;
		
		for (String field : new String[] { "first_name", "last_name", "age", "weight", "height" }) {
			request.setAttribute(field, request.getParameter(field));
		}
		
		Message message = new Message();

		// Parse the patient data in POST.
		try {
			Patient parsedPatient = patientService.parse(
					request.getParameter("first_name"),
					request.getParameter("last_name"),
					request.getParameter("age"),
					request.getParameter("weight"),
					request.getParameter("height"));
			
			if (parsedPatient != null) {
				parsedPatient.setId(patientId);
				patientDao.update(parsedPatient);
				
				message.setContent("Patient updated successfully.");
				message.setType(Message.MessageType.MSG_SUCCESS);
				
				HttpSession session = params.getRequest().getSession(true);
				session.setAttribute("message", message);
				params.getResponse().sendRedirect(params.getContext().getContextPath() + "/patients/");
			}
		} catch (ValidationError e) {
			message.setContent(e.toString());
			message.setType(Message.MessageType.MSG_ERROR);
			
			params.getRequest().setAttribute("message", message);
			RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/patients/edit.jsp");
			dispatcher.forward(params.getRequest(), params.getResponse());
		}

		return true;
	}
	
	// POST /patients/add
	boolean commitAddPatient(HandlerParameters params) throws IOException, ServletException {
		HttpServletRequest request = params.getRequest();
		Message message = new Message();
		
		for (String field : new String[] { "first_name", "last_name", "age", "weight", "height" }) {
			request.setAttribute(field, request.getParameter(field));
		}

		// Parse the patient data in POST.
		try {
			Patient parsedPatient = patientService.parse(
					request.getParameter("first_name"),
					request.getParameter("last_name"),
					request.getParameter("age"),
					request.getParameter("weight"),
					request.getParameter("height"));
			
			if (parsedPatient != null) {
				patientDao.create(parsedPatient);
				
				message.setContent("Patient created successfully.");
				message.setType(Message.MessageType.MSG_SUCCESS);
				
				HttpSession session = params.getRequest().getSession(true);
				session.setAttribute("message", message);
				params.getResponse().sendRedirect(params.getContext().getContextPath() + "/patients/");
			}
		} catch (ValidationError e) {
			message.setContent(e.toString());
			message.setType(Message.MessageType.MSG_ERROR);
			
			params.getRequest().setAttribute("message", message);
			RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/patients/edit.jsp");
			dispatcher.forward(params.getRequest(), params.getResponse());
		}

		return true;
	}
}
