package com.epam.domain;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.epam.protocol.domain.message.Message;
import com.epam.protocol.domain.message.server.MoveFailureMessage;
import com.epam.protocol.domain.message.server.MoveSuccessMessage;

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

	public Message movePoint(int pointId, WorldPosition newPosition) {
		Message moveMessage = null;
		if (isValidPosition(newPosition)) {
			if (!isBusy(newPosition)) {
				moveMessage = new MoveSuccessMessage(newPosition.getX(),
						newPosition.getY());

				this.worldMap.getWorldMap().put(newPosition,
						new State(true, pointId));
			} else {
				moveMessage = new MoveFailureMessage(
						MoveFailureMessage.BUSY_POSITION);
			}
		} else {
			moveMessage = new MoveFailureMessage(
					MoveFailureMessage.INVALID_POSITION);
		}
		return moveMessage;
	}

	private boolean isBusy(WorldPosition position) {
		State state = this.worldMap.getWorldMap().get(position);
		return state.isBusy();
	}

	private boolean isValidPosition(WorldPosition position) {
		boolean result;

		if (isValidCoordinate(position.getX())
				&& isValidCoordinate(position.getY())) {
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	private boolean isValidCoordinate(int coordinate) {
		boolean result = false;
		if (coordinate > 0 && coordinate < WorldMap.MAP_SIZE) {
			result = true;
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
