package com.epam.protocol.domain.message.server;

import com.epam.protocol.domain.message.Message;
import com.epam.protocol.domain.message.constants.ServerMessageType;

public class LoginSuccessServerMessage implements Message {

	private static final byte id = ServerMessageType.SM_LOGIN_SUCCESS;
	private int clientId;
	private int x;
	private int y;
	private int color;

	public LoginSuccessServerMessage() {
	}

	public LoginSuccessServerMessage(int clientId, int x, int y, int color) {
		this.clientId = clientId;
		this.x = x;
		this.y = y;
		this.color = color;
	}

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
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

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public byte getType() {
		return id;
	}
}
