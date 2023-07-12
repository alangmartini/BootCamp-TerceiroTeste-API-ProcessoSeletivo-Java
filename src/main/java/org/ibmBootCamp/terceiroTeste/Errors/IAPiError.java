package org.ibmBootCamp.terceiroTeste.Errors;

import org.springframework.http.HttpStatus;

public interface IAPiError {
	HttpStatus getErrorCode();
}
