package com.epam.protocol.builder.impl;

import java.io.DataInputStream;
import java.io.IOException;

import com.epam.protocol.builder.MessageBuilder;
import com.epam.protocol.domain.message.server.LoginSuccessServerMessage;

public class LoginSuccessMessageBuilder implements MessageBuilder {

	@Override
	public LoginSuccessServerMessage buildMessage(DataInputStream inputStream) {
		LoginSuccessServerMessage message = null;
		try {
			message = new LoginSuccessServerMessage();
			message.setClientId(inputStream.readInt());
			message.setX(inputStream.readInt());
			message.setY(inputStream.readInt());
			message.setColor(inputStream.readInt());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}

}
