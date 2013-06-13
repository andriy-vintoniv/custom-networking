package com.epam.protocol.handler.impl;

import java.nio.ByteBuffer;

import com.epam.server.Connection;
import com.epam.server.ConnectionContainer;
import com.epam.server.MessageSender;

/**
 * Handles message of one type.
 * 
 */
public interface SingleMessageHandler {

	/**
	 * Handles the message of given type.
	 * 
	 * @param byteBuffer
	 *            buffer with received message.
	 * @return {@link ByteBuffer} with answer message or null if no answer
	 *         needed.
	 */
	ByteBuffer handle(ByteBuffer byteBuffer,
			ConnectionContainer<?> connectionContainer,
			MessageSender<?> messageSender, Connection<?> connection);
}
