package com.labplan.webapp;

import java.io.IOException;

import javax.servlet.ServletException;

public interface ResourceHandler {
	void doGet(HandlerParameters params) throws ServletException, IOException;
}
