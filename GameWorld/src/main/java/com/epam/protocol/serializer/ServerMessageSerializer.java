package com.epam.protocol.serializer;

import java.io.DataOutputStream;
import java.io.IOException;

import com.epam.protocol.domain.message.server.LoginFailureServerMessage;
import com.epam.protocol.domain.message.server.LoginSuccessServerMessage;

public class ServerMessageSerializer {

	private final short SIZE_OF_INT = 4;
	private final short SIZE_OF_BYTE = 1;
	private DataOutputStream outputStream;

	public ServerMessageSerializer() {
	}

	public ServerMessageSerializer(DataOutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public void serializeMessage(LoginSuccessServerMessage message) {
		try {
			int messageSize = SIZE_OF_INT * 4;
			outputStream.writeShort(messageSize);
			outputStream.writeByte(message.getId());
			outputStream.writeInt(message.getClientId());
			outputStream.writeInt(message.getX());
			outputStream.writeInt(message.getY());
			outputStream.writeInt(message.getColor());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void serializeMessage(LoginFailureServerMessage message) {
		try {
			int messageSize = SIZE_OF_BYTE;
			outputStream.writeShort(messageSize);
			outputStream.writeByte(message.getId());
			outputStream.writeByte(message.getErrorCode());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DataOutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(DataOutputStream outputStream) {
		this.outputStream = outputStream;
	}
}
