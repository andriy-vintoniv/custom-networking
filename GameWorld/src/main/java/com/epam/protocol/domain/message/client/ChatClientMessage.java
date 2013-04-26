package com.epam.protocol.domain.message.client;

import com.epam.protocol.domain.message.Message;

public class ChatClientMessage implements Message {

	private static final byte id = 8;
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
		return id;
	}

}
