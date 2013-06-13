package com.epam.protocol.handler.impl.client;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import com.epam.protocol.domain.message.constants.ClientMessageType;
import com.epam.protocol.handler.MessageHandler;
import com.epam.protocol.handler.impl.SingleMessageHandler;

public class ClientMessageHandler implements MessageHandler {

	private Map<Byte, SingleMessageHandler> handlers;

	public ClientMessageHandler() {
		initHandlers();
	}

	@Override
	public ByteBuffer handle(ByteBuffer byteBuffer, byte messageType) {
		SingleMessageHandler messageHandler = handlers.get(messageType);
		return messageHandler.handle(byteBuffer);
	}

	private void initHandlers() {
		handlers = new HashMap<Byte, SingleMessageHandler>();

		handlers.put(ClientMessageType.CM_CHAT_MESSAGE,
				new ChatClientMessageHandler());
		handlers.put(ClientMessageType.CM_LOGIN,
				new LoginClientMessageHandler());
		handlers.put(ClientMessageType.CM_MOVE, new MoveClientMessageHandler());
	}
}
