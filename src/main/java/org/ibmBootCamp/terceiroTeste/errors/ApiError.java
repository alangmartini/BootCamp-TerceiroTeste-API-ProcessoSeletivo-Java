package org.ibmBootCamp.terceiroTeste.errors;

import org.springframework.http.HttpStatus;

public interface ApiError {
	public HttpStatus getErrorCode();
	public String getMessage();
}
