package com.quileia.api.exceptions;

/**
 * Este excepción se genera cuando la cantidad de calorías en un menú supera la cantidad parametrizada.
 * 
 * @author Andre
 *
 */
public class ExceededCaloriesException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3810994599195793038L;

	public ExceededCaloriesException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExceededCaloriesException(String message) {
		super(message);
	}

}
