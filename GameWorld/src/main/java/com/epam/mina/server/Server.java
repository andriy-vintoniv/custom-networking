package com.epam.mina.server;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
	private static final int PORT = 1234;

	private final static Logger logger = (Logger) LoggerFactory
			.getLogger(Server.class);

	public static void main(String[] args) throws IOException {
		initServer();
	}

	private static void initServer() throws IOException {
		IoAcceptor acceptor = new NioDatagramAcceptor();

		acceptor.setHandler(new ServerHandler());
		DatagramSessionConfig sessionConfig = (DatagramSessionConfig) acceptor
				.getSessionConfig();

		sessionConfig.setReuseAddress(true);
		logger.debug("Starting server...");

		acceptor.bind(new InetSocketAddress("localhost", PORT));
		logger.debug("Server listening port: " + PORT);
		acceptor.broadcast("hello from server");
		System.out.println("brodcasting");
	}
}
