package com.epam.protocol.serializer;

import java.math.BigInteger;

import com.epam.protocol.domain.message.client.ChatClientMessage;
import com.epam.protocol.domain.message.client.LoginClientMessage;
import com.epam.protocol.domain.message.client.MoveClientMessage;

public class ClentMessageSerializer {

	private final short SIZE_OF_INT = 4;

	public ClentMessageSerializer() {
	}

	public byte[] serializeMessage(LoginClientMessage loginClientMessage) {
		byte[] result = new byte[0];

		int messageLength = loginClientMessage.getLogin().getBytes().length;
		byte[] messageLengthBytes = BigInteger.valueOf(messageLength)
				.toByteArray();
		byte[] messageIdBytes = BigInteger.valueOf(loginClientMessage.getId())
				.toByteArray();
		byte[] clientLoginBytes = loginClientMessage.getLogin().getBytes();

		result = concat(result, messageLengthBytes);
		result = concat(result, messageIdBytes);
		result = concat(result, clientLoginBytes);

		return result;
	}

	public byte[] serializeMessage(MoveClientMessage moveClientMessage) {
		byte[] result = new byte[0];

		short messageSize = SIZE_OF_INT * 2;
		byte[] messageSizeBytes = BigInteger.valueOf(messageSize).toByteArray();
		byte[] messageIdBytes = BigInteger.valueOf(moveClientMessage.getId())
				.toByteArray();

		byte[] xCoordinateBytes = BigInteger.valueOf(moveClientMessage.getX())
				.toByteArray();
		byte[] yCoordinateBytes = BigInteger.valueOf(moveClientMessage.getY())
				.toByteArray();

		result = concat(result, messageSizeBytes);
		result = concat(result, messageIdBytes);
		result = concat(result, xCoordinateBytes);
		result = concat(result, yCoordinateBytes);

		return result;
	}

	public byte[] serializeMessage(ChatClientMessage chatClientMessage) {
		byte[] result = new byte[0];

		int messageSize = chatClientMessage.getMessage().getBytes().length;

		byte[] messageSizeBytes = BigInteger.valueOf(messageSize).toByteArray();
		byte[] messageIdBytes = BigInteger.valueOf(chatClientMessage.getId())
				.toByteArray();
		byte[] messageTextBytes = chatClientMessage.getMessage().getBytes();

		result = concat(result, messageSizeBytes);
		result = concat(result, messageIdBytes);
		result = concat(result, messageTextBytes);

		return result;
	}

	private byte[] concat(byte[] first, byte[] second) {
		int aLen = first.length;
		int bLen = second.length;
		byte[] C = new byte[aLen + bLen];
		System.arraycopy(first, 0, C, 0, aLen);
		System.arraycopy(second, 0, C, aLen, bLen);
		return C;
	}
}
