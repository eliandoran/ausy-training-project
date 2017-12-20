package com.labplan.webapp.handlers.lab_list;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.labplan.entities.LabList;
import com.labplan.entities.LabTest;
import com.labplan.helpers.NumericUtils;
import com.labplan.persistence.sql.SqlLabListDao;
import com.labplan.services.LabListService;
import com.labplan.webapp.HandlerParameters;
import com.labplan.webapp.ResourceHandler;

public class EditLabListResourceHandler implements ResourceHandler {
	private SqlLabListDao listDao;
	private LabListService listService;
	
	public EditLabListResourceHandler() {
		listDao = new SqlLabListDao();
		listService = new LabListService(listDao);
	}
	
	@Override
	public String getPath() {
		return "lists/edit";
	}

	@Override
	public boolean doGet(HandlerParameters params) throws ServletException, IOException {
		Integer listId = NumericUtils.tryParseInteger(params.getRequest().getParameter("id"));

		if (listId == null)
			return false;

		LabList list = listDao.read(listId);

		if (list == null)
			return false;
		
		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/lists/edit.jsp");
		dispatcher.forward(params.getRequest(), params.getResponse());

		return true;
	}

	@Override
	public boolean doPost(HandlerParameters params) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return false;
	}
}
