package org.ibmBootCamp.terceiroTeste.services;

import org.ibmBootCamp.terceiroTeste.errors.ApiCandidatoDuplicado;
import org.ibmBootCamp.terceiroTeste.errors.ApiNomeInvalido;
import org.ibmBootCamp.terceiroTeste.controllers.ServiceResponse;
import org.ibmBootCamp.terceiroTeste.controllers.succesfulMessages.SucessfulMessage;
import org.ibmBootCamp.terceiroTeste.entities.codCandidatoHolder.CodCandidatoHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import segundoteste.IProcessManager;
import segundoteste.Segundo;
import segundoteste.errors.CandidatoDuplicado;
import segundoteste.errors.CandidatoNaoEncontrado;
import segundoteste.errors.NomeInvalido;

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

	public CodCandidatoHolder iniciarProcesso(String name) throws
		ApiCandidatoDuplicado, ApiNomeInvalido {
		try {
			int codCandidato = this.processManager.iniciarProcesso(name);

			CodCandidatoHolder codCandidatoHolder =
				new CodCandidatoHolder(codCandidato);

			return codCandidatoHolder;
		} catch (NomeInvalido e) {
			throw new ApiNomeInvalido();
		} catch (CandidatoDuplicado e) {
			throw new ApiCandidatoDuplicado();
		}
	}

	public ServiceResponse<SucessfulMessage> scheduleInterview(Integer codCandidato) {
		try {
			this.processManager.marcarEntrevista(codCandidato);

			SucessfulMessage responseBody = new SucessfulMessage("Entrevista " +
				"Marcada");

			return new ServiceResponse<>(
				responseBody,
				HttpStatus.OK);
		} catch (CandidatoNaoEncontrado e) {
			return new ServiceResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}


	public ServiceResponse<SucessfulMessage> disqualifyCandidato(Integer codCandidato) {
		try {
			this.processManager.desqualificarCandidato(codCandidato);

			SucessfulMessage responseBody = new SucessfulMessage("Candidato " +
				"Desqualificado");

			return new ServiceResponse<>(
				responseBody,
				HttpStatus.OK);
		} catch (CandidatoNaoEncontrado e) {
			return new ServiceResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public ServiceResponse<SucessfulMessage> approveCandidato(Integer codCandidato) {
		try {
			this.processManager.aprovarCandidato(codCandidato);

			SucessfulMessage responseBody = new SucessfulMessage("Candidato " +
				"Aprovado");

			return new ServiceResponse<>(
				responseBody,
				HttpStatus.OK);
		} catch (CandidatoNaoEncontrado e) {
			return new ServiceResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public ServiceResponse<SucessfulMessage> reset() {
		this.processManager.reset();
		this.processManager = new Segundo();

		SucessfulMessage responseBody =
			new SucessfulMessage("Processo Reiniciado");

		return new ServiceResponse<>(
			responseBody,
			HttpStatus.OK);
	}

	public ServiceResponse<SucessfulMessage> getCandidatoStatus(int id) {
		try {
			String statusCandidato =
				this.processManager.verificarStatusCandidato(id);

			SucessfulMessage responseBody =
				new SucessfulMessage("Status: " + statusCandidato);

			return new ServiceResponse<>(
				responseBody,
				HttpStatus.OK
			);
		} catch (CandidatoNaoEncontrado e) {
			return new ServiceResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	public ServiceResponse<List<String>> getApprovedCandidatos() {
		try {
			List<String> approvedCandidatos =
				this.processManager.obterAprovados();

			return new ServiceResponse(
				approvedCandidatos,
				HttpStatus.OK
			);
		} catch (Exception e) {
			return new ServiceResponse<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
