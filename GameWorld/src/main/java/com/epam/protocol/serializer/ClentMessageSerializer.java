package com.epam.protocol.serializer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.epam.protocol.domain.message.Message;
import com.epam.protocol.domain.message.client.ChatClientMessage;
import com.epam.protocol.domain.message.client.LoginClientMessage;
import com.epam.protocol.domain.message.client.MoveClientMessage;

public class ClentMessageSerializer {

	private static final int BUFFER_SIZE = 256;
	private static final short SIZE_OF_INT = 4;
	private static final short SIZE_OF_BYTE = 1;

	private ByteBuffer byteBuffer;

	public ClentMessageSerializer() {
		byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
	}

	public byte[] serializeMessage(Message message) {
		byte[] bytes = null;
		if (message instanceof ChatClientMessage) {
			bytes = serializeChatClientMessage((ChatClientMessage) message);
		} else if (message instanceof LoginClientMessage) {
			bytes = serializeLoginClientMessage((LoginClientMessage) message);
		} else if (message instanceof MoveClientMessage) {
			bytes = serializeMoveClientMessage((MoveClientMessage) message);
		}

		return bytes;
	}

	public byte[] serializeLoginClientMessage(
			LoginClientMessage loginClientMessage) {
		int messageLength = loginClientMessage.getLogin().getBytes().length
				+ SIZE_OF_BYTE;

		byteBuffer.putShort((short) messageLength);
		byteBuffer.put(loginClientMessage.getType());
		byteBuffer.put(loginClientMessage.getLogin().getBytes());

		byte[] bytes = byteBuffer.array();
		byteBuffer.clear();
		return bytes;
	}

	public byte[] serializeMoveClientMessage(MoveClientMessage moveClientMessage) {
		short messageSize = SIZE_OF_INT * 2 + SIZE_OF_BYTE;
		byteBuffer.putShort(messageSize);
		byteBuffer.put(moveClientMessage.getType());
		byteBuffer.putInt(moveClientMessage.getX());
		byteBuffer.putInt(moveClientMessage.getY());

		byte[] bytes = byteBuffer.array();
		byteBuffer.clear();
		return bytes;
	}

	public byte[] serializeChatClientMessage(ChatClientMessage chatClientMessage) {
		int messageSize = chatClientMessage.getMessage().getBytes().length
				+ SIZE_OF_BYTE;
		byteBuffer.putShort((short) messageSize);
		byteBuffer.put(chatClientMessage.getType());
		byteBuffer.put(chatClientMessage.getMessage().getBytes());

		byte[] bytes = byteBuffer.array();
		byteBuffer.clear();
		return bytes;
	}
}
