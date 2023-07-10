package org.ibmBootCamp.terceiroTeste.controllers;

import org.apache.coyote.Response;
import org.ibmBootCamp.terceiroTeste.entities.codCandidatoHolder.CodCandidatoHolder;
import org.ibmBootCamp.terceiroTeste.entities.pessoa.Pessoa;
import org.ibmBootCamp.terceiroTeste.services.ProcessManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import segundoteste.IProcessManager;
import segundoteste.Segundo;
import segundoteste.errors.CandidatoDuplicado;
import segundoteste.errors.NomeInvalido;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hiring")
public class ProcessManagerController {
  private final ProcessManagerService processManagerService;

	public ProcessManagerController(ProcessManagerService processManagerService) {
		this.processManagerService = processManagerService;
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
	public ResponseEntity<?> iniciarProjeto(@RequestBody Pessoa pessoa) {
	String nome = pessoa.getNome();

	ServiceResponse iniciarProcessoResponse =
		processManagerService.iniciarProcesso(nome);

	return setResponse(
			iniciarProcessoResponse.getMessage(),
			iniciarProcessoResponse.getStatus()
		);
	}

    @PostMapping("/schedule")
	public ResponseEntity<?> scheduleInterview(@RequestBody CodCandidatoHolder codCandidatoHolder) {
		Integer codCandidato = codCandidatoHolder.getCodCandidato();

		ServiceResponse scheduleIntervewResponse = processManagerService
			.scheduleInterview(codCandidato);

		return setResponse(
			scheduleIntervewResponse.getMessage(),
			scheduleIntervewResponse.getStatus()
		);
	}

//  @PostMapping("/disqualify");
//  @PostMapping("/approve");
//  @GetMapping("candidate/:id");
//  @GetMapping("approved");

}
