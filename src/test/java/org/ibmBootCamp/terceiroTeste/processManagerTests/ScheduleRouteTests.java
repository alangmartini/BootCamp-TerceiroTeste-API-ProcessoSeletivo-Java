package org.ibmBootCamp.terceiroTeste.processManagerTests;

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
class ScheduleRouteTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testSuccesful() throws Exception {
		String requestBody = "{ \"nome\": \"Fulano de tal\" }";
		String requestBodyCodCandidato = "{ \"codCandidato\": 1 }";

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody));


		mockMvc.perform(
				post("/api/v1/hiring/schedule")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBodyCodCandidato))
			.andExpect(status().isOk())
			.andExpect(content().string("{message=Entrevista Marcada}"));
	}

	void testCandidatoNaoEncontrado() throws Exception {
		String requestBody = "{ \"nome\": \"Fulano de tal\" }";
		String requestBodyCodCandidato = "{ \"codCandidato\": 10 }";

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody));

		mockMvc.perform(
				post("/api/v1/hiring/schedule")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBodyCodCandidato))
			.andExpect(status().isBadRequest())
			.andExpect(content().string("Candidato não encontrado"));
	}
}
