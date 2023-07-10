package org.ibmBootCamp.terceiroTeste.services;

import org.ibmBootCamp.terceiroTeste.controllers.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import segundoteste.IProcessManager;
import segundoteste.Segundo;
import segundoteste.errors.CandidatoDuplicado;
import segundoteste.errors.CandidatoNaoEncontrado;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ProcessManagerService {
	private final IProcessManager processManager = new Segundo();

	private <T> Map<String, String> createJSON(String key, T value) {
		Map<String, String> responseBody = new HashMap<>();
		responseBody.put(key, String.valueOf(value));

		return responseBody;
	}

	public ServiceResponse iniciarProcesso(String name) {
		try {
			int codCandidato = this.processManager.iniciarProcesso(name);

			Map<String, String> responseBody = createJSON("id", codCandidato);

			return new ServiceResponse(responseBody.toString(), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ServiceResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public ServiceResponse scheduleInterview(Integer codCandidato) {
		try {
			this.processManager.marcarEntrevista(codCandidato);

			Map<String, String> responseBody = createJSON(
				"message",
				"Entrevista Marcada"
			);

			return new ServiceResponse(
				responseBody.toString(),
				HttpStatus.OK);
		} catch (CandidatoNaoEncontrado e) {
			return new ServiceResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
