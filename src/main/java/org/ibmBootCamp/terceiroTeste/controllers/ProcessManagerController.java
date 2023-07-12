package org.ibmBootCamp.terceiroTeste.controllers;

import org.ibmBootCamp.terceiroTeste.DTO.ApprovedResponse;
import org.ibmBootCamp.terceiroTeste.DTO.DatalessResponse;
import org.ibmBootCamp.terceiroTeste.DTO.StartProcessResponse;
import org.ibmBootCamp.terceiroTeste.DTO.StatusCandidateIdResponse;
import org.ibmBootCamp.terceiroTeste.entities.CandidatoStatusHolder;
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
	public ResponseEntity<StartProcessResponse> iniciarProcesso(@RequestBody Pessoa pessoa) {
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

		processManagerService
			.scheduleInterview(codCandidato);

	    DatalessResponse responseBody = new DatalessResponse(
		    this.messageProvider.getMessage("schedule.success")
	    );

	    return ResponseEntity
		    .status(HttpStatus.CREATED)
		    .contentType((MediaType.APPLICATION_JSON))
		    .body(responseBody);
	}

	@PostMapping("/disqualify")
	public ResponseEntity<?> desqualificarCandidato(@RequestBody CodCandidatoHolder codCandidatoHolder) {
		Integer codCandidato = codCandidatoHolder.getCodCandidato();

		processManagerService
			.disqualifyCandidato(codCandidato);

		DatalessResponse responseBody = new DatalessResponse(
			this.messageProvider.getMessage("disqualify.success")
		);

		return ResponseEntity
			.status(HttpStatus.CREATED)
			.contentType((MediaType.APPLICATION_JSON))
			.body(responseBody);
	}

    @PostMapping("/approve")
    public ResponseEntity<DatalessResponse> aprovarCandidato(@RequestBody CodCandidatoHolder codCandidatoHolder) {
	    Integer codCandidato = codCandidatoHolder.getCodCandidato();

	    processManagerService.approveCandidato(codCandidato);

	    DatalessResponse responseBody = new DatalessResponse(
		    this.messageProvider.getMessage("approve.success")
	    );

	    return ResponseEntity
		    .status(HttpStatus.CREATED)
		    .contentType((MediaType.APPLICATION_JSON))
		    .body(responseBody);
    }

	@DeleteMapping("/reset")
	public ResponseEntity<?> resetProcess() {
		processManagerService.reset();

		DatalessResponse responseBody = new DatalessResponse(
			this.messageProvider.getMessage("reset.success")
		);

		// Processo Reiniciado
		return ResponseEntity
			.status(HttpStatus.CREATED)
			.contentType((MediaType.APPLICATION_JSON))
			.body(responseBody);
	}

	@GetMapping ("/status/candidate/{id}")
	public ResponseEntity<?> getCandidatoStatus(@PathVariable("id") int id) {
		String candidatoStatus =
			processManagerService.getCandidatoStatus(id);

		CandidatoStatusHolder statusHolder =
			new CandidatoStatusHolder(candidatoStatus);

		StatusCandidateIdResponse responseBody = new StatusCandidateIdResponse(
			statusHolder,
			this.messageProvider.getMessage("status.candidate.id.sucess", candidatoStatus)
		);

		return ResponseEntity
			.status(HttpStatus.CREATED)
			.contentType((MediaType.APPLICATION_JSON))
			.body(responseBody);
	}

  @GetMapping("approved")
	public ResponseEntity<?> getApprovedCandidatos() {
		List<String> approvedCandidatos =
			processManagerService.getApprovedCandidatos();

	  ApprovedResponse responseBody = new ApprovedResponse(
		  approvedCandidatos,
		  this.messageProvider.getMessage("approved.success")
	  );

	  return ResponseEntity
		  .status(HttpStatus.CREATED)
		  .contentType((MediaType.APPLICATION_JSON))
		  .body(responseBody);
  }

}
