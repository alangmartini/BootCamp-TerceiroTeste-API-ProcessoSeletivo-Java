package org.ibmBootCamp.terceiroTeste.DTO;

public class ErrorProcessResponse implements IProcessResponse {
	private final Exception data;
	private final String message;

	public ErrorProcessResponse(Exception data, String message) {
		this.data = data;
		this.message = message;
	}

	public Exception getData() {
		return this.data;
	}

	public String getMessage() {
		return this.message;
	}
}
