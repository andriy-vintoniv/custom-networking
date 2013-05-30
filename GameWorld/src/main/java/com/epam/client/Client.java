package com.epam.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

import com.epam.game.domain.Point;
import com.epam.game.domain.World;
import com.epam.game.domain.WorldMap;
import com.epam.game.receiver.server.ServerChatMessageReceiver;
import com.epam.game.sender.impl.client.ClientChatMessageSender;
import com.epam.protocol.domain.message.client.ChatClientMessage;
import com.epam.protocol.serializer.ClentMessageSerializer;
import com.epam.server.Server;

public class Client {

	public static void main(String[] args) throws IOException {

		Socket socket = null;
		DataOutputStream dos = null;
		DataInputStream dis = null;

		World gameWorld = World.getInstance();
		Point point = createPoint(gameWorld);
		gameWorld.addPoint(point);

		try {
			socket = new Socket(InetAddress.getLocalHost(), Server.PORT);
			dos = new DataOutputStream(socket.getOutputStream());
			dis = new DataInputStream(socket.getInputStream());

			ClientChatMessageSender clientChatMessageSender = new ClientChatMessageSender(
					new ClentMessageSerializer(), dos);
			ServerChatMessageReceiver serverChatMessageReceiver = new ServerChatMessageReceiver(
					dis);

			startChat(clientChatMessageSender, serverChatMessageReceiver);
		} catch (UnknownHostException e) {
			System.err.println("Unknow host: " + Server.PORT);
			System.exit(0);
		}
		dos.close();
		socket.close();

	}

	private static Point createPoint(World gameWorld) {
		Point point = new Point();
		Random rand = new Random();
		point.setX(rand.nextInt(WorldMap.MAP_SIZE));
		point.setY(rand.nextInt(WorldMap.MAP_SIZE));
		point.setName(UUID.randomUUID().toString());
		int pointId = rand.nextInt();
		while (gameWorld.getPoints().containsKey(Integer.valueOf(pointId))) {
			pointId = rand.nextInt();
		}
		point.setId(pointId);
		return point;
	}

	private static void startChat(ClientChatMessageSender sender,
			ServerChatMessageReceiver receiver) {
		Scanner in = new Scanner(System.in);
		String messageText = in.next();
		while (!messageText.equals("quit")) {
			ChatClientMessage chatMessage = new ChatClientMessage(messageText);
			sender.send(chatMessage);
			receiver.receive();
			messageText = in.next();
		}
	}
}
