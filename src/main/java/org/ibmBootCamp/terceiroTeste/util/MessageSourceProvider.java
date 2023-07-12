package org.ibmBootCamp.terceiroTeste.util;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageSourceProvider implements IMessageProvider {
	private final MessageSource messageSource;

	public MessageSourceProvider(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getMessage(String key) {
		return messageSource.getMessage(key, null, new Locale("pt", "BR"));
	}

	public String getMessage(String key, Object... args) {
		return messageSource.getMessage(key, args, new Locale("pt", "BR"));
	}
}
