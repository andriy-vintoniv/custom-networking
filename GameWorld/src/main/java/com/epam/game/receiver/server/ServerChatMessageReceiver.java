package com.epam.game.receiver.server;

import java.nio.ByteBuffer;

import com.epam.game.receiver.ServerMessageReceiver;
import com.epam.protocol.builder.impl.server.ServerChatMessageBuilder;
import com.epam.protocol.domain.message.server.ChatServerMessage;

public class ServerChatMessageReceiver implements
		ServerMessageReceiver<ChatServerMessage> {

	private ServerChatMessageBuilder serverChatMessageBuilder;
	private ByteBuffer byteBuffer;

	public ServerChatMessageReceiver(ByteBuffer byteBuffer) {
		this.byteBuffer = byteBuffer;
		this.serverChatMessageBuilder = new ServerChatMessageBuilder();
	}

	@Override
	public ChatServerMessage receive() {
		ChatServerMessage message = serverChatMessageBuilder
				.buildMessage(byteBuffer);
		return message;
	}

	public ServerChatMessageBuilder getServerChatMessageBuilder() {
		return serverChatMessageBuilder;
	}

	public void setServerChatMessageBuilder(
			ServerChatMessageBuilder serverChatMessageBuilder) {
		this.serverChatMessageBuilder = serverChatMessageBuilder;
	}
}
