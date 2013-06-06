package com.epam.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.epam.game.receiver.client.ClientChatMessageReceiver;
import com.epam.game.sender.impl.server.ServerChatMessageSender;
import com.epam.protocol.builder.impl.ClientChatMessageBuilder;
import com.epam.protocol.domain.message.client.ChatClientMessage;
import com.epam.protocol.domain.message.server.ChatServerMessage;
import com.epam.protocol.serializer.ServerMessageSerializer;

public class Server {
	public static final int PORT = 95;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			System.err.println("Cannot find port " + PORT);
		}

		Socket socket = null;
		try {
			socket = serverSocket.accept();
			System.out.println("Connection accepted at :" + socket);
		} catch (IOException e) {
			System.out.println("Server failed to accept");
			System.exit(1);
		}

		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		ClientChatMessageReceiver clientChatMessageReceiver = new ClientChatMessageReceiver(
				new ClientChatMessageBuilder(), dis);
		ServerChatMessageSender serverChatMessageSender = new ServerChatMessageSender(
				new ServerMessageSerializer(), dos);

		startChat(clientChatMessageReceiver, serverChatMessageSender);

		dis.close();
		socket.close();
	}

	private static void startChat(ClientChatMessageReceiver receiver,
			ServerChatMessageSender sender) {
		ChatClientMessage chatClientMessage = receiver.receive();
		// ChatServerMessage chatServerMessage = new ChatServerMessage(
		// chatClientMessage.getMessage(), pointId);// where pointId
		// retrieve from
		// sender.send(chatServerMessage);
	}
}
