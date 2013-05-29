package com.epam.protocol.domain.message.client;

import com.epam.protocol.domain.message.Message;
import com.epam.protocol.domain.message.constants.ClientMessageType;

public class LoginClientMessage implements Message {

	private static final byte id = ClientMessageType.CM_LOGIN;
	private String login;

	public LoginClientMessage() {
	}

	public LoginClientMessage(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public byte getId() {
		return id;
	}
}
