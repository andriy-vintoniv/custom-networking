package com.epam.server.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import com.epam.protocol.domain.message.constants.ClientMessageType;
import com.epam.protocol.handler.impl.client.ClientMessageHandler;
import com.epam.server.nio.sender.NIOMessageSender;

public class ServerNIO {
	private static final int POINT_ID_POSITION = 3;
	private static final int BUFFER_SIZE = 256;
	public static final int PORT = 95;
	private final static String SERVER_CHANNEL = "serverChannel";

	private static ChannelContainer channelContainer = ChannelContainer
			.getInstance();
	private static NIOMessageSender messageSender = new NIOMessageSender();

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
							clientSocketChannel
									.register(selector, SelectionKey.OP_READ,
											SelectionKey.OP_WRITE);
						}
					} else {
						loginClient(key);
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
	private static void loginClient(SelectionKey clientKey) {
		ClientMessageHandler clientMessageHandler = new ClientMessageHandler();
		SocketChannel clientChannel = (SocketChannel) clientKey.channel();

		ByteBuffer byteBuffer = ByteBuffer.allocate(BUFFER_SIZE);
		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

		if (clientKey.isReadable()) {
			try {
				int count = 0;
				int readBytes = 0;
				while ((count = clientChannel.read(byteBuffer)) > 0) {
					readBytes = +count;
				}
				if (readBytes > 0) {
					byteBuffer.flip();
					byteBuffer.getShort(); // read message size
					byte messageType = byteBuffer.get();

					NIOConnection connection = new NIOConnection(clientChannel);

					ByteBuffer handledByteBuffer = clientMessageHandler.handle(
							byteBuffer, messageType, channelContainer,
							messageSender, connection);
					if (messageType == ClientMessageType.CM_LOGIN) {
						// get point identifier to map channel with point
						int pointId = handledByteBuffer
								.getInt(POINT_ID_POSITION);
						handledByteBuffer.rewind();
						channelContainer.addConnection(pointId, clientChannel);
					}

					handledByteBuffer.clear();

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
