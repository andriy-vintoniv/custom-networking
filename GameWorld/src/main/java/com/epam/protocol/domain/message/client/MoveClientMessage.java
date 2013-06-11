package com.epam.protocol.domain.message.client;

import com.epam.protocol.domain.message.Message;
import com.epam.protocol.domain.message.constants.ClientMessageType;

public class MoveClientMessage implements Message {
	private static final byte id = ClientMessageType.CM_MOVE;

	private int x;
	private int y;

	public MoveClientMessage() {
	}

	public MoveClientMessage(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public byte getType() {
		return id;
	}
}
