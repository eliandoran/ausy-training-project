package com.labplan.webapp;

import java.io.IOException;

import javax.servlet.ServletException;

public interface ResourceHandler {
	boolean doGet(HandlerParameters params) throws ServletException, IOException;
	
	boolean doPost(HandlerParameters params) throws ServletException, IOException;
}
