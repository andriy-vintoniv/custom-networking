package com.epam.protocol.domain.message.server;

import com.epam.protocol.domain.message.Message;

public class MoveSuccessMessage implements Message {
	private static final byte id = 6;
	private int x;
	private int y;

	public MoveSuccessMessage() {
	}

	public MoveSuccessMessage(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public byte getId() {
		return id;
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
}
