package org.ibmBootCamp.terceiroTeste.Errors;

import org.springframework.http.HttpStatus;

public interface ApiError {
	public HttpStatus getErrorCode();
	public String getMessage();
}
