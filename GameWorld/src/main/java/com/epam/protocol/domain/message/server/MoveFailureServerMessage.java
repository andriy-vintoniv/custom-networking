package com.epam.protocol.domain.message.server;

import com.epam.protocol.domain.message.Message;
import com.epam.protocol.domain.message.constants.ServerMessageType;

public class MoveFailureServerMessage implements Message {
	private static final byte ID = ServerMessageType.SM_MOVE_FAILURE;
	private int reason;

	public MoveFailureServerMessage() {
	}

	public MoveFailureServerMessage(int reason) {
		this.reason = reason;
	}

	public byte getType() {
		return ID;
	}

	public int getReason() {
		return reason;
	}

	public void setReason(int reason) {
		this.reason = reason;
	}
}
