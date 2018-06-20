package com.labplan.webapp.handlers.admin;

import java.io.IOException;

import javax.servlet.ServletException;

import com.labplan.webapp.HandlerParameters;
import com.labplan.webapp.ResourceHandler;

public class DisconnectResourceHandler implements ResourceHandler {
	@Override
	public String getPath() {
		return "admin/disconnect";
	}

	@Override
	public boolean doGet(HandlerParameters params) throws ServletException, IOException {
		params.getAuthHandler().disconnect(params);
		params.getAuthHandler().redirectToLogin(params);
		return true;
	}

	@Override
	public boolean doPost(HandlerParameters params) throws ServletException, IOException {
		return false;
	}
}
