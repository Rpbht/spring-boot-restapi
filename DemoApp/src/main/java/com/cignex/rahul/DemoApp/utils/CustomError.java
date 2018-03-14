package com.cignex.rahul.DemoApp.utils;

import org.springframework.http.HttpStatus;

public class CustomError {

	private String message;
	private HttpStatus errorCode;

	public CustomError() {
		super();
	}
	public CustomError(String message) {
		super();
		this.message = message;
	}
	public CustomError(String message, HttpStatus errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(HttpStatus errorCode) {
		this.errorCode = errorCode;
	}
}
