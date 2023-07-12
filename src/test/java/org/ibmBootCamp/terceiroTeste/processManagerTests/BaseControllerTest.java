package org.ibmBootCamp.terceiroTeste.processManagerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.ibmBootCamp.terceiroTeste.entities.codCandidatoHolder.CodCandidatoHolder;
import org.ibmBootCamp.terceiroTeste.entities.pessoa.Pessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
abstract  public class BaseControllerTest {

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	public void startProcess(Pessoa request) throws Exception {
		String requestBody = objectMapper.writeValueAsString(request);

		mockMvc.perform(
			post("/api/v1/hiring/start")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody));
	}

	public void scheduleInterview(CodCandidatoHolder request) throws Exception {
		String requestBody = objectMapper.writeValueAsString(request);

		mockMvc.perform(
			post("/api/v1/hiring/start")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody));
	}

	public void approveCandidato(CodCandidatoHolder  request) throws Exception {
		String requestBody = objectMapper.writeValueAsString(request);

		mockMvc.perform(
			post("/api/v1/hiring/approve")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody));
	}

	public void resetProcess() throws Exception {
		mockMvc.perform(
			delete("/api/v1/hiring/reset"));
	}
}
