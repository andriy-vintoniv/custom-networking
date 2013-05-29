package com.epam.protocol.domain.message.server;

import com.epam.protocol.domain.message.Message;
import com.epam.protocol.domain.message.constants.ServerMessageType;

public class AnotherPointMoveMessage implements Message {
	private static final byte id = ServerMessageType.SM_ANOTHER_POINT_MOVE;
	private int pointId;
	private int x;
	private int y;

	public AnotherPointMoveMessage() {
	}

	public AnotherPointMoveMessage(int pointId, int x, int y) {
		this.pointId = pointId;
		this.x = x;
		this.y = y;
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
