package org.ibmBootCamp.terceiroTeste.Errors;

import org.springframework.http.HttpStatus;
import segundoteste.errors.CandidatoNaoEncontrado;

public class ApiCandidatoNaoEncontrado extends CandidatoNaoEncontrado implements IAPiError {
	private final HttpStatus errorCode = HttpStatus.BAD_REQUEST;

	public HttpStatus getErrorCode() {
		return this.errorCode;
	}
}