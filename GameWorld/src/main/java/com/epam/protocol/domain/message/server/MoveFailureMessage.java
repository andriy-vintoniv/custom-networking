package com.epam.protocol.domain.message.server;

import com.epam.protocol.domain.message.Message;

public class MoveFailureMessage implements Message {
	public static final int INVALID_POSITION = 1;
	public static final int BUSY_POSITION = 2;
	private static final byte ID = 7;
	private int reason;

	public MoveFailureMessage() {
	}

	public MoveFailureMessage(int reason) {
		this.reason = reason;
	}

	public byte getId() {
		return ID;
	}

	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}
}
