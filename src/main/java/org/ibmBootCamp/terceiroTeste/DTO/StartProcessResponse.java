package org.ibmBootCamp.terceiroTeste.DTO;

import org.ibmBootCamp.terceiroTeste.entities.codCandidatoHolder.CodCandidatoHolder;

public class StartProcessResponse implements IProcessResponse<CodCandidatoHolder> {
	private CodCandidatoHolder data;
	private String message;

	public StartProcessResponse(CodCandidatoHolder data, String message) {
		this.data = data;
		this.message = message;
	}

	public CodCandidatoHolder getData() {
		return data;
	}

	public void setData(CodCandidatoHolder data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
