package org.ibmBootCamp.terceiroTeste.DTO;

public interface IProcessResponse<T> {
	T getData();
	String getMessage();
}