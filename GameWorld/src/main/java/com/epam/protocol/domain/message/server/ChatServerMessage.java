package com.epam.protocol.domain.message.server;

import com.epam.protocol.domain.message.Message;

public class ChatServerMessage implements Message {
	private static final byte id = 8;
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
