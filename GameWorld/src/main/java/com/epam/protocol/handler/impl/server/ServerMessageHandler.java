package com.epam.protocol.handler.impl.server;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import com.epam.protocol.domain.message.constants.ServerMessageType;
import com.epam.protocol.handler.MessageHandler;
import com.epam.protocol.handler.impl.SingleMessageHandler;

public class ServerMessageHandler implements MessageHandler {

	private Map<Byte, SingleMessageHandler> handlers;

	public ServerMessageHandler() {
		initHandlers();
	}

	@Override
	public ByteBuffer handle(ByteBuffer byteBuffer, byte messageType) {
		SingleMessageHandler messageHandler = handlers.get(messageType);
		return messageHandler.handle(byteBuffer);
	}

	private void initHandlers() {
		handlers = new HashMap<Byte, SingleMessageHandler>();
		handlers.put(Byte.valueOf(ServerMessageType.SM_ANOTHER_POINT_DELETE),
				new AnotherPointDeleteServerMessageHandler());
		handlers.put(Byte.valueOf(ServerMessageType.SM_ANOTHER_POINT_INFO),
				new AnotherPoitnInfoServerMessageHandler());
		handlers.put(Byte.valueOf(ServerMessageType.SM_ANOTHER_POINT_MOVE),
				new AnotherPointMoveServerMessageHandler());
		handlers.put(Byte.valueOf(ServerMessageType.SM_CHAT_MESSAGE),
				new ChatServerMessageHandler());
		handlers.put(Byte.valueOf(ServerMessageType.SM_LOGIN_FAILURE),
				new LoginFailureServerMessageHandler());
		handlers.put(Byte.valueOf(ServerMessageType.SM_LOGIN_SUCCESS),
				new LoginSuccessServerMessageHandler());
		handlers.put(Byte.valueOf(ServerMessageType.SM_MOVE_FAILURE),
				new MoveFailureServerMessageHandler());
		handlers.put(Byte.valueOf(ServerMessageType.SM_MOVE_SUCCESS),
				new MoveSuccessServerMessageHandler());
	}
}
