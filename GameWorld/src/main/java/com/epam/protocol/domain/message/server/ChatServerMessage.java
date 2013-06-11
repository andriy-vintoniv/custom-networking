package com.epam.protocol.domain.message.server;

import com.epam.protocol.domain.message.Message;
import com.epam.protocol.domain.message.constants.ServerMessageType;

public class ChatServerMessage implements Message {
	private static final byte id = ServerMessageType.SM_CHAT_MESSAGE;
	private int pointId;
	private String message;

	public ChatServerMessage() {
	}

	public ChatServerMessage(String message, int pointId) {
		this.message = message;
		this.pointId = pointId;
	}

	public byte getType() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getPointId() {
		return pointId;
	}

	public void setPointId(int pointId) {
		this.pointId = pointId;
	}
}
