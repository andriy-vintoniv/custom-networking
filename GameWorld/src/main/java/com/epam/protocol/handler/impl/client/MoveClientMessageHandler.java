package com.epam.protocol.handler.impl.client;

import java.nio.ByteBuffer;

import com.epam.protocol.handler.impl.SingleMessageHandler;

class MoveClientMessageHandler implements SingleMessageHandler {

	@Override
	public ByteBuffer handle(ByteBuffer byteBuffer) {
		return byteBuffer;

	}

}
