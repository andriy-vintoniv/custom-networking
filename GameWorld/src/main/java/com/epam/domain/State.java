package com.epam.domain;

public class State {

	private boolean busy;
	private int userId;

	public State() {
		busy = false;
		userId = -1;
	}

	public State(boolean busy, int userId) {
		this.busy = busy;
		this.userId = userId;
	}

	public boolean isBusy() {
		return busy;
	}

	public void setBusy(boolean busy) {
		this.busy = busy;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
