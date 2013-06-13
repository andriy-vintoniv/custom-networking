package com.epam.protocol.handler.impl.client;

import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Random;

import com.epam.game.domain.Point;
import com.epam.game.domain.World;
import com.epam.game.domain.WorldMap;
import com.epam.protocol.domain.message.server.AnotherPoitnInfoServerMessage;
import com.epam.protocol.domain.message.server.LoginFailureServerMessage;
import com.epam.protocol.domain.message.server.LoginSuccessServerMessage;
import com.epam.protocol.handler.impl.SingleMessageHandler;
import com.epam.server.Connection;
import com.epam.server.ConnectionContainer;
import com.epam.server.MessageSender;

class LoginClientMessageHandler implements SingleMessageHandler {

	private World world = World.getInstance();

	@Override
	public ByteBuffer handle(ByteBuffer byteBuffer,
			ConnectionContainer<?> connectionContainer,
			MessageSender<?> messageSender, Connection<?> connection) {
		Point point = null;
		Charset charset = Charset.forName("UTF-8");
		CharsetDecoder decoder = charset.newDecoder();

		String clientName;
		try {
			clientName = decoder.decode(byteBuffer).toString();

			point = generateNewPoint(clientName);

			if (point != null) {
				world.addPoint(point);
				int pointId = point.getId();
				LoginSuccessServerMessage loginSuccessServerMessage = new LoginSuccessServerMessage(
						pointId, point.getX(), point.getY(), point.getColor());
				// Object connection =
				// connectionContainer.getConnection(pointId);
				messageSender.send(pointId, loginSuccessServerMessage,
						connection);
				connectionContainer.addConnection(pointId, connection);
			} else {
				LoginFailureServerMessage loginFailureServerMessage = new LoginFailureServerMessage(
						(byte) 1);
				// TODO: send login failure message. problem is where to get
				// connection to send.

				// byte[] serializedFailureLoginMessage =
				// serverMessageSerializer
				// .serializeMessage(loginFailureServerMessage);
				// byteBuffer.put(serializedFailureLoginMessage);
				// byteBuffer.flip();
			}
		} catch (CharacterCodingException e) {
			e.printStackTrace();
		}

		// TODO: send to all visible points another point info message

		AnotherPoitnInfoServerMessage anotherPoitnInfoServerMessage = new AnotherPoitnInfoServerMessage(
				point.getId(), point.getX(), point.getY(), point.getColor(),
				point.getName());

		messageSender.send(point.getId(), anotherPoitnInfoServerMessage,
				connectionContainer);

		return byteBuffer;
	}

	/**
	 * Generate new point with random coordinates and unique id.
	 * 
	 * @param name
	 * @return generated point.
	 */
	private Point generateNewPoint(String name) {
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
