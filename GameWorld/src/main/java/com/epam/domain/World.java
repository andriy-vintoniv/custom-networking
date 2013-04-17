package com.epam.domain;

import java.util.HashSet;
import java.util.Set;

public class World {
	private Set<User> users;
	private WorldMap worldMap;

	public World() {
		users = new HashSet<User>();
		worldMap = new WorldMap();
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public WorldMap getWorldMap() {
		return worldMap;
	}

	public void setWorldMap(WorldMap worldMap) {
		this.worldMap = worldMap;
	}
}
