package com.labplan.webapp;

import java.io.IOException;

import javax.servlet.ServletException;

public interface AuthenticationHandler {
	boolean checkAuthentication(HandlerParameters params);
	
	void redirectToLogin(HandlerParameters params) throws ServletException, IOException;
	
	void disconnect(HandlerParameters params);
}
