package com.epam.game.receiver.client;

import java.io.DataInputStream;

import com.epam.game.receiver.ClientMessageReceiver;
import com.epam.protocol.builder.impl.ClientChatMessageBuilder;
import com.epam.protocol.domain.message.client.ChatClientMessage;

public class ClientChatMessageReceiver implements
		ClientMessageReceiver<ChatClientMessage> {

	private ClientChatMessageBuilder clientChatMessageBuilder;
	private DataInputStream dataInputStream;

	public ClientChatMessageReceiver(
			ClientChatMessageBuilder clientChatMessageBuilder,
			DataInputStream dataInputStream) {
		this.clientChatMessageBuilder = clientChatMessageBuilder;
		this.dataInputStream = dataInputStream;
	}

	@Override
	public ChatClientMessage receive() {
		ChatClientMessage message = this.clientChatMessageBuilder
				.buildMessage(dataInputStream);

		return message;
	}

	public ClientChatMessageBuilder getClientChatMessageBuilder() {
		return clientChatMessageBuilder;
	}

	public void setClientChatMessageBuilder(
			ClientChatMessageBuilder clientChatMessageBuilder) {
		this.clientChatMessageBuilder = clientChatMessageBuilder;
	}

	public DataInputStream getDataInputStream() {
		return dataInputStream;
	}

	public void setDataInputStream(DataInputStream dataInputStream) {
		this.dataInputStream = dataInputStream;
	}

}
