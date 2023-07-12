package org.ibmBootCamp.terceiroTeste.Errors;

import org.springframework.http.HttpStatus;
import segundoteste.errors.NomeInvalido;

public class ApiNomeInvalido extends NomeInvalido implements IAPiError {
	private final HttpStatus errorCode = HttpStatus.BAD_REQUEST;

	public HttpStatus getErrorCode() {
		return this.errorCode;
	}
}
