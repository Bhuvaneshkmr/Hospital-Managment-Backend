package com.stg.exception;

/**
 * @author bhuvaneshkumarg
 *
 */
public class HealthException extends Exception {

	private String errMessage;

	public String getErrMessage() {
		return errMessage;
	}

	public void setErrMessage(String errMessage) {
		this.errMessage = errMessage;
	}

	public HealthException(String errMessage) {
		super();
		this.errMessage = errMessage;
	}

	public HealthException() {
		super();
	}

	@Override
	public String getMessage() {

		return this.errMessage;
	}

}
