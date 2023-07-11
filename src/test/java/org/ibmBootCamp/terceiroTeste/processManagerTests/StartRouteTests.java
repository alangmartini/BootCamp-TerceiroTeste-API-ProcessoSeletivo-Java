package org.ibmBootCamp.terceiroTeste.processManagerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ibmBootCamp.terceiroTeste.entities.codCandidatoHolder.CodCandidatoHolder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StartRouteTests {

    @Autowired
    private MockMvc mockMvc;

	private ObjectMapper objectMapper = new ObjectMapper();

	@AfterEach
	void tearDown() throws Exception {
		mockMvc.perform(
			delete("/api/v1/hiring/reset"));
	}

	@Test
	void testSuccesful() throws Exception {
		String requestBody = "{ \"nome\": \"Fulano de tal\" }";

		CodCandidatoHolder codCandidatoHolder = new CodCandidatoHolder(1);

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
				.andExpect(status().isCreated())
				.andExpect(content().string(objectMapper.writeValueAsString(codCandidatoHolder)));
	}

	@Test
	void testSuccesfulMultiple() throws Exception {
		String requestBody = "{ \"nome\": \"Fulano de tal\" }";
		String requestBody2 = "{ \"nome\": \"Beltrano de tal\" }";
		String requestBody3 = "{ \"nome\": \"Ciclano de tal\" }";

		CodCandidatoHolder codCandidatoHolder = new CodCandidatoHolder(1);
		CodCandidatoHolder codCandidatoHolder2 = new CodCandidatoHolder(2);
		CodCandidatoHolder codCandidatoHolder3 = new CodCandidatoHolder(3);

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
			.andExpect(status().isCreated())
			.andExpect(content().string(objectMapper.writeValueAsString(codCandidatoHolder)));

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody2))
			.andExpect(status().isCreated())
			.andExpect(content().string(objectMapper.writeValueAsString(codCandidatoHolder2)));

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody3))
			.andExpect(status().isCreated())
			.andExpect(content().string(objectMapper.writeValueAsString(codCandidatoHolder3)));
	}

	@Test
	void testNomeInvalidoStringVazia() throws Exception {
		String requestBody = "{ \"nome\": \"\" }";

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
			.andExpect(status().isBadRequest())
			.andExpect(content().string("Nome invalido"));
	}

	@Test
	void testNomeInvalidoSemCorpo() throws Exception {
		String requestBody = "{ }";

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
			.andExpect(status().isBadRequest())
			.andExpect(content().string("Nome invalido"));
	}

	@Test
	void testCandidatoDuplicado() throws Exception {
		String requestBody = "{ \"nome\": \"Fulano de tal\" }";

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody));

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
			.andExpect(status().isBadRequest())
			.andExpect(content().string("Candidato ja participa do processo."));
	}
}
