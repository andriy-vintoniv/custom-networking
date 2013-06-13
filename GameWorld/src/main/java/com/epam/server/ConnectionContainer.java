package com.epam.server;

public interface ConnectionContainer<T> {
	void addConnection(Integer pointId, T connection);

	T getConnection(Integer pointId);
}
