package org.ibmBootCamp.terceiroTeste.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CandidatoStatusHolder {
	private final String status;

	public CandidatoStatusHolder(String status) {
		this.status = status;
	}
}
