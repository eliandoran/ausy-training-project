package com.labplan.api.helpers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

/**
 * A helper class which generates a JSON error {@link Response} by wrapping an
 * {@link Exception} and specifying the HTTP {@link Status} code to return.
 * <p>
 * Note that this class only contains static members and cannot be instantiated.
 * </p>
 * 
 * @see #getResponse(Status, Exception)
 * 
 * @author Elian Doran
 *
 */
public final class ErrorResponseBuilder {
	private ErrorResponseBuilder() {

	}

	/**
	 * Given an HTTP {@link Status} code and an underlying {@link Exception}
	 * generates a {@link Response} which can be passed onto a client mentioning
	 * that a RESET resource failed and the reason why.
	 * 
	 * <h2>Example</h2> A call to
	 * {@code getResponse(Status.NOT_FOUND, new EntityNotFoundException())} would
	 * return a 404 {@link Response} with the following {@link Response#getEntity()}:
	 * 
	 * <pre>
	 * {
	"error": "NOT_FOUND",
	"message": "The entity could not be found.",
	"status": "error"
	 * }
	 * </pre>
	 * 
	 * @param status
	 *            The HTTP {@link Status} error to return to the client.
	 * @param exception
	 *            The {@link Exception} that generated the error, its message will
	 *            be contained into the response.
	 * @return An HTTP response with {@code status} HTTP {@link Status} and a JSON
	 *         body describing the error.
	 */
	public static Response getResponse(Status status, Exception exception) {
		JSONObject response = new JSONObject();
		response.put("status", "error");
		response.put("error", status);
		response.put("message", exception.getMessage());

		return Response.status(status).entity(response.toString()).type(MediaType.APPLICATION_JSON).build();
	}
}
