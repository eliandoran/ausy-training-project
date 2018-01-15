package com.labplan.api.providers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.labplan.api.helpers.ErrorResponseBuilder;
import com.labplan.exceptions.EntityNotFoundException;

@Provider
public class EntityNotFoundHandler implements ExceptionMapper<EntityNotFoundException> {
	@Override
	public Response toResponse(EntityNotFoundException exception) {
		return ErrorResponseBuilder.getResponse(Status.NOT_FOUND, exception);
	}
}
