package com.epam.protocol.domain.message.server;

import com.epam.protocol.domain.message.Message;
import com.epam.protocol.domain.message.constants.ServerMessageType;

public class ChatServerMessage implements Message {
	private static final byte id = ServerMessageType.SM_CHAT_MESSAGE;
	private String message;

	public ChatServerMessage() {
	}

	public ChatServerMessage(String message) {
		this.message = message;
	}

	public byte getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
