package com.labplan.api.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.json.JSONObject;

import com.labplan.api.exceptions.EntityNotFoundException;

@Provider
public class EntityNotFoundHandler implements ExceptionMapper<EntityNotFoundException> {
	@Override
	public Response toResponse(EntityNotFoundException exception) {
		JSONObject response = new JSONObject();
		response.put("status", "error");
		response.put("error", Status.NOT_FOUND);
		response.put("message", exception.getMessage());
		
		return Response.status(Status.NOT_FOUND).entity(response.toString()).build();
	}
}
