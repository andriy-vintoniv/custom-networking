package com.epam.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {

	public static void main(String[] args) throws IOException {
		Socket socket = null;
		String str = null;
		BufferedReader br = null;
		DataOutputStream dos = null;
		BufferedReader kyrd = new BufferedReader(new InputStreamReader(
				System.in));

		try {
			socket = new Socket(InetAddress.getLocalHost(), 1234);
			br = new BufferedReader(new InputStreamReader(System.in));
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (UnknownHostException e) {
			System.err.println("Unknow host: " + 1234);
			System.exit(0);
		}

		System.out
				.println("To start the dialog type the message in this client window \n Type exit to end");
		boolean more = true;
		while (more) {
			str = kyrd.readLine();
			dos.writeBytes(str);
			dos.write(13);
			dos.write(10);
			dos.flush();
			String s;
			s = br.readLine();
			System.out.println("From server: " + s);
			if (s.equals("exit")) {
				break;
			}
		}

		br.close();
		dos.close();
		socket.close();

	}
}
