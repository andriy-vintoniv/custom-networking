package com.epam.protocol.builder.impl;

import java.io.DataInputStream;
import java.io.IOException;

import com.epam.protocol.builder.MessageBuilder;
import com.epam.protocol.domain.message.client.ChatClientMessage;

public class ClientChatMessageBuilder implements MessageBuilder {

	@Override
	public ChatClientMessage buildMessage(DataInputStream inputStream) {
		ChatClientMessage chatClientMessage = null;
		try {
			String chatMessage = inputStream.readUTF();
			chatClientMessage = new ChatClientMessage(chatMessage);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return chatClientMessage;
	}
}
