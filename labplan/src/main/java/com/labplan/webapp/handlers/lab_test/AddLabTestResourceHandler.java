package com.labplan.webapp.handlers.lab_test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.labplan.entities.LabTest;
import com.labplan.helpers.NumericUtils;
import com.labplan.persistence.sql.SqlLabTestDao;
import com.labplan.services.LabTestService;
import com.labplan.webapp.HandlerParameters;
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
		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/tests/add.jsp");
		dispatcher.forward(params.getRequest(), params.getResponse());
		
		return true;
	}

	@Override
	public boolean doPost(HandlerParameters params) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return false;
	}

}
