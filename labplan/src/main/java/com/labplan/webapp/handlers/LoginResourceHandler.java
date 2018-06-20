package com.labplan.webapp.handlers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.labplan.webapp.HandlerParameters;
import com.labplan.webapp.ResourceHandler;

public class LoginResourceHandler implements ResourceHandler {
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
		return false;
	}	
}
