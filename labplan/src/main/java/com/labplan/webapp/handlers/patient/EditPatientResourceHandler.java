package com.labplan.webapp.handlers.patient;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.labplan.entities.Patient;
import com.labplan.exceptions.ValidationError;
import com.labplan.helpers.NumericUtils;
import com.labplan.persistence.generic.PatientDao;
import com.labplan.persistence.sql.SqlPatientDao;
import com.labplan.services.PatientService;
import com.labplan.webapp.HandlerParameters;
import com.labplan.webapp.Message;
import com.labplan.webapp.ResourceHandler;

public class EditPatientResourceHandler implements ResourceHandler {
	private PatientDao patientDao;
	private PatientService patientService;
	
	public EditPatientResourceHandler() {
		patientDao = new SqlPatientDao();
		patientService = new PatientService(patientDao);
	}
	
	@Override
	public String getPath() {
		return "patients/edit";
	}

	@Override
	public boolean doGet(HandlerParameters params) throws ServletException, IOException {
		Integer patientId = NumericUtils.tryParseInteger(params.getRequest().getParameter("id"));
		
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

	@Override
	public boolean doPost(HandlerParameters params) throws ServletException, IOException {
		HttpServletRequest request = params.getRequest();
		
		// Parse the `id` GET parameter
		Integer patientId = NumericUtils.tryParseInteger(params.getRequest().getParameter("id"));
		
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
}
