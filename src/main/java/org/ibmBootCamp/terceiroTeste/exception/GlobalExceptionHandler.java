package org.ibmBootCamp.terceiroTeste.exception;

import org.ibmBootCamp.terceiroTeste.DTO.ErrorProcessResponse;
import org.ibmBootCamp.terceiroTeste.errors.ApiCandidatoDuplicado;
import org.ibmBootCamp.terceiroTeste.errors.ApiCandidatoNaoEncontrado;
import org.ibmBootCamp.terceiroTeste.errors.ApiNomeInvalido;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(value = { ApiNomeInvalido.class })
	public ResponseEntity<Object> handleApiNomeInvalido (
		ApiNomeInvalido e
	) {
		ErrorProcessResponse errorBody = new ErrorProcessResponse(
			e.getMessage()
		);

		return new ResponseEntity<>(
			errorBody,
			e.getErrorCode()
		);
	}

	@ExceptionHandler(value = { ApiCandidatoDuplicado.class })
	public ResponseEntity<Object> handleApiCandidatoDuplicado (
		ApiCandidatoDuplicado e
	) {
		ErrorProcessResponse errorBody = new ErrorProcessResponse(
			e.getMessage()
		);

		return new ResponseEntity<>(
			errorBody,
			e.getErrorCode()
		);
	}

	@ExceptionHandler(value = { ApiCandidatoNaoEncontrado.class })
	public ResponseEntity<Object> handleApiCandidatoNaoEncontrado (
		ApiCandidatoNaoEncontrado e
	) {
		ErrorProcessResponse errorBody = new ErrorProcessResponse(
			e.getMessage()
		);

		return new ResponseEntity<>(
			errorBody,
			e.getErrorCode()
		);
	}

	@ExceptionHandler(value = { Exception.class })
	public ResponseEntity<Object> handleException(Exception e) {
		return new ResponseEntity<>(
			e.getMessage(),
			HttpStatus.INTERNAL_SERVER_ERROR
		);
	}
}
