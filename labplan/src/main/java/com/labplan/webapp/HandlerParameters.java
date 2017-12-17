package com.labplan.webapp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandlerParameters {	
	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String[] path;
	
	public HandlerParameters(ServletContext context, HttpServletRequest request, HttpServletResponse response, String[] path) {
		this.context = context;
		this.request = request;
		this.response = response;
		this.path = path;
	}
	
	public ServletContext getContext() {
		return context;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}
	
	public HttpServletResponse getResponse() {
		return response;
	}
	
	public String[] getPath() {
		return path;
	}
}
