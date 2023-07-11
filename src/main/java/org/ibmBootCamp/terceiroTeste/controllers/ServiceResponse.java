package org.ibmBootCamp.terceiroTeste.controllers;

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
	private HttpStatus status;

	/**
	 * Inicializador.
	 * @param bodyOrErrorMessage
	 * @param status
	 */
	public ServiceResponse(T bodyOrErrorMessage, HttpStatus status) {
		this.bodyOrErrorMessage = bodyOrErrorMessage;
		this.status = status;
	}

	public T getMessage() {
		return bodyOrErrorMessage;
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
