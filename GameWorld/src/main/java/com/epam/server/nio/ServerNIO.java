package com.epam.server.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import com.epam.game.domain.Point;
import com.epam.game.domain.World;
import com.epam.game.domain.WorldMap;
import com.epam.protocol.domain.message.constants.ClientMessageType;
import com.epam.protocol.domain.message.server.LoginFailureServerMessage;
import com.epam.protocol.domain.message.server.LoginSuccessServerMessage;
import com.epam.protocol.serializer.ServerMessageSerializer;

public class ServerNIO {
	private static final int BUFFER_SIZE = 256;
	public static final int PORT = 95;
	private static String CLIENT_CHANNEL = "clientChannel";
	private final static String SERVER_CHANNEL = "serverChannel";

	private static World world = World.getInstance();
	private static ChannelContainer channelContainer = ChannelContainer
			.getInstance();

	public static void main(String[] args) {

		String localhost = "localhost";

		ServerSocketChannel channel;
		try {
			channel = ServerSocketChannel.open();
			channel.socket().bind(new InetSocketAddress(localhost, PORT));
			channel.configureBlocking(false);

			Selector selector = Selector.open();

			SelectionKey socketServerSelectionKey = channel.register(selector,
					SelectionKey.OP_ACCEPT);
			socketServerSelectionKey.attach(SERVER_CHANNEL);

			// wait for the selected keys
			for (;;) {

				if (selector.select() == 0) {
					continue;
				}
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectedKeys.iterator();
				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					if (key.attachment().equals(SERVER_CHANNEL)) {
						ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key
								.channel();
						SocketChannel clientSocketChannel = serverSocketChannel
								.accept();

						if (clientSocketChannel != null) {
							clientSocketChannel.configureBlocking(false);
							SelectionKey clientKey = clientSocketChannel
									.register(selector, SelectionKey.OP_READ,
											SelectionKey.OP_WRITE);
							clientKey.attach(CLIENT_CHANNEL);
						}
					} else {
						Point point = loginClient(key);

						SocketChannel clientChannel = (SocketChannel) key
								.channel();
						if (point != null) {
							world.addPoint(point);
							channelContainer.addChannel(clientChannel,
									point.getName());
						}
					}
					iterator.remove();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tries to login client into server. Generate new point and send Login
	 * message to client.
	 * 
	 * @param clientKey
	 * @return generated point - if logged in successfully, else - null.
	 */
	private static Point loginClient(SelectionKey clientKey) {
		Point point = null;
		ServerMessageSerializer serverMessageSerializer = new ServerMessageSerializer();
		SocketChannel clientChannel = (SocketChannel) clientKey.channel();
		ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
		Charset charset = Charset.forName("UTF-8");
		CharsetDecoder decoder = charset.newDecoder();

		if (clientKey.isReadable()) {
			try {
				int count = 0;
				int readBytes = 0;
				while ((count = clientChannel.read(byteBuffer)) > 0) {
					readBytes = +count;
				}
				if (readBytes > 0) {
					byteBuffer.flip();
					short messageSize = byteBuffer.getShort();
					byte messageType = byteBuffer.get();

					if (ClientMessageType.CM_LOGIN == messageType) {
						String clientName = decoder.decode(byteBuffer)
								.toString();

						point = generateNewPoint(clientName);

						if (point != null) {
							LoginSuccessServerMessage loginSuccessServerMessage = new LoginSuccessServerMessage(
									point.getId(), point.getX(), point.getY(),
									point.getColor());

							byte[] serializedLoginSuccessfulMessage = serverMessageSerializer
									.serializeMessage(loginSuccessServerMessage);
							byteBuffer.clear();
							// byteBuffer.flip();
							byteBuffer.put(serializedLoginSuccessfulMessage);
							byteBuffer.flip();
							while (byteBuffer.hasRemaining()) {
								clientChannel.write(byteBuffer);
							}
							byteBuffer.clear();
						} else {
							LoginFailureServerMessage loginFailureServerMessage = new LoginFailureServerMessage(
									(byte) 1);
							byte[] serializedFailureLogonMessage = serverMessageSerializer
									.serializeMessage(loginFailureServerMessage);
							byteBuffer.put(serializedFailureLogonMessage);
							byteBuffer.flip();
							while (byteBuffer.hasRemaining()) {
								clientChannel.write(byteBuffer);
							}
							byteBuffer.clear();
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return point;
	}

	/**
	 * Generate new point with random coordinates and unique id.
	 * 
	 * @param name
	 * @return generated point.
	 */
	private static Point generateNewPoint(String name) {
		Point point = new Point();
		Random rand = new Random();
		point.setX(rand.nextInt(WorldMap.MAP_SIZE));
		point.setY(rand.nextInt(WorldMap.MAP_SIZE));
		point.setName(name);
		int pointId = rand.nextInt();
		while (world.getPoints().containsKey(Integer.valueOf(pointId))) {
			pointId = rand.nextInt();
		}
		point.setId(pointId);
		point.setColor(rand.nextInt(1000));
		return point;
	}
}
