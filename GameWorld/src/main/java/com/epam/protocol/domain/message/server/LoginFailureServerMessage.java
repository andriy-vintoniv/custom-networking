package com.epam.protocol.domain.message.server;

import com.epam.protocol.domain.message.Message;
import com.epam.protocol.domain.message.constants.ServerMessageType;

public class LoginFailureServerMessage implements Message {

	private static final byte id = ServerMessageType.SM_LOGIN_FAILURE;
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

	public byte getType() {
		return id;
	}
}
