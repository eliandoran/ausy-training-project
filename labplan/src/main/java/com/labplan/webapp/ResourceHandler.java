package com.labplan.webapp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ResourceHandler {
	void doGet(String[] path, HttpServletRequest request, HttpServletResponse response);
}
