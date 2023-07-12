package org.ibmBootCamp.terceiroTeste.DTO;

import lombok.Getter;
import lombok.Setter;
import org.ibmBootCamp.terceiroTeste.entities.CandidatoStatusHolder;

@Getter
@Setter
public class StatusCandidateIdResponse implements  IProcessResponse<CandidatoStatusHolder> {
	private CandidatoStatusHolder data;
	private String message;

	public StatusCandidateIdResponse(CandidatoStatusHolder status, String message) {
		this.data = status;
		this.message = message;
	}
}
