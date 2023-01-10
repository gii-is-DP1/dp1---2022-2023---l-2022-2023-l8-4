package org.springframework.samples.petclinic.exception;

public class UnaccesibleGameException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;
	
	public UnaccesibleGameException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public UnaccesibleGameException(String errorCode) {
		super();
		this.errorCode = errorCode;
	}
	public UnaccesibleGameException() {
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
