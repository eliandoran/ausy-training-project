package com.labplan.webapp.handlers;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labplan.webapp.ResourceHandler;

public class PatientsResourceHandler implements ResourceHandler {
	@Override
	public void doGet(String[] path, ServletContext context, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		context.getRequestDispatcher("/app/listPatients.jsp").forward(request, response);			
	}
}
