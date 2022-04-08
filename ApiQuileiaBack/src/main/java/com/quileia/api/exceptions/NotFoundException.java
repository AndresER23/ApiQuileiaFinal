package com.quileia.api.exceptions;

public class NotFoundException extends Exception{

	private static final long serialVersionUID = 417935456695717133L;

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message) {
		super(message);
	}
}
