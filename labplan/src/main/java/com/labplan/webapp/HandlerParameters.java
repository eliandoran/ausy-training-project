package com.labplan.webapp;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Contains a set of object which can prove useful to a {@link ResourceHandler}. Mostly it represents the current
 * state of the {@link LabPlanServlet} such as the {@link ServletContext}, {@link HttpServletResponse}, etc. 
 * @author elian
 *
 */
public class HandlerParameters {
	private ServletContext context;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private String path;

	/**
	 * Creates a new instance of {@link HandlerParameters}, specifying all the contained objects at once.
	 * @param context	The current {@link ServletContext} of the servlet.
	 * @param request	The current request of the servlet.
	 * @param response	The current response of the servlet.
	 * @param path		The URI (relative to the context root) that is being accessed.
	 */
	public HandlerParameters(ServletContext context, HttpServletRequest request, HttpServletResponse response,
			String path) {
		this.context = context;
		this.request = request;
		this.response = response;
		this.path = path;
	}

	/**
	 * Gets the current {@link ServletContext} of the servlet.
	 * @return The current {@link ServletContext} of the servlet.
	 */
	public ServletContext getContext() {
		return context;
	}

	/**
	 * Gets the current request of the servlet.
	 * @return The current request of the servlet.
	 */
	public HttpServletRequest getRequest() {
		return request;
	}

	/**
	 * Gets the current response of the servlet.
	 * @return The current response of the servlet.
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * Gets the URI (relative to the context root) that is being accessed.
	 * <p>Please note that the URI is being stripped of any parts after a semicolon (such as the Java session ID).</p>
	 * @return The URI that is being accessed.
	 */
	public String getPath() {
		return path;
	}
}
