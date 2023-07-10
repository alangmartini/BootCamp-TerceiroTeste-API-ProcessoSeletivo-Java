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
class StartRouteTests {

    @Autowired
    private MockMvc mockMvc;

	@BeforeEach
	void tearDown() throws Exception {
		mockMvc.perform(
			delete("/api/v1/hiring/reset"));
	}

	@Test
	void testSuccesful() throws Exception {
		String requestBody = "{ \"nome\": \"Fulano de tal10\" }";

		Map<String, Integer> responseBody = new HashMap<>();
		responseBody.put("id", 1);

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
				.andExpect(status().isCreated())
				.andExpect(content().string("{id=3}"));
	}

	void testNomeInvalidoStringVazia() throws Exception {
		String requestBody = "{ \"nome\": \"\" }";

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
			.andExpect(status().isBadRequest())
			.andExpect(content().string("Nome inválido"));
	}

	void testNomeInvalidoSemCorpo() throws Exception {
		String requestBody = "{ }";

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
			.andExpect(status().isBadRequest())
			.andExpect(content().string("Nome inválido"));
	}

	void testCandidatoDuplicado() throws Exception {
		String requestBody = "{ \"nome\": \"Fulano de tal\" }";

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
			.andExpect(status().isOk())
			.andExpect(content().string("1"));

		mockMvc.perform(
				post("/api/v1/hiring/start")
					.contentType(MediaType.APPLICATION_JSON)
					.content(requestBody))
			.andExpect(status().isBadRequest())
			.andExpect(content().string("Candidato Duplicado"));
	}
}
