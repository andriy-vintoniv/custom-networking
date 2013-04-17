package com.epam.client.udp;

import java.net.InetSocketAddress;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.future.IoFutureListener;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioDatagramConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epam.server.udp.MemoryMonitor;

public class MemMonClient extends IoHandlerAdapter {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(MemMonClient.class);

	private IoSession session;

	private IoConnector connector;

	public MemMonClient() {

		System.out.println("UDPClient::UDPClient");
		System.out.println("Created a datagram connector");
		connector = new NioDatagramConnector();

		System.out.println("Setting the handler");
		connector.setHandler(this);

		System.out.println("About to connect to the server...");
		ConnectFuture connFuture = connector.connect(new InetSocketAddress(
				"localhost", MemoryMonitor.PORT));

		System.out.println("About to wait.");
		connFuture.awaitUninterruptibly();

		System.out.println("Adding a future listener.");
		connFuture.addListener(new IoFutureListener<ConnectFuture>() {
			public void operationComplete(ConnectFuture future) {
				if (future.isConnected()) {
					System.out.println("...connected");
					session = future.getSession();
					try {
						sendData();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					LOGGER.error("Not connected...exiting");
				}
			}
		});
	}

	private void sendData() throws InterruptedException {
		for (int i = 0; i < 30; i++) {
			long free = Runtime.getRuntime().freeMemory();
			IoBuffer buffer = IoBuffer.allocate(8);
			buffer.putLong(free);
			buffer.flip();
			session.write(buffer);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				throw new InterruptedException(e.getMessage());
			}
		}
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		cause.printStackTrace();
	}

	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		System.out.println("Session recv...");
	}

	@Override
	public void messageSent(IoSession session, Object message) throws Exception {
		System.out.println("Message sent...");
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		System.out.println("Session closed...");
	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
		System.out.println("Session created...");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
		System.out.println("Session idle...");
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		System.out.println("Session opened...");
	}

	public static void main(String[] args) {
		new MemMonClient();
	}
}