package com.labplan.webapp.handlers;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.labplan.persistence.generic.AdministratorDao;
import com.labplan.persistence.sql.SqlAdministratorDao;
import com.labplan.webapp.AuthenticationHandler;
import com.labplan.webapp.HandlerParameters;
import com.labplan.webapp.LabPlanServlet;
import com.labplan.webapp.ResourceHandler;

public class LoginResourceHandler implements ResourceHandler, AuthenticationHandler {
	private static final Logger LOGGER = Logger.getLogger(LoginResourceHandler.class.getName());
	
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
		HttpSession session = params.getRequest().getSession(true);
		
		String userName = request.getParameter("username");
		String password = request.getParameter("password");		
		String newURL;
		
		if (administratorDao.validateAuthentication(userName, password)) {
			newURL = "/LabPlan/";
			session.setAttribute("login_user", userName);
			LOGGER.info("Authenticated user: " + userName);
		} else {
			newURL = "/LabPlan/login";						
			session.setAttribute("login_failed", true);
			LOGGER.info("Authentication failed for user: " + userName);
		}		
		
		params.getResponse().sendRedirect(newURL);
		
		return true;
	}

	@Override
	public boolean checkAuthentication(HandlerParameters params) {
		HttpSession session = params.getRequest().getSession(true);
		
		Object authUserNameObject = session.getAttribute("login_user");
		return (authUserNameObject != null && authUserNameObject instanceof String && !((String)authUserNameObject).isEmpty());
	}

	@Override
	public void redirectToLogin(HandlerParameters params) throws ServletException, IOException {
		params.getResponse().sendRedirect("/LabPlan/login");
	}	
	
	@Override
	public void disconnect(HandlerParameters params) {
		HttpSession session = params.getRequest().getSession(true);
		
		LOGGER.info("Disconnecting user: " + session.getAttribute("login_user"));
		session.setAttribute("login_user", null);
	}
}
