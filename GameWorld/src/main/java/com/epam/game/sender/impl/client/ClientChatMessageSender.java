package com.epam.game.sender.impl.client;

import java.io.DataOutputStream;
import java.io.IOException;

import com.epam.game.sender.ClientMessageSender;
import com.epam.protocol.domain.message.client.ChatClientMessage;
import com.epam.protocol.serializer.ClentMessageSerializer;

public class ClientChatMessageSender implements
		ClientMessageSender<ChatClientMessage> {

	private ClentMessageSerializer clentMessageSerializer;
	private DataOutputStream dos;

	public ClientChatMessageSender(
			ClentMessageSerializer clentMessageSerializer, DataOutputStream dos) {
		this.setClentMessageSerializer(clentMessageSerializer);
		this.dos = dos;
	}

	@Override
	public void send(ChatClientMessage message) {
		byte[] clientChatMessageBytes = this.clentMessageSerializer
				.serializeMessage(message);
		try {
			dos.write(clientChatMessageBytes);
			dos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ClentMessageSerializer getClentMessageSerializer() {
		return clentMessageSerializer;
	}

	public void setClentMessageSerializer(
			ClentMessageSerializer clentMessageSerializer) {
		this.clentMessageSerializer = clentMessageSerializer;
	}

	public DataOutputStream getDos() {
		return dos;
	}

	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}

}
