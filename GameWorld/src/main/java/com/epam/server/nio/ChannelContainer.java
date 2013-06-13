package com.epam.server.nio;

import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

import com.epam.server.ConnectionContainer;

public class ChannelContainer implements ConnectionContainer<SocketChannel> {

	private Map<Integer, SocketChannel> channels;
	private static ChannelContainer instance = new ChannelContainer();

	private ChannelContainer() {
		channels = new HashMap<Integer, SocketChannel>();
	}

	@Override
	public void addConnection(Integer pointId, SocketChannel channel) {
		channels.put(pointId, channel);
	}

	public static ChannelContainer getInstance() {
		return instance;
	}

	@Override
	public SocketChannel getConnection(Integer pointId) {
		return channels.get(pointId);
	}

	public Map<Integer, SocketChannel> getChannels() {
		return channels;
	}

	public void setChannels(Map<Integer, SocketChannel> channels) {
		this.channels = channels;
	}

}
