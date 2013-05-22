package com.epam.domain;

public class Point {

	private int id;
	private String name;
	private String color;
	private WorldPosition worldPosition;

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public WorldPosition getWorldPosition() {
		return worldPosition;
	}

	public void setWorldPosition(WorldPosition worldPosition) {
		this.worldPosition = worldPosition;
	}
}
