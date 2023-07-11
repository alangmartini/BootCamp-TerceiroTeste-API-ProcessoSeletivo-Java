package org.ibmBootCamp.terceiroTeste.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;

/**
 * Objeto criado para padronizar respostas do Service. Como
 * o Spring Stringifica o corpo automaticamente, bodyOrErrorMessage pode
 * ser uma String ou não.
 *
 * @param <T> Tipo de bodyOrErrorsMessage.
 */
public class ServiceResponse<T> {
	private T bodyOrErrorMessage;
	private String errorMessage;
	private HttpStatus status;
	private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * Inicializador.
	 * @param bodyOrErrorMessage
	 * @param status
	 */
	public ServiceResponse(T bodyOrErrorMessage, HttpStatus status) {
		this.bodyOrErrorMessage = bodyOrErrorMessage;
		this.status = status;
	}

	public ServiceResponse(String errorMessage, HttpStatus status) {
		this.errorMessage = errorMessage;
		this.status = status;
	}

	public String getMessage() {
		if (this.errorMessage != null) {

			return this.errorMessage;
		}

		try {
			return this.objectMapper.writeValueAsString(this.bodyOrErrorMessage);
		} catch (Exception e) {
			return "Failed to parse response";
		}
	}

	public void setMessage(T message) {
		this.bodyOrErrorMessage = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}
}
