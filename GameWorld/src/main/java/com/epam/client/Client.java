package com.epam.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import com.epam.protocol.domain.message.client.LoginClientMessage;
import com.epam.protocol.serializer.ClentMessageSerializer;
import com.epam.server.Server;

public class Client {

	public static void main(String[] args) throws IOException {

		ClentMessageSerializer clentMessageTransformer = new ClentMessageSerializer();

		Socket socket = null;
		BufferedReader br = null;
		DataOutputStream dos = null;

		try {
			socket = new Socket(InetAddress.getLocalHost(), Server.PORT);
			br = new BufferedReader(new InputStreamReader(System.in));
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			System.err.println("Unknow host: " + Server.PORT);
			System.exit(0);
		}
		clentMessageTransformer.setOutputStream(dos);
		clentMessageTransformer.serializeMessage(new LoginClientMessage(
				"test login message."));

		br.close();
		dos.close();
		socket.close();

	}
}
