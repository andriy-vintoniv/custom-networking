package com.epam.server.nio;

import java.nio.channels.SocketChannel;

import com.epam.server.Connection;

public class NIOConnection implements Connection<SocketChannel> {
	private SocketChannel socketChannel;

	public NIOConnection(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
	}

	@Override
	public SocketChannel getConnection() {
		return socketChannel;
	}

}
