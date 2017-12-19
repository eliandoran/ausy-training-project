package com.labplan.webapp.handlers.lab_test;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.labplan.entities.LabTest;
import com.labplan.exceptions.ValidationException;
import com.labplan.persistence.sql.SqlLabTestDao;
import com.labplan.services.LabTestService;
import com.labplan.webapp.HandlerParameters;
import com.labplan.webapp.Message;
import com.labplan.webapp.ResourceHandler;

public class AddLabTestResourceHandler implements ResourceHandler {
	private SqlLabTestDao testDao;
	private LabTestService testService;

	public AddLabTestResourceHandler() {
		testDao = new SqlLabTestDao();
		testService = new LabTestService(testDao);
	}

	@Override
	public String getPath() {
		return "tests/add";
	}

	@Override
	public boolean doGet(HandlerParameters params) throws ServletException, IOException {
		HttpServletRequest request = params.getRequest();
		request.setAttribute("is_new", true);

		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/tests/add.jsp");
		dispatcher.forward(params.getRequest(), params.getResponse());

		return true;
	}

	@Override
	public boolean doPost(HandlerParameters params) throws ServletException, IOException {
		HttpServletRequest request = params.getRequest();
		Message message = new Message();

		for (String field : new String[] { "name", "description", "value_min", "value_max" })
			request.setAttribute(field, request.getParameter(field));

		// Parse the lab test data in POST.
		try {
			LabTest parsedTest = testService.parse(request.getParameter("name"), request.getParameter("description"),
					request.getParameter("value_min"), request.getParameter("value_max"));

			if (parsedTest != null) {
				testDao.create(parsedTest);

				message.setContent("Lab test created successfully.");
				message.setType(Message.MessageType.MSG_SUCCESS);

				HttpSession session = params.getRequest().getSession(true);
				session.setAttribute("message", message);
				params.getResponse().sendRedirect(params.getContext().getContextPath() + "/tests/");
			}
		} catch (ValidationException e) {
			message.setContent(e.toString());
			message.setType(Message.MessageType.MSG_ERROR);

			params.getRequest().setAttribute("message", message);
			RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/tests/add.jsp");
			dispatcher.forward(params.getRequest(), params.getResponse());
		}

		return true;
	}

}
