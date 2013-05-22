package com.epam.domain;

import java.util.HashMap;
import java.util.Map;

public class WorldMap {

	public final static int MAP_SIZE = 100;
	private static WorldMap instance = new WorldMap();

	private Map<WorldPosition, State> worldMap;

	private WorldMap() {
		this.worldMap = new HashMap<WorldPosition, State>();

		for (int i = 0; i < MAP_SIZE; i++) {
			for (int j = 0; j < MAP_SIZE; j++) {
				worldMap.put(new WorldPosition(i, j), new State());
			}
		}
	}

	public static WorldMap getInstance() {
		return instance;
	}

	public Map<WorldPosition, State> getWorldMap() {
		return worldMap;
	}

	public void setWorldMap(Map<WorldPosition, State> worldMap) {
		this.worldMap = worldMap;
	}
}
