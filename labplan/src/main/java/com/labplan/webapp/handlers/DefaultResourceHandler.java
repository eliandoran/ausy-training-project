package com.labplan.webapp.handlers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import com.labplan.webapp.HandlerParameters;
import com.labplan.webapp.ResourceHandler;

public class DefaultResourceHandler implements ResourceHandler {
	@Override
	public void doGet(HandlerParameters params) throws ServletException, IOException {
		RequestDispatcher dispatcher = params.getContext().getRequestDispatcher("/app/index.jsp");
		dispatcher.forward(params.getRequest(), params.getResponse());
	}
}
