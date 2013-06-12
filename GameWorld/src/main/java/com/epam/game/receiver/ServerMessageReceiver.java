package com.epam.game.receiver;

public interface ServerMessageReceiver<T> {
	T receive();
}
