package com.cignex.rahul.DemoApp.utils;

import java.util.List;

import org.springframework.http.HttpStatus;

public class ResponseBuilder<T> {

	private String message;
	private HttpStatus errorCode;
	private Object data;

	public ResponseBuilder() {
		super();
	}

	public ResponseBuilder(String message, HttpStatus errorCode) {
		super();
		this.message = message;
		this.errorCode = errorCode;
	}
	
	public ResponseBuilder(String message, HttpStatus errorCode, Object data) {
		super();
		this.message = message;
		this.errorCode = errorCode;
		this.data = data;
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
