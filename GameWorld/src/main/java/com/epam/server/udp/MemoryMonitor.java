package com.epam.server.udp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.DatagramSessionConfig;
import org.apache.mina.transport.socket.nio.NioDatagramAcceptor;

public class MemoryMonitor {

	public static final int PORT = 18567;

	private ConcurrentHashMap<SocketAddress, String> clients;

	public MemoryMonitor() throws IOException {

		NioDatagramAcceptor acceptor = new NioDatagramAcceptor();
		acceptor.setHandler(new MemoryMonitorHandler(this));

		DefaultIoFilterChainBuilder chain = acceptor.getFilterChain();
		chain.addLast("logger", new LoggingFilter());

		DatagramSessionConfig dcfg = acceptor.getSessionConfig();
		dcfg.setReuseAddress(true);

		clients = new ConcurrentHashMap<SocketAddress, String>();

		acceptor.bind(new InetSocketAddress(PORT));

		System.out.println("UDPServer listening on port " + PORT);
	}

	protected void recvUpdate(SocketAddress clientAddr, long update) {
		String client = clients.get(clientAddr);
		if (client != null) {
			client = String.valueOf(update);
		} else {
			System.err.println("Received update from unknown client");
		}
	}

	protected void addClient(SocketAddress clientAddr) {
		if (!containsClient(clientAddr)) {
			String client = clientAddr.toString();
			clients.put(clientAddr, client);
		}
	}

	protected boolean containsClient(SocketAddress clientAddr) {
		return clients.contains(clientAddr);
	}

	protected void removeClient(SocketAddress clientAddr) {
		clients.remove(clientAddr);
	}

	public static void main(String[] args) throws IOException {
		new MemoryMonitor();
	}
}
