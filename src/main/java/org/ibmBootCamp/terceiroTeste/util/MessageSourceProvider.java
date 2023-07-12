package org.ibmBootCamp.terceiroTeste.util;

import org.springframework.context.MessageSource;
import java.util.Locale;

public class MessageSourceProvider {
	private final MessageSource messageSource;

	public MessageSourceProvider(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getMessage(String key, Object... args) {
		return messageSource.getMessage(key, args, Locale.getDefault());
	}
}
