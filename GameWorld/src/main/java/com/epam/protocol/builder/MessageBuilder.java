package com.epam.protocol.builder;

import java.io.DataInputStream;

import com.epam.protocol.domain.message.Message;

public interface MessageBuilder<T extends Message> {
	public T buildMessage(DataInputStream inputStream);
}
