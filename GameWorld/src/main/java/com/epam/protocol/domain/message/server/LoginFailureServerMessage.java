package com.epam.protocol.domain.message.server;

import com.epam.protocol.domain.message.Message;

public class LoginFailureServerMessage implements Message {

	private static final byte id = 2;
	private byte errorCode;

	public LoginFailureServerMessage() {
	}

	public LoginFailureServerMessage(byte errorCode) {
		this.errorCode = errorCode;
	}

	public byte getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(byte errorCode) {
		this.errorCode = errorCode;
	}

	public byte getId() {
		return id;
	}
}
