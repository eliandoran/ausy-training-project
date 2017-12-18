package com.labplan.webapp.handlers.patient;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.labplan.entities.Patient;
import com.labplan.exceptions.ValidationError;
import com.labplan.persistence.generic.PatientDao;
import com.labplan.persistence.sql.SqlPatientDao;
import com.labplan.services.PatientService;
import com.labplan.webapp.HandlerParameters;
import com.labplan.webapp.Message;
import com.labplan.webapp.ResourceHandler;

public class AddPatientResourceHandler implements ResourceHandler {
	private PatientDao patientDao;
	private PatientService patientService;
	
	public AddPatientResourceHandler() {
		patientDao = new SqlPatientDao();
		patientService = new PatientService(patientDao);
	}
	
	@Override
	public String getPath() {
		return "patients/add";
	}

	@Override
	public boolean doGet(HandlerParameters params) throws ServletException, IOException {
		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/patients/add.jsp");
		dispatcher.forward(params.getRequest(), params.getResponse());
		
		return true;
	}

	@Override
	public boolean doPost(HandlerParameters params) throws ServletException, IOException {
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
			RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/patients/add.jsp");
			dispatcher.forward(params.getRequest(), params.getResponse());
		}

		return true;
	}
}
