package br.com.teste.sonda.aeronave.controller.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	List<FieldMessage> errors = new ArrayList<>();

	public ValidationError(LocalDateTime timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addErrors(String fieldMessage, String message) {
		this.errors.add(new FieldMessage(fieldMessage, message));
	}

}
