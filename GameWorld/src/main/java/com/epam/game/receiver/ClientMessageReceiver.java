package com.epam.game.receiver;

import com.epam.protocol.domain.message.Message;

public interface ClientMessageReceiver<T extends Message> {
	T receive();
}
