package com.epam.protocol.serializer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.epam.protocol.domain.message.server.AnotherPointDeleteMessage;
import com.epam.protocol.domain.message.server.AnotherPointMoveMessage;
import com.epam.protocol.domain.message.server.AnotherPoitnInfoMessage;
import com.epam.protocol.domain.message.server.ChatServerMessage;
import com.epam.protocol.domain.message.server.LoginFailureServerMessage;
import com.epam.protocol.domain.message.server.LoginSuccessServerMessage;
import com.epam.protocol.domain.message.server.MoveFailureMessage;
import com.epam.protocol.domain.message.server.MoveSuccessMessage;

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

	public byte[] serializeMessage(AnotherPoitnInfoMessage message) {
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

	public byte[] serializeMessage(AnotherPointDeleteMessage message) {
		short messageSize = SIZE_OF_BYTE + SIZE_OF_INT;

		byteBuffer.putShort(messageSize);
		byteBuffer.put(message.getType());
		byteBuffer.putInt(message.getPointId());

		byte[] bytes = byteBuffer.array();
		byteBuffer.clear();

		return bytes;
	}

	public byte[] serializeMessage(AnotherPointMoveMessage message) {
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

	public byte[] serializeMessage(MoveFailureMessage message) {
		short messageSize = SIZE_OF_BYTE + SIZE_OF_INT;

		byteBuffer.putShort(messageSize);
		byteBuffer.put(message.getType());
		byteBuffer.putInt(message.getReason());

		byte[] bytes = byteBuffer.array();
		byteBuffer.clear();

		return bytes;
	}

	public byte[] serializeMessage(MoveSuccessMessage message) {
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
