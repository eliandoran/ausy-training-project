package com.labplan.webapp.handlers.patient;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.labplan.helpers.NumericUtils;
import com.labplan.persistence.sql.SqlPatientDao;
import com.labplan.services.PatientService;
import com.labplan.webapp.HandlerParameters;
import com.labplan.webapp.PageInformation;
import com.labplan.webapp.ResourceHandler;

public class ListPatientResourceHandler implements ResourceHandler {
	private SqlPatientDao patientDao;
	private PatientService patientService;

	public ListPatientResourceHandler() {
		patientDao = new SqlPatientDao();
		patientService = new PatientService(patientDao);
	}
	
	@Override
	public String getPath() {
		return "patients/";
	}

	@Override
	public boolean doGet(HandlerParameters params) throws ServletException, IOException {
		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/patients/list.jsp");
		HttpServletRequest request = params.getRequest();

		Integer entriesPerPage = 10;
		Integer pageCount = patientService.getPageCount(entriesPerPage);
		Integer page = NumericUtils.tryParseInteger(params.getRequest().getParameter("page"));

		page = (page != null ? page : 1);
		page = Math.max(page, 1);
		page = Math.min(page, pageCount);

		request.setAttribute("page", new PageInformation(page, pageCount, entriesPerPage));
		request.setAttribute("patients", patientService.getPage(page, entriesPerPage));

		dispatcher.forward(params.getRequest(), params.getResponse());
		return true;
	}

	@Override
	public boolean doPost(HandlerParameters params) throws ServletException, IOException {
		return false;
	}
}
