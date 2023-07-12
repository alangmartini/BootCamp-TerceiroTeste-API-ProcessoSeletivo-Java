package org.ibmBootCamp.terceiroTeste.errors;

import org.springframework.http.HttpStatus;
import segundoteste.errors.NomeInvalido;

public class ApiNomeInvalido extends NomeInvalido {
	private final HttpStatus errorCode = HttpStatus.BAD_REQUEST;

	public HttpStatus getErrorCode() {
		return this.errorCode;
	}
}
