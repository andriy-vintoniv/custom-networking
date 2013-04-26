package com.epam.protocol.domain.message.server;

import com.epam.protocol.domain.message.Message;

public class MoveFailureMessage implements Message {
	private static final byte id = 7;
	private int reason;

	public MoveFailureMessage() {
	}

	public MoveFailureMessage(int reason) {
		this.reason = reason;
	}

	public byte getId() {
		return id;
	}

	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}
}
