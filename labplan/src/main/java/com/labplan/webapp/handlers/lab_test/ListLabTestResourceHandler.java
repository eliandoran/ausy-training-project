package com.labplan.webapp.handlers.lab_test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.labplan.helpers.NumericUtils;
import com.labplan.persistence.sql.SqlLabTestDao;
import com.labplan.services.LabTestService;
import com.labplan.webapp.HandlerParameters;
import com.labplan.webapp.PageInformation;
import com.labplan.webapp.ResourceHandler;

public class ListLabTestResourceHandler implements ResourceHandler {
	private SqlLabTestDao testDao;
	private LabTestService testService;

	public ListLabTestResourceHandler() {
		testDao = new SqlLabTestDao();
		testService = new LabTestService(testDao);
	}

	@Override
	public String getPath() {
		return "tests/";
	}

	@Override
	public boolean doGet(HandlerParameters params) throws ServletException, IOException {
		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/tests/list.jsp");
		HttpServletRequest request = params.getRequest();

		Integer entriesPerPage = 10;
		Integer pageCount = testService.getPageCount(entriesPerPage);
		Integer page = NumericUtils.tryParseInteger(params.getRequest().getParameter("page"));

		page = (page != null ? page : 1);
		page = Math.max(page, 1);
		page = Math.min(page, pageCount);

		request.setAttribute("page", new PageInformation(page, pageCount, entriesPerPage));
		request.setAttribute("tests", testService.getPage(page, entriesPerPage));

		dispatcher.forward(params.getRequest(), params.getResponse());
		return true;
	}

	@Override
	public boolean doPost(HandlerParameters params) throws ServletException, IOException {
		return false;
	}

}
