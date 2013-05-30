package com.epam.game.util;

import com.epam.game.domain.Point;

public class Filter {

	private static final int VISIBILITY = 2;

	public boolean accept(Point senderPoint, Point receiverPoint) {
		boolean result = false;
		int senderX = senderPoint.getX();
		int senderY = senderPoint.getY();
		int receiverX = receiverPoint.getX();
		int receiverY = receiverPoint.getY();

		if (isVisible(senderX, receiverX) && isVisible(senderY, receiverY)) {
			result = true;
		}
		return result;
	}

	private boolean isVisible(int senderCoordinate, int receiverCoordinate) {
		boolean result = false;

		if ((senderCoordinate - receiverCoordinate <= VISIBILITY)
				|| (receiverCoordinate - senderCoordinate <= VISIBILITY)) {
			result = true;
		}

		return result;
	}
}
