package com.batchprocess.exception;

public class ValidationException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorLine;

	public ValidationException(String errorLine) {
		super("Validation Failed ");
		this.errorLine = errorLine;
	}
	
	public ValidationException(Throwable cuase, String errorLine) {
		super("Validation Failed ", cuase);
		this.errorLine = errorLine;
	}
	
	public String errorLine() {
		return this.errorLine;
	}
}
