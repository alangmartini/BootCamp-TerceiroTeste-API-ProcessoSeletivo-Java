package org.ibmBootCamp.terceiroTeste.controllers;

import org.ibmBootCamp.terceiroTeste.DTO.IProcessResponse;
import org.ibmBootCamp.terceiroTeste.DTO.StartProcessResponse;
import org.ibmBootCamp.terceiroTeste.controllers.succesfulMessages.SucessfulMessage;
import org.ibmBootCamp.terceiroTeste.entities.codCandidatoHolder.CodCandidatoHolder;
import org.ibmBootCamp.terceiroTeste.entities.pessoa.Pessoa;
import org.ibmBootCamp.terceiroTeste.services.ProcessManagerService;
import org.ibmBootCamp.terceiroTeste.util.IMessageProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller que maneja as routas para manipular o processo Seletivo.
 * Você pode verificar o retorno de cada endpoint ao verificar o tipo de
 * ReponseService.
 */
@RestController
@RequestMapping("/api/v1/hiring")
public class ProcessManagerController {
    private final ProcessManagerService processManagerService;
	private final IMessageProvider messageProvider;

	public ProcessManagerController(
		ProcessManagerService processManagerService,
		IMessageProvider messageProvider
	) {
		this.processManagerService = processManagerService;
		this.messageProvider = messageProvider;
	}

	public <T> ResponseEntity<T> setResponse(
	  T body,
	  HttpStatus status
	) {
	return ResponseEntity
		.status(status)
		.contentType(MediaType.APPLICATION_JSON)
		.body(body);
	}

	@PostMapping("/start")
	public ResponseEntity<IProcessResponse> iniciarProcesso(@RequestBody Pessoa pessoa) {
		String nome = pessoa.getNome();

		CodCandidatoHolder codCandidatoHolder =
			processManagerService.iniciarProcesso(nome);

		StartProcessResponse responseBody = new StartProcessResponse(
			codCandidatoHolder,
			this.messageProvider.getMessage("start.success")
		);

		return ResponseEntity
			.status(HttpStatus.CREATED)
			.contentType((MediaType.APPLICATION_JSON))
			.body(responseBody);
	}

    @PostMapping("/schedule")
	public ResponseEntity<?> marcarEntrevista(@RequestBody CodCandidatoHolder codCandidatoHolder) {
		Integer codCandidato = codCandidatoHolder.getCodCandidato();

		// "Entrevista Marcada"
		ServiceResponse<SucessfulMessage> scheduleInterviewResponse = processManagerService
			.scheduleInterview(codCandidato);

		return setResponse(
			scheduleInterviewResponse.getMessage(),
			scheduleInterviewResponse.getStatus()
		);
	}

	@PostMapping("/disqualify")
	public ResponseEntity<?> desqualificarCandidato(@RequestBody CodCandidatoHolder codCandidatoHolder) {
		Integer codCandidato = codCandidatoHolder.getCodCandidato();

		// "Candidato Desqualificado"
		ServiceResponse<SucessfulMessage> disqualifyResponse = processManagerService
			.disqualifyCandidato(codCandidato);

		return setResponse(
			disqualifyResponse.getMessage(),
			disqualifyResponse.getStatus()
		);
	}

    @PostMapping("/approve")
    public ResponseEntity<?> aprovarCandidato(@RequestBody CodCandidatoHolder codCandidatoHolder) {
	    Integer codCandidato = codCandidatoHolder.getCodCandidato();

		// Candidato Aprovado
	    ServiceResponse<SucessfulMessage> approveResponse = processManagerService
		    .approveCandidato(codCandidato);

	    return setResponse(
		    approveResponse.getMessage(),
		    approveResponse.getStatus()
	    );
    }

	@DeleteMapping("/reset")
	public ResponseEntity<?> resetProcess() {
		ServiceResponse<SucessfulMessage> resetResponse = processManagerService.reset();

		// Processo Reiniciado
		return setResponse(
			resetResponse.getMessage(),
			resetResponse.getStatus()
		);
	}

	@GetMapping ("/status/candidate/{id}")
	public ResponseEntity<?> getCandidatoStatus(@PathVariable("id") int id) {
		// Status: StatusDoCandidato
		// ex: Status: Recebido
		ServiceResponse<SucessfulMessage> candidatoStatusResponse =
			processManagerService.getCandidatoStatus(id);

		return setResponse(
			candidatoStatusResponse.getMessage(),
			candidatoStatusResponse.getStatus()
		);
	}

  @GetMapping("approved")
	public ResponseEntity<?> getApprovedCandidatos() {
		ServiceResponse<List<String>> approvedCandidatosResponse =
			processManagerService.getApprovedCandidatos();

		return setResponse(
			approvedCandidatosResponse.getMessage(),
			approvedCandidatosResponse.getStatus()
		);
  }

}
