package com.epam.protocol.domain.message;

public enum ClientMessageType {

	CM_LOGIN(1), CM_MOVE(2), SM_CHAT_MESSAGE(3);
	private final int id;

	private ClientMessageType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
