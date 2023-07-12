package org.ibmBootCamp.terceiroTeste.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DatalessResponse implements  IProcessResponse<String> {
	private String data;
	private String message;

	public DatalessResponse(String message) {
		this.data = "";
		this.message = message;
	}
}
