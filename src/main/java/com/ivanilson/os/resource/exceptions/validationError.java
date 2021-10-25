package com.ivanilson.os.resource.exceptions;

import java.util.ArrayList;
import java.util.List;

public class validationError extends StandarError{
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();

	public validationError() {
		super();
	}

	public validationError(long timestamp, Integer status, String error) {
		super(timestamp, status, error);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}

}
