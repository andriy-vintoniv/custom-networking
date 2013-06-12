package com.epam.protocol.builder.impl.server;

import java.nio.ByteBuffer;

import com.epam.protocol.builder.MessageBuilder;
import com.epam.protocol.domain.message.server.ChatServerMessage;

public class ServerChatMessageBuilder extends MessageBuilder<ChatServerMessage> {

	@Override
	public ChatServerMessage buildMessage(ByteBuffer byteBuffer) {
		ChatServerMessage message = new ChatServerMessage();
		message.setPointId(byteBuffer.getInt());
		message.setMessage(getString(byteBuffer));

		return message;
	}
}
