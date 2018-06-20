package com.labplan.webapp.handlers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
		HttpSession session = params.getRequest().getSession(true);
		
		HttpServletRequest request = params.getRequest();
		request.setAttribute("login_failed", session.getAttribute("login_failed"));
		
		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/login.jsp");
		dispatcher.forward(params.getRequest(), params.getResponse());		
		
		session.setAttribute("login_failed", null);

		return true;
	}

	@Override
	public boolean doPost(HandlerParameters params) throws ServletException, IOException {
		HttpServletRequest request = params.getRequest();
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");		
		String newURL;
		
		if (administratorDao.validateAuthentication(userName, password)) {
			newURL = "/app/index.jsp";
		} else {
			newURL = "/app/login.jsp";
			
			HttpSession session = params.getRequest().getSession(true);
			session.setAttribute("login_failed", true);
		}
		
		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher(newURL);
		dispatcher.forward(params.getRequest(), params.getResponse());
		
		return true;
	}	
}
