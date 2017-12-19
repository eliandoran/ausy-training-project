package com.labplan.webapp.handlers.lab_list;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.labplan.helpers.NumericUtils;
import com.labplan.persistence.sql.SqlLabListDao;
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
		Integer pageCount = listService.getPageCount(entriesPerPage);
		Integer page = NumericUtils.tryParseInteger(params.getRequest().getParameter("page"));

		page = (page != null ? page : 1);
		page = Math.max(page, 1);
		page = Math.min(page, pageCount);

		request.setAttribute("page", new PageInformation(page, pageCount, entriesPerPage));
		request.setAttribute("lists", listService.getPage(page, entriesPerPage));

		dispatcher.forward(params.getRequest(), params.getResponse());
		return true;
	}

	@Override
	public boolean doPost(HandlerParameters params) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return false;
	}

}
