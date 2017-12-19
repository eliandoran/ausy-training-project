package com.labplan.services;

import com.labplan.exceptions.ValidationException;
import com.labplan.helpers.NumericUtils;

/**
 * A helper class for validating user input given as a {@link String} with a variety of assertions.
 * 
 * <p>An instance of {@link Validator} keeps its own {@link ValidationException}. Should an
 * assertion like {@link Validator#assertNotNull(String, Object)} fail, {@link Validator} uses
 * {@link ValidationException#addField(String, String)} to keep track of the failed assertion.
 * 
 * <p>To be able to validate multiple fields at once, {@link ValidationException} is only thrown
 * when {@link Validator#validate()} is called.</p>
 * 
 * @author Elian Doran
 *
 */
public class Validator {
	private ValidationException resultingException;
	
	/**
	 * Creates a new instance of {@link Validator}.
	 */
	public Validator() {
		resultingException = new ValidationException();
	}
	
	/**
	 * Gets the {@link ValidationException} containing all the failed assertions. The exception is updated
	 * using {@link ValidationException#addField(String, String)} every time an assertion like
	 * {@link Validator#assertNotNull(String, Object)} fails.
	 * @return A {@link ValidationException} containing all the failed assertions.
	 */
	public ValidationException getResultingException() {
		return resultingException;
	}
	
	/**
	 * Determines whether the all the assertions have succeeded.
	 * @return {@code true} if no assertions failed, {@code false} otherwise.
	 */
	public boolean getValidationSucceeded() {
		return resultingException.getFields().size() == 0;
	}
	
	/**
	 * If any assertions have failed, the method throws the underlying {@link ValidationException},
	 * otherwise nothing happens.
	 * @throws ValidationException	If any assertions have failed.
	 */
	public void validate() throws ValidationException {
		if (!getValidationSucceeded())
			throw resultingException;
	}
	
	/**
	 * Asserts that the given {@code value} is not {@code null}.
	 * @param fieldName		The name of the field being validated, it will represent the key of {@link ValidationException#getFields()}.
	 * @param value			The value being used for the assertion.
	 * @return {@code true} if the assertion succeeded, {@code false} otherwise.
	 */
	public boolean assertNotNull(String fieldName, Object value) {
		if (value == null) {
			resultingException.addField(fieldName, "should not be empty.");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Asserts that the given {@link String} {@code value} is not {@code null} and its length is non-zero. 
	 * @param fieldName		The name of the field being validated, it will represent the key of {@link ValidationException#getFields()}.
	 * @param value			The value being used for the assertion.
	 * @return {@code true} if the assertion succeeded, {@code false} otherwise.
	 */
	public boolean assertNotEmpty(String fieldName, String value) {
		if (!assertNotNull(fieldName, value)) return false;
		
		if (value.length() == 0) {
			resultingException.addField(fieldName, "should not be empty.");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Asserts that the given {@link String} {@code value} has a length between {@code minLength} and {@code maxLength}.
	 * @param fieldName		The name of the field being validated, it will represent the key of {@link ValidationException#getFields()}.
	 * @param value			The value being used for the assertion.
	 * @param minLength		The minimum length of the string, or {@code null} to skip the minimum check.
	 * @param maxLength		The maximum length of the string, or {@code null} to skip the maximum check.
	 * @return {@code true} if the assertion succeeded, {@code false} otherwise.
	 */
	public boolean assertStringLength(String fieldName, String value, Integer minLength, Integer maxLength) {
		if (!assertNotEmpty(fieldName, value)) return false;
		
		if (minLength != null && value.length() < minLength) {
			resultingException.addField(fieldName, "should be at least " + minLength + " characters long.");
			return false;
		}
		
		if (maxLength != null && value.length() > maxLength) {
			resultingException.addField(fieldName, "exceeds the maximum length of " + maxLength + " characters.");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Asserts that the given {@link String} {@code value} contains only alphabetic characters. Whitespace is allowed if
	 * {@code permitWhitespace} is {@code true} and any character in {@code exceptions} is also allowed.
	 * 
	 * <p>The method uses {@link Character#isAlphabetic(char)} and {@link Character#isWhitespace(char)} for
	 * validation.</p>
	 * 
	 * @param fieldName			The name of the field being validated, it will represent the key of {@link ValidationException#getFields()}.
	 * @param value				The value being used for the assertion.
	 * @param permitWhitespace	Whether to allow whitespace characters, along alphabetic characters.
	 * @param exceptions		A string containing characters to be allowed, on top of alphabetic and whitespace characters.
	 * @return {@code true} if the assertion succeeded, {@code false} otherwise.
	 */
	public boolean assertStringIsAlphabetic(String fieldName, String value, boolean permitWhitespace, String exceptions) {
		if (!assertNotEmpty(fieldName, value)) return false;
		
		for (int pos = 0; pos < value.length(); pos++) {
			char ch = value.charAt(pos);
			
			if (Character.isWhitespace(ch) && permitWhitespace)
				continue;
			
			if (exceptions.indexOf(ch) != -1)
				continue;
			
			if (!Character.isAlphabetic(ch)) {
				resultingException.addField(fieldName, "cannot contain any non-alphabetic characters.");
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Asserts that the given {@link String} {@code value} can be successfully converted to an {@link Integer}.
	 * @param fieldName		The name of the field being validated, it will represent the key of {@link ValidationException#getFields()}.
	 * @param value			The value being used for the assertion.
	 * @return {@code true} if the assertion succeeded, {@code false} otherwise.
	 */
	public boolean assertStringIsInteger(String fieldName, String value) {
		if (!assertNotEmpty(fieldName, value)) return false;
		
		Integer intValue = NumericUtils.tryParseInteger(value);
		
		if (intValue == null) {
			resultingException.addField(fieldName, "is not a valid integer.");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Asserts that the given {@link String} {@code value} can be successfully converted to a {@link Float}.
	 * @param fieldName		The name of the field being validated, it will represent the key of {@link ValidationException#getFields()}.
	 * @param value			The value being used for the assertion.
	 * @return {@code true} if the assertion succeeded, {@code false} otherwise.
	 */
	public boolean assertStringIsFloat(String fieldName, String value) {
		if (!assertNotEmpty(fieldName, value)) return false;
		
		Float floatValue = NumericUtils.tryParseFloat(value);
		
		if (floatValue == null) {
			resultingException.addField(fieldName, "is not a valid decimal number.");
			return false;
		}
		
		return true;
	}
	
	/**
	 * Asserts that the given {@link Integer} {@code value} is between {@code minValue} and {@code maxValue}.
	 * @param fieldName		The name of the field being validated, it will represent the key of {@link ValidationException#getFields()}.
	 * @param value			The value being used for the assertion.
	 * @param minValue		The minimum value or {@code null} to skip the minimum check.
	 * @param maxValue		The maximum value or {@code null} to skip the maximum check.
	 * @return {@code true} if the assertion succeeded, {@code false} otherwise.
	 */
	public boolean assertIntegerInRange(String fieldName, Integer value, Integer minValue, Integer maxValue) {
		if (minValue != null && value < minValue) {
			resultingException.addField(fieldName, "should be greater than or equal to " + minValue);
			return false;
		}
		
		if (maxValue != null && value > maxValue) {
			resultingException.addField(fieldName, "should be less than or equal to " + maxValue);
			return false;
		}
		
		return true;
	}
	
	/**
	 * Asserts that the given {@link String} {@code value} is an {@link Integer} with values between  is between {@code minValue} and {@code maxValue}.
	 * @param fieldName		The name of the field being validated, it will represent the key of {@link ValidationException#getFields()}.
	 * @param value			The value being used for the assertion.
	 * @param minValue		The minimum value or {@code null} to skip the minimum check.
	 * @param maxValue		The maximum value or {@code null} to skip the maximum check.
	 * @return {@code true} if the assertion succeeded, {@code false} otherwise.
	 */
	public boolean assertIntegerInRange(String fieldName, String value, Integer minValue, Integer maxValue) {
		if (!assertStringIsInteger(fieldName, value)) return false;
		
		return assertIntegerInRange(fieldName, Integer.parseInt(value), minValue, maxValue);
	}
}