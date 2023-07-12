package org.ibmBootCamp.terceiroTeste.Errors;

import org.springframework.http.HttpStatus;
import segundoteste.errors.CandidatoDuplicado;

public class ApiCandidatoDuplicado extends CandidatoDuplicado implements IAPiError {
	private final HttpStatus errorCode = HttpStatus.BAD_REQUEST;

	public HttpStatus getErrorCode() {
		return this.errorCode;
	}
}
