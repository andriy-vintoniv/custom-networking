package com.epam.game.receiver.client;

import java.nio.ByteBuffer;

import com.epam.game.receiver.ClientMessageReceiver;
import com.epam.protocol.builder.impl.client.ClientChatMessageBuilder;
import com.epam.protocol.domain.message.client.ChatClientMessage;

public class ClientChatMessageReceiver implements
		ClientMessageReceiver<ChatClientMessage> {

	private ClientChatMessageBuilder clientChatMessageBuilder;
	private ByteBuffer byteBuffer;

	public ClientChatMessageReceiver(
			ClientChatMessageBuilder clientChatMessageBuilder,
			ByteBuffer byteBuffer) {
		this.clientChatMessageBuilder = clientChatMessageBuilder;
		this.byteBuffer = byteBuffer;
	}

	@Override
	public ChatClientMessage receive() {
		ChatClientMessage message = this.clientChatMessageBuilder
				.buildMessage(byteBuffer);

		return message;
	}

	public ClientChatMessageBuilder getClientChatMessageBuilder() {
		return clientChatMessageBuilder;
	}

	public void setClientChatMessageBuilder(
			ClientChatMessageBuilder clientChatMessageBuilder) {
		this.clientChatMessageBuilder = clientChatMessageBuilder;
	}

}
