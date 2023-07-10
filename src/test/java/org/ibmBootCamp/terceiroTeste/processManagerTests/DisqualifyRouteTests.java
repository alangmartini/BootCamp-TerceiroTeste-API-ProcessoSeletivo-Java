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
class DisqualifyRouteTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void testSuccesfulCandidatoRecebido() throws Exception {
		String requestBody = "{ \"nome\": \"Fulano de tal1\" }";
		String requestBodyCodCandidato = "{ \"codCandidato\": 1 }";

		mockMvc.perform(
			post("/api/v1/hiring/start")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody));

		mockMvc.perform(
				post("/api/v1/hiring/disqualify")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBodyCodCandidato))
			.andExpect(status().isOk())
			.andExpect(content().string("{message=Candidato Desqualificado}"));
	}

	@Test
	void testSuccesfulCandidatoQualificado() throws Exception {
		String requestBody = "{ \"nome\": \"Fulano de tal2\" }";
		String requestBodyCodCandidato = "{ \"codCandidato\": 2 }";

		mockMvc.perform(
			post("/api/v1/hiring/start")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody));

		mockMvc.perform(
				post("/api/v1/hiring/schedule")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBodyCodCandidato));

		mockMvc.perform(
			post("/api/v1/hiring/disqualify")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBodyCodCandidato))
			.andExpect(status().isOk())
			.andExpect(content().string("{message=Candidato Desqualificado}"));
	}

	@Test
	void testSuccesfulCandidatoAprovado() throws Exception {
		String requestBody = "{ \"nome\": \"Fulano de tal3\" }";
		String requestBodyCodCandidato = "{ \"codCandidato\": 3 }";

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
				post("/api/v1/hiring/disqualify")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBodyCodCandidato))
			.andExpect(status().isOk())
			.andExpect(content().string("{message=Candidato Desqualificado}"));
	}

	@Test
	void testCandidatoNaoEncontradoCandidatoRecebido() throws Exception {
		String requestBody = "{ \"nome\": \"Fulano de tal4\" }";
		String requestBodyCodCandidatoNaoEncontrado = "{ \"codCandidato\": 10" +
			" }";

		mockMvc.perform(
			post("/api/v1/hiring/start")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody));

		mockMvc.perform(
				post("/api/v1/hiring/disqualify")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBodyCodCandidatoNaoEncontrado))
			.andExpect(status().isBadRequest())
			.andExpect(content().string("Candidato nao encontrado"));
	}

	@Test
	void testNaoEncontradoCandidatoQualificado() throws Exception {
		String requestBody = "{ \"nome\": \"Fulano de tal5\" }";
		String requestBodyCodCandidato = "{ \"codCandidato\": 5 }";
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
				post("/api/v1/hiring/disqualify")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBodyCodCandidatoNaoEncontrado))
			.andExpect(status().isBadRequest())
			.andExpect(content().string("Candidato nao encontrado"));
	}

	@Test
	void testNaoEncontradoCandidatoAprovado() throws Exception {
		String requestBody = "{ \"nome\": \"Fulano de tal6\" }";
		String requestBodyCodCandidato = "{ \"codCandidato\": 6 }";
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
				.content(requestBodyCodCandidato));

		mockMvc.perform(
				post("/api/v1/hiring/disqualify")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBodyCodCandidatoNaoEncontrado))
			.andExpect(status().isBadRequest())
			.andExpect(content().string("Candidato nao encontrado"));
	}
}
