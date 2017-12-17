package com.labplan.webapp.handlers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labplan.webapp.HandlerParameters;
import com.labplan.webapp.ResourceHandler;

public class PatientsResourceHandler implements ResourceHandler {
	@Override
	public void doGet(HandlerParameters params) throws ServletException, IOException {	
		if (params.getPath().length == 1) {
			listPatients(params);
		}
	}
	
	void listPatients(HandlerParameters params) throws ServletException, IOException {
		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/listPatients.jsp");
		dispatcher.forward(params.getRequest(), params.getResponse());
	}
}
