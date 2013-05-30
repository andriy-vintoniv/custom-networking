package com.epam.protocol.builder.impl;

import java.io.DataInputStream;
import java.io.IOException;

import com.epam.protocol.builder.MessageBuilder;
import com.epam.protocol.domain.message.client.MoveClientMessage;

public class MoveClientMessageBuilder implements
		MessageBuilder<MoveClientMessage> {

	@Override
	public MoveClientMessage buildMessage(DataInputStream inputStream) {
		MoveClientMessage message = null;
		try {
			message = new MoveClientMessage();
			message.setX(inputStream.readInt());
			message.setY(inputStream.readInt());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return message;
	}
}
