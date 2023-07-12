package org.ibmBootCamp.terceiroTeste.processManagerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ibmBootCamp.terceiroTeste.entities.codCandidatoHolder.CodCandidatoHolder;
import org.ibmBootCamp.terceiroTeste.entities.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
abstract  public class BaseControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	public void startProcess(String requestBody) throws Exception {

		mockMvc.perform(
			post("/api/v1/hiring/start")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody));
	}

	public void scheduleInterview(String requestBody) throws Exception {
		mockMvc.perform(
			post("/api/v1/hiring/schedule")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody));
	}

	public void approveCandidato(String requestBody) throws Exception {
		mockMvc.perform(
			post("/api/v1/hiring/approve")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody));
	}

	public void resetProcess() throws Exception {
		mockMvc.perform(
			delete("/api/v1/hiring/reset"));
	}

	public String createPessoaRequestBody() throws Exception {
		Pessoa pessoa = new Pessoa("Fulano de tal");

		String requestBody = this.objectMapper.writeValueAsString(pessoa);

		return requestBody;
	}

	public String createPessoaRequestBody(String nome) throws Exception {
		Pessoa pessoa = new Pessoa(nome);

		String requestBody = this.objectMapper.writeValueAsString(pessoa);

		return requestBody;
	}

	public String createCodCandidatoHolderRequestBody() throws Exception {
		CodCandidatoHolder codCandidatoHolder = new CodCandidatoHolder(1);

		String requestBody = this.objectMapper.writeValueAsString(codCandidatoHolder);

		return requestBody;
	}

	public String createCodCandidatoHolderRequestBody(Integer codCandidato) throws Exception {
		CodCandidatoHolder codCandidatoHolder = new CodCandidatoHolder(codCandidato);

		String requestBody = this.objectMapper.writeValueAsString(codCandidatoHolder);

		return requestBody;
	}
}
