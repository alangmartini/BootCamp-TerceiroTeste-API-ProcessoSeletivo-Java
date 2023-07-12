package org.ibmBootCamp.terceiroTeste.DTO;

import lombok.Getter;
import lombok.Setter;
import org.ibmBootCamp.terceiroTeste.entities.CandidatoStatusHolder;

import java.util.List;

@Getter
@Setter
public class ApprovedResponse implements  IProcessResponse<List<String>> {
	private List<String> data;
	private String message;

	public ApprovedResponse(List<String> status, String message) {
		this.data = status;
		this.message = message;
	}
}
