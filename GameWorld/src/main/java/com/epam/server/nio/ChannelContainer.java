package com.epam.server.nio;

import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;

public class ChannelContainer {

	private Map<String, SocketChannel> channels;
	private static ChannelContainer instance = new ChannelContainer();

	private ChannelContainer() {
		channels = new HashMap<String, SocketChannel>();
	}

	public void addChannel(SocketChannel channel, String clientName) {
		channels.put(clientName, channel);
	}

	public static ChannelContainer getInstance() {
		return instance;
	}

	public SocketChannel getChannel(String clientName) {
		return channels.get(clientName);
	}

	public Map<String, SocketChannel> getChannels() {
		return channels;
	}

	public void setChannels(Map<String, SocketChannel> channels) {
		this.channels = channels;
	}

}
