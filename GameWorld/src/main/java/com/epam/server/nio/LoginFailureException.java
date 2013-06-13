package com.epam.server.nio;

public class LoginFailureException extends Exception {

	private static final long serialVersionUID = 3215317353798192032L;

	public LoginFailureException() {
		super();
	}

	public LoginFailureException(String message) {
		super(message);
	}

	public LoginFailureException(String message, Throwable cause) {
		super(message, cause);
	}
}
