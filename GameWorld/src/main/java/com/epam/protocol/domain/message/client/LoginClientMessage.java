package com.epam.protocol.domain.message.client;

import com.epam.protocol.domain.message.Message;

public class LoginClientMessage implements Message {

	private static final byte id = 1;
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
