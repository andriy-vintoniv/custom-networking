package com.epam.protocol.builder.impl;

import java.io.DataInputStream;
import java.io.IOException;

import com.epam.protocol.builder.MessageBuilder;
import com.epam.protocol.domain.message.server.LoginFailureServerMessage;

public class LoginFailureMessageBuilder implements MessageBuilder {

	@Override
	public LoginFailureServerMessage buildMessage(DataInputStream inputStream) {
		LoginFailureServerMessage message = null;
		try {
			message = new LoginFailureServerMessage();
			message.setErrorCode(inputStream.readByte());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
}
