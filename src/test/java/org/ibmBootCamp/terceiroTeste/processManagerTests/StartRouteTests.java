package org.ibmBootCamp.terceiroTeste.processManagerTests;

import org.ibmBootCamp.terceiroTeste.controllers.ProcessManagerController;
import org.ibmBootCamp.terceiroTeste.controllers.succesfulMessages.SucessfulMessage;
import org.ibmBootCamp.terceiroTeste.entities.codCandidatoHolder.CodCandidatoHolder;
import org.ibmBootCamp.terceiroTeste.entities.pessoa.Pessoa;
import org.ibmBootCamp.terceiroTeste.processManagerTests.expectedMessages.ErrorMessages;
import org.ibmBootCamp.terceiroTeste.processManagerTests.expectedMessages.SucessMessages;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith (SpringRunner.class)
class StartRouteTests extends BaseControllerTest {
	@AfterEach
	void tearDown() throws Exception {
		this.resetProcess();
	}

	@Test
	void testSuccesful() throws Exception {
		Pessoa pessoa = new Pessoa("Fulano de tal");

		String requestBody = this.objectMapper.writeValueAsString(pessoa);

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
				.andExpect(status().isCreated())
				.andExpect(content().string(SucessMessages.START_PROCESS));
	}

	@Test
	void testSuccesfulMultiple() throws Exception {
		Pessoa pessoa = new Pessoa("Fulano de tal");
		Pessoa pessoa2 = new Pessoa("Beltrano de tal");
		Pessoa pessoa3 = new Pessoa("Ciclano de tal");


		String requestBody = this.objectMapper.writeValueAsString(pessoa);
		String requestBody2 = this.objectMapper.writeValueAsString(pessoa2);
		String requestBody3 = this.objectMapper.writeValueAsString(pessoa3);

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
			.andExpect(status().isCreated())
			.andExpect(content().string(SucessMessages.START_PROCESS));

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody2))
			.andExpect(status().isCreated())
			.andExpect(content().string(SucessMessages.START_PROCESS_2));

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody3))
			.andExpect(status().isCreated())
			.andExpect(content().string(SucessMessages.START_PROCESS_3));
	}

	@Test
	void testNomeInvalidoStringVazia() throws Exception {
		Pessoa pessoa = new Pessoa("");

		String requestBody = this.objectMapper.writeValueAsString(pessoa);

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(ErrorMessages.NOME_INVALIDO));
	}

	@Test
	void testNomeInvalidoSemCorpo() throws Exception {
		String requestBody = "{ }";

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(ErrorMessages.NOME_INVALIDO));
	}

	@Test
	void testCandidatoDuplicado() throws Exception {
		Pessoa pessoa = new Pessoa("Fulano de tal");

		String requestBody = this.objectMapper.writeValueAsString(pessoa);

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody));

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
			.andExpect(status().isBadRequest())
			.andExpect(content().string(ErrorMessages.CANDIDATO_DUPLICADO));
	}
}
