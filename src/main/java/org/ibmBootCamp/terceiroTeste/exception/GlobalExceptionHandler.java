package org.ibmBootCamp.terceiroTeste.exception;

import org.ibmBootCamp.terceiroTeste.DTO.ErrorProcessResponse;
import org.ibmBootCamp.terceiroTeste.errors.ApiCandidatoDuplicado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import segundoteste.errors.CandidatoDuplicado;
import segundoteste.errors.CandidatoNaoEncontrado;
import segundoteste.errors.NomeInvalido;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(
		value = {
			CandidatoDuplicado.class,
			NomeInvalido.class,
			CandidatoNaoEncontrado.class
		}
	)
	public ResponseEntity<Object> handleApiErrors (
		ApiCandidatoDuplicado e
	) {
		ErrorProcessResponse errorBody = new ErrorProcessResponse(
			e,
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
