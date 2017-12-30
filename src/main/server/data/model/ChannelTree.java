package main.server.data.model;

import main.server.data.Constants;

public class ChannelTree {

	private Channel root;
	
	public ChannelTree(String name) {
		root = new Channel(name, null);
	}
	
	public Channel getRoot() {
		return root;
	}
	
	public void populateDefault() {
		// The first children are continents, then the countries.
		for (int i = 0; i < Constants.continents.length; i++) {
			Channel contChannel = new Channel(Constants.continents[i], root);
			for (String country: Constants.countries[i]) {
				Channel countryChannel = new Channel(country, contChannel);
				contChannel.addChild(countryChannel);
			}
			root.addChild(contChannel);
		}
	}
	
	public Channel getChannelByUser (int id) {
		for (Channel channel: root.getChildren()) {
			for (Channel subChannel: channel.getChildren()) {
				if (subChannel.getMembers().contains(id))
					return subChannel;
			}
		}
		return null;
	}
}
