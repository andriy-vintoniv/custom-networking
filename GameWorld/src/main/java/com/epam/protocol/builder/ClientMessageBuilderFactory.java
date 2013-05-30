package com.epam.protocol.builder;

import com.epam.protocol.builder.impl.ClientChatMessageBuilder;
import com.epam.protocol.builder.impl.LoginClientMessageBuilder;
import com.epam.protocol.builder.impl.MoveClientMessageBuilder;
import com.epam.protocol.domain.message.Message;
import com.epam.protocol.domain.message.constants.ClientMessageType;

public class ClientMessageBuilderFactory {
	public static MessageBuilder<? extends Message> getMessageBuilder(
			byte messageType) {
		MessageBuilder<? extends Message> messageBuilder = null;

		if (messageType == ClientMessageType.CM_LOGIN) {
			messageBuilder = new LoginClientMessageBuilder();
		} else if (messageType == ClientMessageType.CM_MOVE) {
			messageBuilder = new MoveClientMessageBuilder();
		} else if (messageType == ClientMessageType.CM_CHAT_MESSAGE) {
			messageBuilder = new ClientChatMessageBuilder();
		}

		return messageBuilder;
	}
}
