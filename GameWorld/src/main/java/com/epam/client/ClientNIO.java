package com.epam.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.UUID;

import com.epam.protocol.domain.message.client.LoginClientMessage;
import com.epam.protocol.serializer.ClentMessageSerializer;
import com.epam.server.nio.ServerNIO;

public class ClientNIO {

	public static void main(String[] args) {
		ByteBuffer byteBuffer = ByteBuffer.allocate(512);

		try {
			SocketChannel channel = SocketChannel.open();

			channel.configureBlocking(false);
			channel.connect(new InetSocketAddress("localhost", ServerNIO.PORT));

			while (!channel.finishConnect()) {
				// System.out.println("still connecting");
			}

			LoginClientMessage loginClientMessage = new LoginClientMessage(UUID
					.randomUUID().toString());

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
				ByteBuffer buffer = ByteBuffer.allocate(20);
				String message = "";
				while ((channel.read(buffer)) > 0) {
					// flip the buffer to start reading
					buffer.flip();
					message += Charset.defaultCharset().decode(buffer);

				}
				if (message.length() > 0) {
					System.out.println(message);
					// write some data into the channel
					CharBuffer buffer2 = CharBuffer.wrap("Hello Server");
					while (buffer2.hasRemaining()) {
						channel.write(Charset.defaultCharset().encode(buffer2));
					}
					message = "";
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
