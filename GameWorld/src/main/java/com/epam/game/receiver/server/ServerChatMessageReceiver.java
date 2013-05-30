package com.epam.game.receiver.server;

import java.io.DataInputStream;

import com.epam.game.receiver.ServerMessageReceiver;
import com.epam.protocol.builder.impl.ServerChatMessageBuilder;
import com.epam.protocol.domain.message.server.ChatServerMessage;

public class ServerChatMessageReceiver implements
		ServerMessageReceiver<ChatServerMessage> {

	private ServerChatMessageBuilder serverChatMessageBuilder;
	private DataInputStream dataInputStream;

	public ServerChatMessageReceiver(DataInputStream dis) {
		this.setDataInputStream(dis);
		this.serverChatMessageBuilder = new ServerChatMessageBuilder();
	}

	@Override
	public ChatServerMessage receive() {
		ChatServerMessage message = serverChatMessageBuilder
				.buildMessage(dataInputStream);
		return message;
	}

	public ServerChatMessageBuilder getServerChatMessageBuilder() {
		return serverChatMessageBuilder;
	}

	public void setServerChatMessageBuilder(
			ServerChatMessageBuilder serverChatMessageBuilder) {
		this.serverChatMessageBuilder = serverChatMessageBuilder;
	}

	public DataInputStream getDataInputStream() {
		return dataInputStream;
	}

	public void setDataInputStream(DataInputStream dataInputStream) {
		this.dataInputStream = dataInputStream;
	}

}
