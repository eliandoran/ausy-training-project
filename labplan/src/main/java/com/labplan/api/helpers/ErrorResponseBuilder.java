package com.labplan.api.helpers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

public class ErrorResponseBuilder {
	public static Response getResponse(Status status, Exception exception) {
		JSONObject response = new JSONObject();
		response.put("status", "error");
		response.put("error", status);
		response.put("message", exception.getMessage());
		
		return Response
				.status(status)
				.entity(response.toString())
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
}
