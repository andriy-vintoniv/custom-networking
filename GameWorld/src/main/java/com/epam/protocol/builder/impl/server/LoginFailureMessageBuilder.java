package com.epam.protocol.builder.impl.server;

import java.nio.ByteBuffer;

import com.epam.protocol.builder.MessageBuilder;
import com.epam.protocol.domain.message.server.LoginFailureServerMessage;

public class LoginFailureMessageBuilder extends
		MessageBuilder<LoginFailureServerMessage> {

	@Override
	public LoginFailureServerMessage buildMessage(ByteBuffer byteBuffer) {
		LoginFailureServerMessage message = null;
		message = new LoginFailureServerMessage();
		message.setErrorCode(byteBuffer.get());
		return message;
	}
}
