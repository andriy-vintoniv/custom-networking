package com.epam.domain;

import java.util.HashMap;
import java.util.Map;

import com.epam.domain.Coordinate;
import com.epam.domain.State;

public class WorldMap {

	private final static int MAP_SIZE = 100;

	private Map<Coordinate, State> worldMap;

	public WorldMap() {
		this.worldMap = new HashMap<Coordinate, State>();

		for (int i = 0; i < MAP_SIZE; i++) {
			for (int j = 0; j < MAP_SIZE; j++) {
				worldMap.put(new Coordinate(i, j), new State());
			}
		}
	}

	public Map<Coordinate, State> getWorldMap() {
		return worldMap;
	}

	public void setWorldMap(Map<Coordinate, State> worldMap) {
		this.worldMap = worldMap;
	}
}
