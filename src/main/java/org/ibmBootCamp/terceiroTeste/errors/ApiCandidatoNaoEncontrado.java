package org.ibmBootCamp.terceiroTeste.errors;

import org.springframework.http.HttpStatus;
import segundoteste.errors.CandidatoNaoEncontrado;

public class ApiCandidatoNaoEncontrado extends CandidatoNaoEncontrado {
	private final HttpStatus errorCode = HttpStatus.BAD_REQUEST;

	public HttpStatus getErrorCode() {
		return this.errorCode;
	}
}