package com.epam.mina.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerHandler extends IoHandlerAdapter {

	private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());

	@Override
	public void sessionOpened(IoSession session) {
		// set idle time to 10 seconds
		session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);

		session.setAttribute("Values: ");
	}

	@Override
	public void messageReceived(IoSession session, Object message) {
		logger.info("Message received in the server..");
		logger.info("Message is: " + message.toString());

		session.write("Reply from server");
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) {
		logger.info("Disconnecting the idle.");
		// disconnect an idle client
		session.close(true);
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) {
		// close the connection on exceptional situation
		session.close(true);
	}
}
