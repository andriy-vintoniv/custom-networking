package com.epam.protocol.serializer;

import java.io.DataOutputStream;
import java.io.IOException;

import com.epam.protocol.domain.message.client.ChatClientMessage;
import com.epam.protocol.domain.message.client.LoginClientMessage;
import com.epam.protocol.domain.message.client.MoveClientMessage;

public class ClentMessageSerializer {

	private final short SIZE_OF_INT = 4;

	private DataOutputStream outputStream;

	public ClentMessageSerializer() {
	}

	public ClentMessageSerializer(DataOutputStream outputStream) {
		this.outputStream = outputStream;
	}

	public void serializeMessage(LoginClientMessage loginClientMessage) {
		try {
			int messageLength = loginClientMessage.getLogin().getBytes().length;
			outputStream.writeShort(messageLength);
			outputStream.writeByte(loginClientMessage.getId());
			outputStream.writeUTF(loginClientMessage.getLogin());
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				outputStream.flush();
			} catch (IOException e) {
			}
		}
	}

	public void serializeMessage(MoveClientMessage moveClientMessage) {
		short messageSize = SIZE_OF_INT * 2;
		try {
			outputStream.writeShort(messageSize);
			outputStream.writeByte(moveClientMessage.getId());
			outputStream.writeInt(moveClientMessage.getX());
			outputStream.writeInt(moveClientMessage.getY());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void serializeMessage(ChatClientMessage chatClientMessage) {
		try {
			int messageSize = chatClientMessage.getMessage().getBytes().length;
			outputStream.writeShort(messageSize);
			outputStream.writeByte(chatClientMessage.getId());
			outputStream.writeUTF(chatClientMessage.getMessage());
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
