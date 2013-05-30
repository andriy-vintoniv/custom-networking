package com.epam.game.domain;

import java.util.BitSet;

public class WorldMap {

	public final static int MAP_SIZE = 100;
	private static WorldMap instance = new WorldMap();

	private BitSet bitset;

	private WorldMap() {
		bitset = new BitSet(MAP_SIZE * MAP_SIZE);
	}

	/**
	 * Sets position to busy or free
	 * 
	 * @param x
	 *            - coordinate
	 * @param y
	 *            - coordinate
	 * @param busy
	 *            - set true, if position is busy, else - false
	 */
	public void setPosition(int x, int y, boolean busy) {
		this.bitset.set(x * y, busy);
	}

	public boolean isBusyPosition(int x, int y) {
		return this.bitset.get(x * y);
	}

	public boolean isValidCoordinates(int x, int y) {
		boolean result = false;
		if ((x > 0 && x < WorldMap.MAP_SIZE)
				&& (y > 0 && y < WorldMap.MAP_SIZE)) {
			result = true;
		}
		return result;
	}

	public static WorldMap getInstance() {
		return instance;
	}
}
