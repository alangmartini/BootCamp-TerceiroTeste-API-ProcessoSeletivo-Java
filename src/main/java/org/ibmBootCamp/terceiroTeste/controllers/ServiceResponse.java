package org.ibmBootCamp.terceiroTeste.controllers;

import org.springframework.http.HttpStatus;

public class ServiceResponse {
	private String bodyOrErrorMessage;
	private HttpStatus status;

	public ServiceResponse(String message, HttpStatus status) {
		this.bodyOrErrorMessage = message;
		this.status = status;
	}

	public String getMessage() {
		return bodyOrErrorMessage;
	}

	public void setMessage(String message) {
		this.bodyOrErrorMessage = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
