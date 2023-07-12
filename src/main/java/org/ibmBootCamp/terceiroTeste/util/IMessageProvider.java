package org.ibmBootCamp.terceiroTeste.util;

public interface IMessageProvider {
	String getMessage(String key, Object... args);
	String getMessage(String key);
}
