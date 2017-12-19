package com.labplan.webapp.handlers.lab_list;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.labplan.persistence.sql.SqlLabListDao;
import com.labplan.services.LabListService;
import com.labplan.webapp.HandlerParameters;
import com.labplan.webapp.ResourceHandler;

public class AddLabListResourceHandler implements ResourceHandler {
	private SqlLabListDao listDao;
	private LabListService listService;
	
	public AddLabListResourceHandler() {
		listDao = new SqlLabListDao();
		listService = new LabListService(listDao);
	}

	@Override
	public String getPath() {
		return "lists/add";
	}

	@Override
	public boolean doGet(HandlerParameters params) throws ServletException, IOException {
		HttpServletRequest request = params.getRequest();
		request.setAttribute("creation_date", new Date());
		request.setAttribute("is_new", true);
		
		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/lists/add.jsp");
		dispatcher.forward(params.getRequest(), params.getResponse());
		return true;
	}

	@Override
	public boolean doPost(HandlerParameters params) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return false;
	}		
}
