package com.epam.game.sender.impl.server;

import java.io.DataOutputStream;
import java.util.Collection;

import com.epam.game.domain.Point;
import com.epam.game.domain.World;
import com.epam.game.sender.ServerMessageSender;
import com.epam.game.util.Filter;
import com.epam.protocol.domain.message.server.ChatServerMessage;
import com.epam.protocol.serializer.ServerMessageSerializer;

public class ServerChatMessageSender implements
		ServerMessageSender<ChatServerMessage> {

	private ServerMessageSerializer serverMessageSerializer;
	private DataOutputStream dos;
	private World gameWorld = World.getInstance();

	public ServerChatMessageSender(
			ServerMessageSerializer serverMessageSerializer,
			DataOutputStream dos) {
		this.setServerMessageSerializer(serverMessageSerializer);
		this.dos = dos;
	}

	@Override
	public void send(ChatServerMessage message) {

	}

	public void iterate(Filter filter, ChatServerMessage message) {

		Point senderPoint = gameWorld.getPoints().get(
				Integer.valueOf(message.getPointId()));
		// Visitor visitor = new ConcreteVisitor(Message);
		Collection<Point> points = gameWorld.getPoints().values();
		for (Point receiverPoint : points) {
			if (filter.accept(senderPoint, receiverPoint)) {
				// TODO: implementation of broadcasting chat message
			}
		}
	}

	public DataOutputStream getDos() {
		return dos;
	}

	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}

	public ServerMessageSerializer getServerMessageSerializer() {
		return serverMessageSerializer;
	}

	public void setServerMessageSerializer(
			ServerMessageSerializer serverMessageSerializer) {
		this.serverMessageSerializer = serverMessageSerializer;
	}

}
