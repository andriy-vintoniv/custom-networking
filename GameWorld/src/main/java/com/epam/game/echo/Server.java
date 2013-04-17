package com.epam.game.echo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

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

		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(
				socket.getInputStream()));
		String string;
		System.out.println("Server waiting for message from tthe client");
		boolean quit = false;

		do {
			String message = "";
			string = br.readLine();

			int len = string.length();

			if (string.equals("exit")) {
				quit = true;
			}

			for (int i = 0; i < len; i++) {
				message = message + string.charAt(i);
				dos.write((byte) string.charAt(i));
			}

			System.out.println("From client: " + message);
			dos.write(13);
			dos.write(10);
			dos.flush();
		} while (!quit);

		dos.close();
		socket.close();
	}
}
