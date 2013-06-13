package com.epam.server.nio;

import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public class ChannelContainer {

	private Map<Integer, SocketChannel> channels;
	private static ChannelContainer instance = new ChannelContainer();

	private ChannelContainer() {
		channels = new HashMap<Integer, SocketChannel>();
	}

	public void addChannel(SocketChannel channel, Integer pointId) {
		channels.put(pointId, channel);
	}

	public static ChannelContainer getInstance() {
		return instance;
	}

	public SocketChannel getChannel(String clientName) {
		return channels.get(clientName);
	}

	public Map<Integer, SocketChannel> getChannels() {
		return channels;
	}

	public void setChannels(Map<Integer, SocketChannel> channels) {
		this.channels = channels;
	}

}
