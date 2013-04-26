package com.epam.protocol.domain.message.server;

import com.epam.protocol.domain.message.Message;

public class AnotherPoitnInfoMessage implements Message {
	private static final byte id = 3;
	private int pointId;
	private int x;
	private int y;
	private int color;
	private String name;

	public AnotherPoitnInfoMessage() {
	}

	public AnotherPoitnInfoMessage(int pointId, int x, int y, int color,
			String name) {
		this.pointId = pointId;
		this.x = x;
		this.y = y;
		this.color = color;
		this.name = name;
	}

	public byte getId() {
		return id;
	}

	public int getPointId() {
		return pointId;
	}

	public void setPointId(int pointId) {
		this.pointId = pointId;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
