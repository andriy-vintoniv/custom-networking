package com.epam.protocol.builder.impl.server;

import java.nio.ByteBuffer;

import com.epam.protocol.builder.MessageBuilder;
import com.epam.protocol.domain.message.server.LoginSuccessServerMessage;

public class LoginSuccessMessageBuilder extends
		MessageBuilder<LoginSuccessServerMessage> {

	@Override
	public LoginSuccessServerMessage buildMessage(ByteBuffer byteBuffer) {
		LoginSuccessServerMessage message = null;

		message = new LoginSuccessServerMessage();
		message.setClientId(byteBuffer.getInt());
		message.setX(byteBuffer.getInt());
		message.setY(byteBuffer.getInt());
		message.setColor(byteBuffer.getInt());

		return message;
	}

}
