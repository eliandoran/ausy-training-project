package com.labplan.webapp;

import java.io.IOException;

import javax.servlet.ServletException;

/**
 * Specifies a POST-like <em>resource</em> that can handle different HTTP methods (such as {@code GET} and {@code POST}) which
 * can be accessed at the URL defined by {@link ResourceHandler#getPath()}.
 * 
 * <p>{@link LabPlanServlet} is the parent of these handlers which processes {@code GET} and {@code POST} methods and then
 * passes control to the {@link ResourceHandler} whose path matches.</p>
 * @author elian
 *
 */
public interface ResourceHandler {
	/**
	 * Obtains the URI from which this REST resource can be accessed. The path is relative to the servlet's context path
	 * and have no leading slash. Trailing slashes are optional.
	 * @return The URI from which this REST resource can be accessed.
	 */
	String getPath();

	/**
	 * Represents a method which is called when a {@code GET} request is made towards this resource.
	 * @param params				A {@link HandlerParameters} containing a set of information related to this access, like the servlet request and response.
	 * @return						{@code true} if the operation succeeded (i.e. all GET parameters present and valid, etc.).
	 * @throws ServletException		When the underlying servlet logic failed.
	 * @throws IOException			When the JSP loading failed.
	 */
	boolean doGet(HandlerParameters params) throws ServletException, IOException;

	/**
	 * Represents a method which is called when a {@code POST} request is made towards this resource.
	 * @param params				A {@link HandlerParameters} containing a set of information related to this access, like the servlet request and response.
	 * @return						{@code true} if the operation succeeded (i.e. all GET parameters present and valid, etc.).
	 * @throws ServletException		When the underlying servlet logic failed.
	 * @throws IOException			When the JSP loading failed.
	 */
	boolean doPost(HandlerParameters params) throws ServletException, IOException;
}
