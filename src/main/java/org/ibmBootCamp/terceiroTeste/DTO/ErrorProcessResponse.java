package org.ibmBootCamp.terceiroTeste.DTO;

public class ErrorProcessResponse implements IProcessResponse {
	private final String data;
	private final String message;

	public ErrorProcessResponse(String message) {
		this.data = "";
		this.message = message;
	}

	public String getData() {
		return this.data;
	}

	public String getMessage() {
		return this.message;
	}
}
