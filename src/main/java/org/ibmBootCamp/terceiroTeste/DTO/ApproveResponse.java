package org.ibmBootCamp.terceiroTeste.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApproveResponse implements  IProcessResponse<String> {
	private String data;
	private String message;

	public ApproveResponse(String message) {
		this.data = "";
		this.message = message;
	}
}
