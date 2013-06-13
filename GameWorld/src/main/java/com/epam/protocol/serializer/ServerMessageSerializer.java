package com.epam.protocol.serializer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.epam.protocol.domain.message.server.AnotherPointDeleteServerMessage;
import com.epam.protocol.domain.message.server.AnotherPointMoveServerMessage;
import com.epam.protocol.domain.message.server.AnotherPoitnInfoServerMessage;
import com.epam.protocol.domain.message.server.ChatServerMessage;
import com.epam.protocol.domain.message.server.LoginFailureServerMessage;
import com.epam.protocol.domain.message.server.LoginSuccessServerMessage;
import com.epam.protocol.domain.message.server.MoveFailureServerMessage;
import com.epam.protocol.domain.message.server.MoveSuccessServerMessage;

public class ServerMessageSerializer {

	private static final int BUFFER_SIZE = 256;
	private static final short SIZE_OF_INT = 4;
	private static final short SIZE_OF_BYTE = 1;

	private ByteBuffer byteBuffer;

	public ServerMessageSerializer() {
		byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
	}

	public byte[] serializeMessage(LoginSuccessServerMessage message) {
		int messageSize = SIZE_OF_INT * 4 + SIZE_OF_BYTE;
		byteBuffer.putShort((short) messageSize);
		byteBuffer.put(message.getType());
		byteBuffer.putInt(message.getClientId());
		byteBuffer.putInt(message.getX());
		byteBuffer.putInt(message.getY());
		byteBuffer.putInt(message.getColor());

		byte[] bytes = byteBuffer.array();
		byteBuffer.clear();

		return bytes;
	}

	public byte[] serializeMessage(LoginFailureServerMessage message) {
		int messageSize = SIZE_OF_BYTE + SIZE_OF_BYTE;

		byteBuffer.putShort((short) messageSize);
		byteBuffer.put(message.getType());
		byteBuffer.put(message.getErrorCode());

		byte[] bytes = byteBuffer.array();
		byteBuffer.clear();

		return bytes;
	}

	public byte[] serializeMessage(ChatServerMessage message) {
		int messageTextSize = message.getMessage().getBytes().length;
		short messageSize = (short) (SIZE_OF_BYTE + SIZE_OF_BYTE + SIZE_OF_INT + messageTextSize);

		byteBuffer.putShort(messageSize);
		byteBuffer.put(message.getType());
		byteBuffer.putInt(message.getPointId());
		byteBuffer.put(message.getMessage().getBytes());

		byte[] bytes = byteBuffer.array();
		byteBuffer.clear();

		return bytes;
	}

	public byte[] serializeMessage(AnotherPoitnInfoServerMessage message) {
		int pointNameSize = message.getName().getBytes().length;
		short messageSize = (short) (SIZE_OF_BYTE + SIZE_OF_INT * 4 + pointNameSize);

		byteBuffer.putShort(messageSize);
		byteBuffer.put(message.getType());
		byteBuffer.putInt(message.getPointId());
		byteBuffer.putInt(message.getX());
		byteBuffer.putInt(message.getY());
		byteBuffer.putInt(message.getColor());
		byteBuffer.put(message.getName().getBytes());

		byte[] bytes = byteBuffer.array();
		byteBuffer.clear();

		return bytes;
	}

	public byte[] serializeMessage(AnotherPointDeleteServerMessage message) {
		short messageSize = SIZE_OF_BYTE + SIZE_OF_INT;

		byteBuffer.putShort(messageSize);
		byteBuffer.put(message.getType());
		byteBuffer.putInt(message.getPointId());

		byte[] bytes = byteBuffer.array();
		byteBuffer.clear();

		return bytes;
	}

	public byte[] serializeMessage(AnotherPointMoveServerMessage message) {
		short messageSize = SIZE_OF_BYTE + SIZE_OF_INT * 3;

		byteBuffer.putShort(messageSize);
		byteBuffer.put(message.getType());
		byteBuffer.putInt(message.getPointId());
		byteBuffer.putInt(message.getX());
		byteBuffer.putInt(message.getY());

		byte[] bytes = byteBuffer.array();
		byteBuffer.clear();

		return bytes;
	}

	public byte[] serializeMessage(MoveFailureServerMessage message) {
		short messageSize = SIZE_OF_BYTE + SIZE_OF_INT;

		byteBuffer.putShort(messageSize);
		byteBuffer.put(message.getType());
		byteBuffer.putInt(message.getReason());

		byte[] bytes = byteBuffer.array();
		byteBuffer.clear();

		return bytes;
	}

	public byte[] serializeMessage(MoveSuccessServerMessage message) {
		short messageSize = SIZE_OF_BYTE + SIZE_OF_INT * 2;

		byteBuffer.putShort(messageSize);
		byteBuffer.put(message.getType());
		byteBuffer.putInt(message.getX());
		byteBuffer.putInt(message.getY());

		byte[] bytes = byteBuffer.array();
		byteBuffer.clear();

		return bytes;
	}
}
