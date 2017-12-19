package com.labplan.services;

import com.labplan.exceptions.ValidationException;
import com.labplan.helpers.NumericUtils;

public class Validator {
	private ValidationException resultingException;
	
	public Validator() {
		resultingException = new ValidationException();
	}
	
	public ValidationException getResultingException() {
		return resultingException;
	}
	
	public boolean getValidationSucceeded() {
		return resultingException.getFields().size() == 0;
	}
	
	public void validate() throws ValidationException {
		if (!getValidationSucceeded())
			throw resultingException;
	}
	
	public boolean assertNotNull(String fieldName, Object value) {
		if (value == null) {
			resultingException.addField(fieldName, "should not be empty.");
			return false;
		}
		
		return true;
	}
	
	public boolean assertNotEmpty(String fieldName, String value) {
		if (!assertNotNull(fieldName, value)) return false;
		
		if (value.length() == 0) {
			resultingException.addField(fieldName, "should not be empty.");
			return false;
		}
		
		return true;
	}
	
	public boolean assertStringLength(String fieldName, String value, Integer minLength, Integer maxLength) {
		if (!assertNotEmpty(fieldName, value)) return false;
		
		if (value.length() < minLength) {
			resultingException.addField(fieldName, "should be at least " + minLength + " characters long.");
			return false;
		}
		
		if (value.length() > maxLength) {
			resultingException.addField(fieldName, "exceeds the maximum length of " + maxLength + " characters.");
			return false;
		}
		
		return true;
	}
	
	public boolean assertStringIsInteger(String fieldName, String value) {
		if (!assertNotEmpty(fieldName, value)) return false;
		
		Integer intValue = NumericUtils.tryParseInteger(value);
		
		if (intValue == null) {
			resultingException.addField(fieldName, "is not a valid integer.");
			return false;
		}
		
		return true;
	}
}