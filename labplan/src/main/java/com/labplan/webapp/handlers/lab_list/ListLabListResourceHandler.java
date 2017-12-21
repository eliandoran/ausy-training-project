package com.labplan.webapp.handlers.lab_list;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.labplan.entities.LabList;
import com.labplan.entities.Patient;
import com.labplan.helpers.NumericUtils;
import com.labplan.persistence.generic.PatientDao;
import com.labplan.persistence.sql.SqlLabListDao;
import com.labplan.persistence.sql.SqlPatientDao;
import com.labplan.services.LabListService;
import com.labplan.webapp.HandlerParameters;
import com.labplan.webapp.PageInformation;
import com.labplan.webapp.ResourceHandler;

public class ListLabListResourceHandler implements ResourceHandler {
	private SqlLabListDao listDao;
	private LabListService listService;

	public ListLabListResourceHandler() {
		listDao = new SqlLabListDao();
		listService = new LabListService(listDao);
	}

	@Override
	public String getPath() {
		return "lists/";
	}

	@Override
	public boolean doGet(HandlerParameters params) throws ServletException, IOException {
		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/lists/list.jsp");
		HttpServletRequest request = params.getRequest();

		Integer entriesPerPage = 10;
		Integer pageCount = null;
		Integer page = NumericUtils.tryParseInteger(params.getRequest().getParameter("page"));
		
		Patient patient = null;
		List<LabList> lists;
		
		if (request.getParameter("patient") != null) {
			// Try to parse the patient ID.
			Integer patientId = NumericUtils.tryParseInteger(request.getParameter("patient"));
			
			if (patientId == null)
				return false;
			
			// Try to read the given patient.
			PatientDao patientDao = new SqlPatientDao();
			patient = patientDao.read(patientId);
			
			if (patient == null)
				return false;
			
			pageCount = listService.getPageCount(patient, entriesPerPage);
		} else {
			pageCount = listService.getPageCount(entriesPerPage);
		}
		
		page = (page != null ? page : 1);
		page = Math.max(page, 1);
		page = Math.min(page, pageCount);
		
		if (patient != null) {
			lists = listService.getPage(patient, page, entriesPerPage);
		} else {
			lists = listService.getPage(page, entriesPerPage);
		}

		request.setAttribute("page", new PageInformation(page, pageCount, entriesPerPage));
		request.setAttribute("lists", lists);

		dispatcher.forward(params.getRequest(), params.getResponse());
		return true;
	}
	
	@Override
	public boolean doPost(HandlerParameters params) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return false;
	}

}
