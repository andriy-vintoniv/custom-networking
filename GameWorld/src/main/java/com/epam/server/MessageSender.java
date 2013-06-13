package com.epam.server;

import java.nio.channels.SocketChannel;

import com.epam.protocol.domain.message.Message;
import com.epam.server.nio.ChannelContainer;

public interface MessageSender<T> {

	/**
	 * Sends message to client.
	 * 
	 * @param pointId
	 *            identifier of message receiver.
	 * @param message
	 *            {@link Message} to send
	 */
	// TODO: change type from Object to T
	void send(int pointId, Message message, Connection<?> connection);

	/**
	 * Sends message to visible clients.
	 * 
	 * @param pointId
	 *            identifier of message receiver.
	 * @param message
	 *            {@link Message} to send
	 * @param channelContainer
	 *            {@link ChannelContainer} - container of available connection
	 */
	void send(int pointId, Message message,
			ConnectionContainer<?> channelContainer);

}
