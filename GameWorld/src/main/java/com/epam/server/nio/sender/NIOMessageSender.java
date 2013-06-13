package com.epam.server.nio.sender;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;
import java.util.Collection;

import com.epam.game.domain.Point;
import com.epam.game.domain.World;
import com.epam.protocol.domain.message.Message;
import com.epam.protocol.serializer.ServerMessageSerializer;
import com.epam.server.Connection;
import com.epam.server.ConnectionContainer;
import com.epam.server.MessageSender;
import com.epam.server.nio.NIOConnection;
import com.epam.server.util.Filter;

public class NIOMessageSender implements MessageSender<SocketChannel> {

	private static final int BUFFER_SIZE = 512;
	private World world = World.getInstance();
	private ServerMessageSerializer serverMessageSerializer = new ServerMessageSerializer();

	@Override
	public void send(int pointId, Message message, Connection<?> connection) {

		SocketChannel socketChannel = (SocketChannel) connection
				.getConnection();
		ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

		byte[] serializedLoginSuccessfulMessage = serverMessageSerializer
				.serializeMessage(message);
		byteBuffer.clear();
		byteBuffer.put(serializedLoginSuccessfulMessage);
		byteBuffer.flip();

		while (byteBuffer.hasRemaining()) {
			try {
				socketChannel.write(byteBuffer);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		byteBuffer.clear();
	}

	@Override
	public void send(int pointId, Message message,
			ConnectionContainer<?> channelContainer) {
		Point senderPoint = world.getPoints().get(pointId);
		Collection<Point> points = world.getPoints().values();
		Filter filter = new Filter();

		for (Point receiverPoint : points) {
			if (filter.accept(senderPoint, receiverPoint)) {
				SocketChannel socketChannel = (SocketChannel) channelContainer
						.getConnection(receiverPoint.getId());
				send(pointId, message, new NIOConnection(socketChannel));
			}
		}
	}
}
