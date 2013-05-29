package com.epam.protocol.domain.message.server;

import com.epam.protocol.domain.message.Message;
import com.epam.protocol.domain.message.constants.ServerMessageType;

public class AnotherPointDeleteMessage implements Message {
	private static final byte id = ServerMessageType.SM_ANOTHER_POINT_DELETE;
	private int pointId;

	public AnotherPointDeleteMessage() {
	}

	public AnotherPointDeleteMessage(int pointId) {
		this.pointId = pointId;
	}

	public byte getId() {
		return id;
	}

	public int getPointId() {
		return pointId;
	}

	public void setPointId(int pointId) {
		this.pointId = pointId;
	}
}
