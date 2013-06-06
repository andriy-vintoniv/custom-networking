package com.epam.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;

import com.epam.game.domain.Point;
import com.epam.game.domain.World;
import com.epam.game.domain.WorldMap;
import com.epam.server.ServerNIO;

public class ClientNIO {

	public static void main(String[] args) {
		try {
			SocketChannel channel = SocketChannel.open();

			// we open this channel in non blocking mode
			channel.configureBlocking(false);
			channel.connect(new InetSocketAddress("localhost", ServerNIO.PORT));

			World gameWorld = World.getInstance();
			Point point = createPoint(gameWorld);
			// replace it to the place when point is logged in successfully
			gameWorld.addPoint(point);

			while (!channel.finishConnect()) {
				// System.out.println("still connecting");
			}
			Selector selector = Selector.open();
			SelectionKey selectionKey = channel.register(selector,
					SelectionKey.OP_WRITE, point);

			while (true) {
				// see if any message has been received
				ByteBuffer bufferA = ByteBuffer.allocate(20);
				int count = 0;
				String message = "";
				while ((count = channel.read(bufferA)) > 0) {
					// flip the buffer to start reading
					bufferA.flip();
					message += Charset.defaultCharset().decode(bufferA);

				}
				if (message.length() > 0) {
					System.out.println(message);
					// write some data into the channel
					CharBuffer buffer = CharBuffer.wrap("Hello Server");
					while (buffer.hasRemaining()) {
						channel.write(Charset.defaultCharset().encode(buffer));
					}
					message = "";
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
}
