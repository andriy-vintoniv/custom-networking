package com.epam.protocol.serializer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.epam.protocol.domain.message.Message;
import com.epam.protocol.domain.message.server.AnotherPointDeleteServerMessage;
import com.epam.protocol.domain.message.server.AnotherPointMoveServerMessage;
import com.epam.protocol.domain.message.server.AnotherPoitnInfoServerMessage;
import com.epam.protocol.domain.message.server.ChatServerMessage;
import com.epam.protocol.domain.message.server.LoginFailureServerMessage;
import com.epam.protocol.domain.message.server.LoginSuccessServerMessage;
import com.epam.protocol.domain.message.server.MoveFailureServerMessage;
import com.epam.protocol.domain.message.server.MoveSuccessServerMessage;

public class ServerMessageSerializer {

	private static final int BUFFER_SIZE = 512;
	private static final short SIZE_OF_INT = 4;
	private static final short SIZE_OF_BYTE = 1;

	private ByteBuffer byteBuffer;

	public ServerMessageSerializer() {
		byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
	}

	public byte[] serializeMessage(Message message) {
		byte[] bytes = null;

		if (message instanceof LoginSuccessServerMessage) {
			bytes = serializeLoginSuccessServerMessage((LoginSuccessServerMessage) message);
		} else if (message instanceof LoginFailureServerMessage) {
			bytes = serializeLoginFailureServerMessage((LoginFailureServerMessage) message);
		} else if (message instanceof ChatServerMessage) {
			bytes = serializeChatServerMessage((ChatServerMessage) message);
		} else if (message instanceof AnotherPoitnInfoServerMessage) {
			bytes = serializeAnotherPoitnInfoServerMessage((AnotherPoitnInfoServerMessage) message);
		} else if (message instanceof AnotherPointDeleteServerMessage) {
			bytes = serializeAnotherPointDeleteServerMessage((AnotherPointDeleteServerMessage) message);
		} else if (message instanceof AnotherPointMoveServerMessage) {
			bytes = serializeAnotherPointMoveServerMessage((AnotherPointMoveServerMessage) message);
		} else if (message instanceof MoveSuccessServerMessage) {
			bytes = serializeMoveSuccessServerMessage((MoveSuccessServerMessage) message);
		} else if (message instanceof MoveFailureServerMessage) {
			bytes = serializeMoveFailureServerMessage((MoveFailureServerMessage) message);
		}

		return bytes;
	}

	public byte[] serializeLoginSuccessServerMessage(
			LoginSuccessServerMessage message) {
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

	public byte[] serializeLoginFailureServerMessage(
			LoginFailureServerMessage message) {
		int messageSize = SIZE_OF_BYTE + SIZE_OF_BYTE;

		byteBuffer.putShort((short) messageSize);
		byteBuffer.put(message.getType());
		byteBuffer.put(message.getErrorCode());

		byte[] bytes = byteBuffer.array();
		byteBuffer.clear();

		return bytes;
	}

	public byte[] serializeChatServerMessage(ChatServerMessage message) {
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

	public byte[] serializeAnotherPoitnInfoServerMessage(
			AnotherPoitnInfoServerMessage message) {
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

	public byte[] serializeAnotherPointDeleteServerMessage(
			AnotherPointDeleteServerMessage message) {
		short messageSize = SIZE_OF_BYTE + SIZE_OF_INT;

		byteBuffer.putShort(messageSize);
		byteBuffer.put(message.getType());
		byteBuffer.putInt(message.getPointId());

		byte[] bytes = byteBuffer.array();
		byteBuffer.clear();

		return bytes;
	}

	public byte[] serializeAnotherPointMoveServerMessage(
			AnotherPointMoveServerMessage message) {
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

	public byte[] serializeMoveFailureServerMessage(
			MoveFailureServerMessage message) {
		short messageSize = SIZE_OF_BYTE + SIZE_OF_INT;

		byteBuffer.putShort(messageSize);
		byteBuffer.put(message.getType());
		byteBuffer.putInt(message.getReason());

		byte[] bytes = byteBuffer.array();
		byteBuffer.clear();

		return bytes;
	}

	public byte[] serializeMoveSuccessServerMessage(
			MoveSuccessServerMessage message) {
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
