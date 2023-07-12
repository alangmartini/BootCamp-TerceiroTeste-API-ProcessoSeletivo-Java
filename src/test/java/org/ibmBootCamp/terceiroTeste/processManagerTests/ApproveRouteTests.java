package org.ibmBootCamp.terceiroTeste.processManagerTests;

	import com.fasterxml.jackson.databind.ser.Serializers;
	import org.ibmBootCamp.terceiroTeste.processManagerTests.expectedMessages.ErrorMessages;
	import org.junit.jupiter.api.AfterEach;
	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
	import org.springframework.boot.test.context.SpringBootTest;
	import org.springframework.test.web.servlet.MockMvc;
	import org.springframework.http.MediaType;

	import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
	import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApproveRouteTests extends BaseControllerTest {
	@AfterEach
	void tearDown() throws Exception {
		mockMvc.perform(
			delete("/api/v1/hiring/reset"));
	}

	@Test
	void testSuccesful() throws Exception {
		String requestBodyPessoa = this.createPessoaRequestBody();
		String requestBodyCodCandidato =
			this.createCodCandidatoHolderRequestBody();

		this.startProcess(requestBodyPessoa);
		this.scheduleInterview(requestBodyCodCandidato);

		mockMvc.perform(
			post("/api/v1/hiring/approve")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBodyCodCandidato))
			.andExpect(status().isOk())
			.andExpect(content().string("{\"message\":\"Candidato " +
				"Aprovado\"}"));
	}

	@Test
	void testCandidatoNaoEncontrado() throws Exception {
		String requestBody = this.createPessoaRequestBody();
		String requestBodyCodCandidato =
			this.createCodCandidatoHolderRequestBody();
		String requestBodyCodCandidatoNaoEncontrado =
			this.createCodCandidatoHolderRequestBody(10);

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
			.andExpect(content().string(ErrorMessages.CANDIDATO_NAO_ENCONTRADO));
	}
}
