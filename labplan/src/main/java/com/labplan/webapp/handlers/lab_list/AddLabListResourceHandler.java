package com.labplan.webapp.handlers.lab_list;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.labplan.entities.LabList;
import com.labplan.entities.Patient;
import com.labplan.exceptions.ValidationException;
import com.labplan.persistence.generic.PatientDao;
import com.labplan.persistence.sql.SqlLabListDao;
import com.labplan.persistence.sql.SqlPatientDao;
import com.labplan.services.LabListService;
import com.labplan.webapp.HandlerParameters;
import com.labplan.webapp.Message;
import com.labplan.webapp.ResourceHandler;

public class AddLabListResourceHandler implements ResourceHandler {
	private SqlLabListDao listDao;
	private LabListService listService;

	public AddLabListResourceHandler() {
		listDao = new SqlLabListDao();
		listService = new LabListService(listDao);
	}

	@Override
	public String getPath() {
		return "lists/add";
	}

	@Override
	public boolean doGet(HandlerParameters params) throws ServletException, IOException {
		HttpServletRequest request = params.getRequest();
		request.setAttribute("creation_date", new Date());
		request.setAttribute("is_new", true);
		request.setAttribute("patients", getPatients());

		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/lists/add.jsp");
		dispatcher.forward(params.getRequest(), params.getResponse());
		return true;
	}

	@Override
	public boolean doPost(HandlerParameters params) throws ServletException, IOException {
		HttpServletRequest request = params.getRequest();
		Message message = new Message();
		
		request.setAttribute("patient_id", request.getParameter("patient_id"));
		request.setAttribute("is_new", true);
		request.setAttribute("creation_date", new Date());
		request.setAttribute("patients", getPatients());
		
		try {
			LabList parsedList = listService.parse(request.getParameter("patient_id"));
						
			if (parsedList != null) {
				listDao.create(parsedList);

				message.setContent("Lab list created successfully.");
				message.setType(Message.MessageType.MSG_SUCCESS);

				HttpSession session = params.getRequest().getSession(true);
				session.setAttribute("message", message);
				params.getResponse().sendRedirect(params.getContext().getContextPath() + "/lists/");
			}
		} catch (ValidationException e) {
			message.setContent(e.toString());
			message.setType(Message.MessageType.MSG_ERROR);

			params.getRequest().setAttribute("message", message);
			RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/lists/add.jsp");
			dispatcher.forward(params.getRequest(), params.getResponse());
		}
		
		return true;
	}

	private List<Patient> getPatients() {
		PatientDao patientDao = new SqlPatientDao();
		List<Patient> patientsList = new LinkedList<Patient>();
		patientsList.addAll(patientDao.readAll());

		return patientsList;
	}
}
