package com.epam.game.receiver;

import com.epam.protocol.domain.message.Message;

public interface ServerMessageReceiver<T extends Message> {
	T receive();
}
