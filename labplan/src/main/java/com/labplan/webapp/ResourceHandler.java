package com.labplan.webapp;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ResourceHandler {
	void doGet(HandlerParameters params) throws ServletException, IOException;
}
