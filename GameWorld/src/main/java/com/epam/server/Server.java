package com.epam.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.epam.protocol.builder.MessageBuilder;
import com.epam.protocol.builder.impl.LoginClientMessageBuilder;
import com.epam.protocol.domain.message.client.LoginClientMessage;

public class Server {
	public static final int PORT = 95;

	public static void main(String[] args) throws IOException {
		MessageBuilder messageBuilder = new LoginClientMessageBuilder();

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

		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		DataInputStream dis = new DataInputStream(socket.getInputStream());

		while (true) {

			LoginClientMessage loginMessage = (LoginClientMessage) messageBuilder
					.buildMessage(dis);
			System.out.println("Message reseived");
			System.out.println("login " + loginMessage.getLogin());
		}

		// dos.close();
		// socket.close();
	}
}
