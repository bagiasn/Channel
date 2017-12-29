package main.server.utils;

import main.server.model.Channel;
import main.server.model.ChannelTree;

public class ChannelManager {
	
	private static ChannelManager instance;
	
	private ChannelTree channelTree;
	
	private ChannelManager() {
		channelTree = new ChannelTree("root");
	}
	
	public static ChannelManager getInstance() {
		if (instance == null) {
			synchronized (ChannelManager.class) {
				if (instance == null) {
					instance = new ChannelManager();
				}
			}
		}
		return instance;
	}
	
	public void addChannel(Channel newChannel) {
		channelTree.addChannel(newChannel);
	}
}