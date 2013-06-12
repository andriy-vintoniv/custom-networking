package com.epam.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;

import com.epam.game.domain.Point;
import com.epam.protocol.builder.MessageBuilder;
import com.epam.protocol.builder.ServerMessageBuilderFactory;
import com.epam.protocol.domain.message.client.LoginClientMessage;
import com.epam.protocol.domain.message.constants.ServerMessageType;
import com.epam.protocol.domain.message.server.LoginSuccessServerMessage;
import com.epam.protocol.serializer.ClentMessageSerializer;
import com.epam.server.nio.ServerNIO;

public class ClientNIO {

	public static void main(String[] args) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(512);
		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
		String clientName = args[0];

		try {
			SocketChannel channel = SocketChannel.open();

			channel.configureBlocking(false);
			channel.connect(new InetSocketAddress("localhost", ServerNIO.PORT));

			while (!channel.finishConnect()) {
				// System.out.println("still connecting");
			}

			LoginClientMessage loginClientMessage = new LoginClientMessage(
					clientName);

			// get login message bytes array
			ClentMessageSerializer clentMessageSerializer = new ClentMessageSerializer();
			byte[] serializedLoginMessage = clentMessageSerializer
					.serializeMessage(loginClientMessage);
			byteBuffer.clear();
			byteBuffer.put(serializedLoginMessage);
			byteBuffer.flip();
			// write message to channel
			while (byteBuffer.hasRemaining()) {
				channel.write(byteBuffer);
			}
			byteBuffer.clear();

			while (true) {
				// see if any message has been received
				int count = 0;
				int readBytes = 0;
				while ((count = channel.read(byteBuffer)) > 0) {
					readBytes += count;
				}
				if (readBytes > 0) {
					byteBuffer.flip();
					short messageSize = byteBuffer.getShort();
					byte messageType = byteBuffer.get();
					if (messageType == ServerMessageType.SM_LOGIN_SUCCESS) {
						MessageBuilder<?> loginSuccessfulMessageBuilder = ServerMessageBuilderFactory
								.getMessageBuilder(messageType);

						LoginSuccessServerMessage loginSuccessServerMessage = (LoginSuccessServerMessage) loginSuccessfulMessageBuilder
								.buildMessage(byteBuffer);
						Point point = new Point();
						point.setName(clientName);
						point.setColor(loginSuccessServerMessage.getColor());
						point.setId(loginSuccessServerMessage.getClientId());
						point.setX(loginSuccessServerMessage.getX());
						point.setY(loginSuccessServerMessage.getY());

						System.out.println("Loged in successfully");
						System.out.println("point(" + point.getX() + ","
								+ point.getY() + ")");
						byteBuffer.clear();
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
