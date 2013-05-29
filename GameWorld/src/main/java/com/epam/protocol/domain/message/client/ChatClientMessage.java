package com.epam.protocol.domain.message.client;

import com.epam.protocol.domain.message.Message;
import com.epam.protocol.domain.message.constants.ClientMessageType;

public class ChatClientMessage implements Message {

	private static final byte ID = ClientMessageType.CM_CHAT_MESSAGE;
	private String message;

	public ChatClientMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public byte getId() {
		return ID;
	}

}
