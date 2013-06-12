package com.epam.protocol.builder.impl.client;

import java.nio.ByteBuffer;

import com.epam.protocol.builder.MessageBuilder;
import com.epam.protocol.domain.message.client.ChatClientMessage;

public class ClientChatMessageBuilder extends MessageBuilder<ChatClientMessage> {

	@Override
	public ChatClientMessage buildMessage(ByteBuffer byteBuffer) {
		ChatClientMessage chatClientMessage = null;
		String chatMessage = getString(byteBuffer);
		chatClientMessage = new ChatClientMessage(chatMessage);

		return chatClientMessage;
	}
}
