package org.ibmBootCamp.terceiroTeste.DTO;

public interface IProcessResponse<T> {
	T getData();
	String getMessage();
	void setData(T data);
	void setMessage(String message);
}