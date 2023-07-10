package org.ibmBootCamp.terceiroTeste.processManagerTests;

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
class ApproveRouteTests {
	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	void tearDown() throws Exception {
		mockMvc.perform(
			delete("/api/v1/hiring/reset"));
	}

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
					.content(requestBodyCodCandidato));

		mockMvc.perform(
			post("/api/v1/hiring/approve")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBodyCodCandidato))
			.andExpect(status().isOk())
			.andExpect(content().string("{message=Candidato Aprovado}"));
	}

	@Test
	void testCandidatoNaoEncontrado() throws Exception {
		String requestBody = "{ \"nome\": \"Fulano de ta1l\" }";
		String requestBodyCodCandidato = "{ \"codCandidato\": 2 }";
		String requestBodyCodCandidatoNaoEncontrado = "{ \"codCandidato\": 10" +
			" }";

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
					.content(requestBodyCodCandidatoNaoEncontrado))
			.andExpect(status().isBadRequest())
			.andExpect(content().string("Candidato nao encontrado"));
	}
}
