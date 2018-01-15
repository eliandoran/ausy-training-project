package com.labplan.api.exceptions;

/**
 * A {@link RuntimeException} indicating that a request to get an entity (for example, by ID) failed because no corresponding entity
 * could be found.
 * @author Elian Doran
 * @see com.labplan.entities.Patient
 * @see com.labplan.api.PatientsController
 */
public class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -7343683268510879223L;
	
	@Override
	public String getMessage() {
		return "The entity could not be found.";
	}
}
