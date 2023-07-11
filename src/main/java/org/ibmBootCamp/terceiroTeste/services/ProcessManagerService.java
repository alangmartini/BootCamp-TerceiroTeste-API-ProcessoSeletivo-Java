package org.ibmBootCamp.terceiroTeste.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ibmBootCamp.terceiroTeste.controllers.ServiceResponse;
import org.ibmBootCamp.terceiroTeste.entities.codCandidatoHolder.CodCandidatoHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import segundoteste.IProcessManager;
import segundoteste.Segundo;
import segundoteste.errors.CandidatoNaoEncontrado;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProcessManagerService {
	private IProcessManager processManager = new Segundo();

	private <T> Map<String, String> createJSON(String key, T value) {
		Map<String, String> responseBody = new HashMap<>();
		responseBody.put(key, String.valueOf(value));

		return responseBody;
	}

	public ServiceResponse iniciarProcesso(String name) {
		try {
			int codCandidato = this.processManager.iniciarProcesso(name);

			CodCandidatoHolder codCandidatoHolder =
				new CodCandidatoHolder(codCandidato);

			return new ServiceResponse(codCandidatoHolder, HttpStatus.CREATED);
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


	public ServiceResponse disqualifyCandidato(Integer codCandidato) {
		try {
			this.processManager.desqualificarCandidato(codCandidato);

			Map<String, String> responseBody = createJSON(
				"message",
				"Candidato Desqualificado"
			);

			return new ServiceResponse(
				responseBody.toString(),
				HttpStatus.OK);
		} catch (CandidatoNaoEncontrado e) {
			return new ServiceResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public ServiceResponse approveCandidato(Integer codCandidato) {
		try {
			this.processManager.aprovarCandidato(codCandidato);

			Map<String, String> responseBody = createJSON(
				"message",
				"Candidato Aprovado"
			);

			return new ServiceResponse(
				responseBody.toString(),
				HttpStatus.OK);
		} catch (CandidatoNaoEncontrado e) {
			return new ServiceResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public ServiceResponse reset() {
		this.processManager.reset();
		this.processManager = new Segundo();

		Map<String, String> responseBody = createJSON(
			"message",
			"Processo Reiniciado"
		);

		return new ServiceResponse(
			responseBody.toString(),
			HttpStatus.OK);
	}

	public ServiceResponse getCandidatoStatus(int id) {
		try {
			String statusCandidato =
				this.processManager.verificarStatusCandidato(id);

			Map<String, String> responseBody = createJSON(
				"message",
				"Status: " + statusCandidato
			);

			return new ServiceResponse(
				responseBody.toString(),
				HttpStatus.OK
			);
		} catch (CandidatoNaoEncontrado e) {
			return new ServiceResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public ServiceResponse getApprovedCandidatos() {
		try {
			List<String> approvedCandidatos =
				this.processManager.obterAprovados();

			return new ServiceResponse(
				approvedCandidatos,
				HttpStatus.OK
			);
		} catch (Exception e) {
			return new ServiceResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
