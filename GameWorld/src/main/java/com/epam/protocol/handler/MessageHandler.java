package com.epam.protocol.handler;

import java.nio.ByteBuffer;

/**
 * Handles aany message by message type
 * 
 */
public interface MessageHandler {

	/**
	 * Handles the message of given type.
	 * 
	 * @param byteBuffer
	 *            buffer with received message.
	 * @return {@link ByteBuffer} with answer message or null if no answer
	 *         needed.
	 */

	/**
	 * Handles the message of given type.
	 * 
	 * @param byteBuffer
	 *            buffer with received message.
	 * @param messageType
	 *            type of message which should be handled
	 * @return {@link ByteBuffer} with answer message or null if no answer
	 *         needed.
	 */
	ByteBuffer handle(ByteBuffer byteBuffer, byte messageType);
}
