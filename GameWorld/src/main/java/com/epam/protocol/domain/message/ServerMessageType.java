package com.epam.protocol.domain.message;

public enum ServerMessageType {
	SM_LOGIN_SUCCESS(1), SM_LOGIN_FAILURE(2), SM_ANOTHER_POINT_INFO(3), SM_ANOTHER_POINT_MOVE(
			4), SM_ANOTHER_POINT_DELETE(5), SM_MOVE_SUCCESS(6), SM_MOVE_FAILURE(
			7), SM_CHAT_MESSAGE(8);

	private final int id;

	private ServerMessageType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
