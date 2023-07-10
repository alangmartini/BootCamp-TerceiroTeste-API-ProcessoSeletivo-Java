package org.ibmBootCamp.terceiroTeste.services;

import org.ibmBootCamp.terceiroTeste.controllers.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import segundoteste.IProcessManager;
import segundoteste.Segundo;
import segundoteste.errors.CandidatoDuplicado;

import java.util.Optional;

@Service
public class ProcessManagerService {
	private final IProcessManager processManager = new Segundo();

	public ServiceResponse iniciarProcesso(String name) {
		try {
			int codCandidato = this.processManager.iniciarProcesso(name);

			return new ServiceResponse(String.valueOf(codCandidato), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ServiceResponse(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
