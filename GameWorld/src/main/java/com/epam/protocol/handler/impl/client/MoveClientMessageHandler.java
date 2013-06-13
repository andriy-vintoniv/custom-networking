package com.epam.protocol.handler.impl.client;

import java.nio.ByteBuffer;

import com.epam.protocol.handler.impl.SingleMessageHandler;
import com.epam.server.Connection;
import com.epam.server.ConnectionContainer;
import com.epam.server.MessageSender;

class MoveClientMessageHandler implements SingleMessageHandler {

	@Override
	public ByteBuffer handle(ByteBuffer byteBuffer,
			ConnectionContainer<?> connectionContainer,
			MessageSender<?> messageSender, Connection<?> connection) {
		return byteBuffer;

	}

}
