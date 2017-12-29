package main.server.model;

public class ChannelTree {

	private Channel root;
	
	public ChannelTree(String name) {
		root = new Channel(name, null);
	}
	
	public void addChannel(Channel newChannel) {
		// TODO Implement tree insertion logic.
		root.addChild(newChannel);
	}
}
