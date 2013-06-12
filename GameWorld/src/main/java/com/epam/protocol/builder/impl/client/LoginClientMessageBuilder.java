package com.epam.protocol.builder.impl.client;

import java.nio.ByteBuffer;

import com.epam.protocol.builder.MessageBuilder;
import com.epam.protocol.domain.message.client.LoginClientMessage;

public class LoginClientMessageBuilder extends
		MessageBuilder<LoginClientMessage> {

	@Override
	public LoginClientMessage buildMessage(ByteBuffer byteBuffer) {
		LoginClientMessage message = null;
		message = new LoginClientMessage();
		String login = getString(byteBuffer);
		message.setLogin(login);
		return message;
	}
}