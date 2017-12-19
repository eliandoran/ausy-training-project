package com.labplan.webapp.handlers.lab_test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.labplan.entities.LabTest;
import com.labplan.helpers.IntUtils;
import com.labplan.persistence.sql.SqlLabTestDao;
import com.labplan.services.LabTestService;
import com.labplan.webapp.HandlerParameters;
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
		Integer testId = IntUtils.tryParse(params.getRequest().getParameter("id"));
		
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
		// TODO Auto-generated method stub
		return false;
	}

}
