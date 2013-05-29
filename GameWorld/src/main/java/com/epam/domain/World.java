package com.epam.domain;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.epam.protocol.domain.message.constants.MoveResult;

public class World {
	private final static World instance = new World();

	private ConcurrentMap<Integer, Point> points;
	private WorldMap worldMap;

	private World() {
		worldMap = WorldMap.getInstance();
		setPoints(new ConcurrentHashMap<Integer, Point>());
	}

	public static World getInstance() {
		return instance;
	}

	public void addPoint(Point point) {
		this.points.put(Integer.valueOf(point.getId()), point);
	}

	public void removePoint(Integer pointId) {
		if (points.containsKey(pointId)) {
			points.remove(pointId);
		}
	}

	/**
	 * Try to move point to position (x, y). If coordinates is valid(in range
	 * [0..99]) and position is free, than point is moved. Else point will not
	 * moved and reason will be returned.
	 * 
	 * @param pointId
	 * @param x
	 * @param y
	 * @return 1 - if moved successfully, 2 - if coordinates are not valid, 3 -
	 *         if position is busy.
	 */
	public int movePoint(int pointId, int x, int y) {
		int result = 0;
		synchronized (this) {
			if (worldMap.isValidCoordinates(x, y)) {
				if (!worldMap.isBusyPosition(x, y)) {
					result = MoveResult.MOVE_SUCCESS;
					this.worldMap.setPosition(x, y, true);
				} else {
					result = MoveResult.BUSY_POSITION;
				}
			} else {
				result = MoveResult.INVALID_POSITION;
			}
		}
		return result;
	}

	public WorldMap getWorldMap() {
		return worldMap;
	}

	public void setWorldMap(WorldMap worldMap) {
		this.worldMap = worldMap;
	}

	public ConcurrentMap<Integer, Point> getPoints() {
		return points;
	}

	public void setPoints(ConcurrentMap<Integer, Point> points) {
		this.points = points;
	}

}
