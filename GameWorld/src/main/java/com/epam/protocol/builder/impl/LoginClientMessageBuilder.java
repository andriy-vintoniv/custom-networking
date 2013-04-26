package com.epam.protocol.builder.impl;

import java.io.DataInputStream;
import java.io.IOException;

import com.epam.protocol.builder.MessageBuilder;
import com.epam.protocol.domain.message.client.LoginClientMessage;

public class LoginClientMessageBuilder implements MessageBuilder {

	@Override
	public LoginClientMessage buildMessage(DataInputStream inputStream) {
		LoginClientMessage message = null;
		try {
			// byte messageType = inputStream.readByte();
			//
			// if (messageType == ClientMessageType.CM_LOGIN.getId()) {
			message = new LoginClientMessage();

			String login = inputStream.readUTF();
			message.setLogin(login);
			// }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
}