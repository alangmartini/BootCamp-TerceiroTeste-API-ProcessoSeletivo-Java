package org.ibmBootCamp.terceiroTeste.processManagerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;
import segundoteste.candidatos.Candidato;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GetApprovedRouteTests {
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
		String requestBodyCodCandidato = "{ \"codCandidato\": 1 }";

		List<Candidato> mockApprovedCandidatos = new ArrayList<>();

		mockApprovedCandidatos.add(new Candidato("Fulano de tal", 1));

		mockMvc.perform(
			post("/api/v1/hiring/start")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody));

		mockMvc.perform(
			post("/api/v1/hiring/schedule")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBodyCodCandidato));

		mockMvc.perform(
				post("/api/v1/hiring/approve")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBodyCodCandidato));

		mockMvc.perform(
			get("/api/v1/hiring/approved"))
				.andExpect(status().isOk())
				.andExpect(content().string(objectMapper.writeValueAsString(mockApprovedCandidatos)));
	}

	@Test
	void testSuccesfulMultiple() throws Exception {
		String requestBody = "{ \"nome\": \"Fulano de tal\" }";
		String requestBody2 = "{ \"nome\": \"Beltrano de tal\" }";
		String requestBodyCodCandidato = "{ \"codCandidato\": 1 }";
		String requestBodyCodCandidato2 = "{ \"codCandidato\": 2 }";

		List<Candidato> mockApprovedCandidatos = new ArrayList<>();

		mockApprovedCandidatos.add(new Candidato("Fulano de tal", 1));
		mockApprovedCandidatos.add(new Candidato("Beltrano de tal", 2));

		mockMvc.perform(
			post("/api/v1/hiring/start")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody));
		mockMvc.perform(
			post("/api/v1/hiring/start")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody2));

		mockMvc.perform(
			post("/api/v1/hiring/schedule")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBodyCodCandidato));
		mockMvc.perform(
			post("/api/v1/hiring/schedule")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBodyCodCandidato2));

		mockMvc.perform(
			post("/api/v1/hiring/approve")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBodyCodCandidato));
		mockMvc.perform(
			post("/api/v1/hiring/approve")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBodyCodCandidato2));

		mockMvc.perform(
				get("/api/v1/hiring/approved"))
			.andExpect(status().isOk())
			.andExpect(content().string(objectMapper.writeValueAsString(mockApprovedCandidatos)));
	}

	void testSuccesfulEmpty() throws Exception {
		List<Candidato> mockApprovedCandidatos = new ArrayList<>();


		mockMvc.perform(
				get("/api/v1/hiring/approved"))
			.andExpect(status().isOk())
			.andExpect(content().string(objectMapper.writeValueAsString(mockApprovedCandidatos)));
	}
}
