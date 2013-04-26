package com.epam.protocol.domain.message.server;

import com.epam.protocol.domain.message.Message;

public class AnotherPointDeleteMessage implements Message {
	private static final byte id = 5;
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
