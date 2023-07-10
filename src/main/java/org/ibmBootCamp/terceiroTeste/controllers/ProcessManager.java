package org.ibmBootCamp.terceiroTeste.controllers;

import org.ibmBootCamp.terceiroTeste.entities.pessoa.Pessoa;
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

@RestController
@RequestMapping("/api/v1/hiring")
public class ProcessManager {
  private final IProcessManager processManagerService = new Segundo();

  public <T> ResponseEntity<T> setResponse(
	  HttpStatus status,
	  T body
  ) {
	return ResponseEntity
		.status(status)
		.contentType(MediaType.APPLICATION_JSON)
		.body(body);
  }
  @PostMapping("/start")
  public ResponseEntity<?> iniciarProjeto(@RequestBody Pessoa pessoa) {
    String nome = pessoa.getNome();

	try {
		int codCandidatoCriado = this.processManagerService
			.iniciarProcesso(nome);

		Map<String, Integer> responseBody = new HashMap<>();
		responseBody.put("id", codCandidatoCriado);

		return setResponse(HttpStatus.CREATED, responseBody);
	} catch (CandidatoDuplicado e) {
		return setResponse(HttpStatus.BAD_REQUEST, e.getMessage());
	} catch (NomeInvalido e) {
		return setResponse(HttpStatus.BAD_REQUEST, e.getMessage());
	}
  }


//  @PostMapping("/schedule");
//  @PostMapping("/disqualify");
//  @PostMapping("/approve");
//  @GetMapping("candidate/:id");
//  @GetMapping("approved");

}
