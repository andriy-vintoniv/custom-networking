package com.epam.protocol.handler.impl.server;

import java.nio.ByteBuffer;

import com.epam.game.domain.Point;
import com.epam.protocol.builder.impl.server.LoginSuccessMessageBuilder;
import com.epam.protocol.domain.message.server.LoginSuccessServerMessage;
import com.epam.protocol.handler.impl.SingleMessageHandler;

class LoginSuccessServerMessageHandler implements SingleMessageHandler {

	@Override
	public ByteBuffer handle(ByteBuffer byteBuffer) {
		LoginSuccessMessageBuilder loginSuccessMessageBuilder = new LoginSuccessMessageBuilder();

		LoginSuccessServerMessage loginSuccessServerMessage = loginSuccessMessageBuilder
				.buildMessage(byteBuffer);
		Point point = new Point();
		// TODO: set name
		// point.setName(clientName);
		point.setColor(loginSuccessServerMessage.getColor());
		point.setId(loginSuccessServerMessage.getClientId());
		point.setX(loginSuccessServerMessage.getX());
		point.setY(loginSuccessServerMessage.getY());

		System.out.println("Loged in successfully");
		System.out.println(point.getName());
		System.out.println("point(" + point.getX() + "," + point.getY() + ")");
		byteBuffer.clear();

		return null;
	}

}
