package com.epam.protocol.builder;

import com.epam.protocol.builder.impl.server.LoginFailureMessageBuilder;
import com.epam.protocol.builder.impl.server.LoginSuccessMessageBuilder;
import com.epam.protocol.builder.impl.server.ServerChatMessageBuilder;
import com.epam.protocol.domain.message.Message;
import com.epam.protocol.domain.message.constants.ServerMessageType;

public class ServerMessageBuilderFactory {

	public static MessageBuilder<?> getMessageBuilder(byte messageType) {
		MessageBuilder<? extends Message> messageBuilder = null;

		if (messageType == ServerMessageType.SM_LOGIN_SUCCESS) {
			messageBuilder = new LoginSuccessMessageBuilder();
		} else if (messageType == ServerMessageType.SM_LOGIN_FAILURE) {
			messageBuilder = new LoginFailureMessageBuilder();
		} else if (messageType == ServerMessageType.SM_CHAT_MESSAGE) {
			messageBuilder = new ServerChatMessageBuilder();
		}
		return messageBuilder;
	}
}
