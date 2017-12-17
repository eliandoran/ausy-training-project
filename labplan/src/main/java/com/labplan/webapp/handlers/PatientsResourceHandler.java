package com.labplan.webapp.handlers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.labplan.helpers.IntUtils;
import com.labplan.persistence.generic.PatientDao;
import com.labplan.persistence.sql.SqlPatientDao;
import com.labplan.services.PatientService;
import com.labplan.webapp.HandlerParameters;
import com.labplan.webapp.PageInformation;
import com.labplan.webapp.ResourceHandler;

public class PatientsResourceHandler implements ResourceHandler {
	@Override
	public void doGet(HandlerParameters params) throws ServletException, IOException {	
		if (params.getPath().length == 1) {
			listPatients(params);
		}
	}
	
	void listPatients(HandlerParameters params) throws ServletException, IOException {
		PatientDao patientDao = new SqlPatientDao();
		PatientService patientService = new PatientService(patientDao);
		Integer page = IntUtils.tryParse(params.getRequest().getParameter("page"));
		
		page = (page != null ? page : 1);
		
		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/listPatients.jsp");
		HttpServletRequest request = params.getRequest();
		
		PageInformation pageInfo = new PageInformation();
		pageInfo.setCurrent(page);
		request.setAttribute("page", pageInfo);
		
		dispatcher.forward(params.getRequest(), params.getResponse());
	}
}
