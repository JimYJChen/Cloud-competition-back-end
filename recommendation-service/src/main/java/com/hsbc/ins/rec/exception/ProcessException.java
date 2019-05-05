package com.hsbc.ins.rec.exception;

public class ProcessException extends RuntimeException{
	 
	private final String errorCode;

	public ProcessException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public ProcessException(Throwable cause, String errorCode) {
		super(cause);
		this.errorCode = errorCode;
	}
	
	public ProcessException(String message, Throwable cause, String errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}
	
}
