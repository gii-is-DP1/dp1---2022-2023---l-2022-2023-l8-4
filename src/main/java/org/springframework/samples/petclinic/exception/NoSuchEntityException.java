package org.springframework.samples.petclinic.exception;

import org.springframework.stereotype.Component;

@Component
public class NoSuchEntityException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;
	
	public NoSuchEntityException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public NoSuchEntityException(String errorCode) {
		super();
		this.errorCode = errorCode;
	}
	public NoSuchEntityException() {
	}	
	
	public static long getSerialVersioniud() {
		return serialVersionUID;
	}
	public String getErrorCode() {
		return this.errorCode;
	}
	public String getErrorMessage() {
		return this.errorMessage;
	}
	public void setErrorCode(String code) {
		this.errorCode = code;
	}
	public void setErrorMessage(String message) {
		this.errorMessage = message;
	}
	
	
}
