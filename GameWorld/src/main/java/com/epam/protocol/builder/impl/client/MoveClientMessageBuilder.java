package com.epam.protocol.builder.impl.client;

import java.nio.ByteBuffer;

import com.epam.protocol.builder.MessageBuilder;
import com.epam.protocol.domain.message.client.MoveClientMessage;

public class MoveClientMessageBuilder extends MessageBuilder<MoveClientMessage> {

	@Override
	public MoveClientMessage buildMessage(ByteBuffer byteBuffer) {
		MoveClientMessage message = null;
		message = new MoveClientMessage();
		message.setX(byteBuffer.getInt());
		message.setY(byteBuffer.getInt());
		return message;
	}
}
