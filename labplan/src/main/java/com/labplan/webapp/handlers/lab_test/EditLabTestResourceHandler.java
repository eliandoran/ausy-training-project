package com.labplan.webapp.handlers.lab_test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.labplan.entities.LabTest;
import com.labplan.exceptions.ValidationError;
import com.labplan.helpers.NumericUtils;
import com.labplan.persistence.sql.SqlLabTestDao;
import com.labplan.services.LabTestService;
import com.labplan.webapp.HandlerParameters;
import com.labplan.webapp.Message;
import com.labplan.webapp.ResourceHandler;

public class EditLabTestResourceHandler implements ResourceHandler {
	private SqlLabTestDao testDao;
	private LabTestService testService;
	
	public EditLabTestResourceHandler() {
		testDao = new SqlLabTestDao();
		testService = new LabTestService(testDao);
	}
	
	@Override
	public String getPath() {
		return "tests/edit";
	}

	@Override
	public boolean doGet(HandlerParameters params) throws ServletException, IOException {
		Integer testId = NumericUtils.tryParseInteger(params.getRequest().getParameter("id"));
		
		if (testId == null)
			return false;
		
		LabTest test = testDao.read(testId);
		
		if (test == null)
			return false;
		
		HttpServletRequest request = params.getRequest();
		request.setAttribute("name", test.getName());
		request.setAttribute("description", test.getDescription());
		request.setAttribute("value_min", test.getValueMin());
		request.setAttribute("value_max", test.getValueMax());
		
		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/tests/edit.jsp");
		request.setAttribute("test", test);
		dispatcher.forward(params.getRequest(), params.getResponse());
		
		return true;
	}

	@Override
	public boolean doPost(HandlerParameters params) throws ServletException, IOException {
		HttpServletRequest request = params.getRequest();
		
		// Parse the `id` GET parameter
		Integer testId = NumericUtils.tryParseInteger(params.getRequest().getParameter("id"));
		
		if (testId == null)
			return false;
		
		for (String field : new String[] { "name", "description", "value_min", "value_max" })
			request.setAttribute(field, request.getParameter(field));
		
		Message message = new Message();
		
		// Parse the lab test data in POST.
		try {
			LabTest parsedTest = testService.parse(
					request.getParameter("name"),
					request.getParameter("description"),
					request.getParameter("value_min"),
					request.getParameter("value_max"));
			
			if (parsedTest != null) {
				parsedTest.setId(testId);
				testDao.update(parsedTest);
				
				message.setContent("Lab test updated successfully.");
				message.setType(Message.MessageType.MSG_SUCCESS);
				
				HttpSession session = params.getRequest().getSession(true);
				session.setAttribute("message", message);
				params.getResponse().sendRedirect(params.getContext().getContextPath() + "/tests/");
			}
		} catch (ValidationError e) {
			message.setContent(e.toString());
			message.setType(Message.MessageType.MSG_ERROR);
			
			params.getRequest().setAttribute("message", message);
			RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/tests/edit.jsp");
			dispatcher.forward(params.getRequest(), params.getResponse());
		}
		
		return true;
	}

}
