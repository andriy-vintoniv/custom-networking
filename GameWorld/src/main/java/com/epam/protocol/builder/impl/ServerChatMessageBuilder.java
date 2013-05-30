package com.epam.protocol.builder.impl;

import java.io.DataInputStream;
import java.io.IOException;

import com.epam.protocol.builder.MessageBuilder;
import com.epam.protocol.domain.message.server.ChatServerMessage;

public class ServerChatMessageBuilder implements
		MessageBuilder<ChatServerMessage> {

	@Override
	public ChatServerMessage buildMessage(DataInputStream inputStream) {
		ChatServerMessage message = new ChatServerMessage();

		try {
			message.setPointId(inputStream.readInt());
			message.setMessage(inputStream.readUTF());
		} catch (IOException e) {
			e.printStackTrace();
		}

		return message;
	}

}
