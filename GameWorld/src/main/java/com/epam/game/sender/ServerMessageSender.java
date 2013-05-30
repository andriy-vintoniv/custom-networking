package com.epam.game.sender;

import com.epam.protocol.domain.message.Message;

public interface ServerMessageSender<T extends Message> {
	void send(T message);
}
