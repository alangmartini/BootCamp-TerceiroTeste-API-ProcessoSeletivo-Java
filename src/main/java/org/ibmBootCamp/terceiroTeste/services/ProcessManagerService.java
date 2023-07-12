package org.ibmBootCamp.terceiroTeste.services;

import org.ibmBootCamp.terceiroTeste.errors.ApiCandidatoDuplicado;
import org.ibmBootCamp.terceiroTeste.errors.ApiCandidatoNaoEncontrado;
import org.ibmBootCamp.terceiroTeste.errors.ApiNomeInvalido;
import org.ibmBootCamp.terceiroTeste.entities.codCandidatoHolder.CodCandidatoHolder;
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

	public void scheduleInterview(Integer codCandidato) {
		try {
			this.processManager.marcarEntrevista(codCandidato);
		} catch (CandidatoNaoEncontrado e) {
			throw new ApiCandidatoNaoEncontrado();
		}
	}


	public void disqualifyCandidato(Integer codCandidato) {
		try {
			this.processManager.desqualificarCandidato(codCandidato);
		} catch (CandidatoNaoEncontrado e) {
			throw new ApiCandidatoNaoEncontrado();
		}
	}

	public void approveCandidato(Integer codCandidato) {
		try {
			this.processManager.aprovarCandidato(codCandidato);
		} catch (CandidatoNaoEncontrado e) {
			throw new ApiCandidatoNaoEncontrado();
		}
	}

	public void reset() {
		this.processManager.reset();
		this.processManager = new Segundo();
	}

	public String getCandidatoStatus(int id) {
		try {
			String statusCandidato =
				this.processManager.verificarStatusCandidato(id);

			return statusCandidato;
		} catch (CandidatoNaoEncontrado e) {
			throw new ApiCandidatoNaoEncontrado();
		}
	}

	public List<String> getApprovedCandidatos() {

		List<String> approvedCandidatos =
			this.processManager.obterAprovados();

		return approvedCandidatos;
	}
}
