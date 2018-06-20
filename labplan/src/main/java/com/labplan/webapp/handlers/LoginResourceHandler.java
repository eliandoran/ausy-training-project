package com.labplan.webapp.handlers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.labplan.persistence.generic.AdministratorDao;
import com.labplan.persistence.sql.SqlAdministratorDao;
import com.labplan.webapp.HandlerParameters;
import com.labplan.webapp.ResourceHandler;

public class LoginResourceHandler implements ResourceHandler {
	private AdministratorDao administratorDao;
	
	public LoginResourceHandler() {
		administratorDao = new SqlAdministratorDao();
	}
	
	@Override
	public String getPath() {
		return "login";
	}

	@Override
	public boolean doGet(HandlerParameters params) throws ServletException, IOException {
		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/login.jsp");
		dispatcher.forward(params.getRequest(), params.getResponse());

		return true;
	}

	@Override
	public boolean doPost(HandlerParameters params) throws ServletException, IOException {
		HttpServletRequest request = params.getRequest();
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");		

		if (administratorDao.validateAuthentication(userName, password)) {
			System.out.println("Authentication successful.");
		} else {
			System.out.println("Authentication failed.");
		}
		
		return true;
	}	
}
