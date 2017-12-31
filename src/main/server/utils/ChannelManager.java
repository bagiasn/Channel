package main.server.utils;

import main.server.UserHandler;
import main.server.data.Constants;
import main.server.data.model.Channel;
import main.server.data.model.ChannelTree;

public class ChannelManager {
	
	private static ChannelManager instance;
	
	private ChannelTree channelTree;
	private UserManager userManager;
	
	private ChannelManager() {
		channelTree = new ChannelTree("Earth");
		channelTree.populateDefault();
		
		userManager = UserManager.getInstance();
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
	
	public String sendMessage(int id, String msg) {
		// Get the channel that the user belongs to.
		Channel userChannel = channelTree.getChannelByUser(id);
		if (userChannel == null) return Constants.RTN_NOT_SUB;
		// Iterate over its members and send the message.
		for (Integer userId: userChannel.getMembers()) {
			if (userId == id) continue;
			UserHandler handler = userManager.getUser(userId);
			handler.sendMessage(msg);
		}
		return Constants.RTN_OK;
	}
	
	public String shoutMessage(int id, String msg) {
		// Inform who is the user shouting.
		msg = String.format(Constants.MSG_SHOUT, id) + msg;
		// Get the channel that the user belongs to.
		Channel userChannel = channelTree.getChannelByUser(id);
		// Get the parent channel.
		Channel parent = userChannel.getParent();
		// Iterate over its children and send the message to all its members.
		for (Channel child: parent.getChildren()) {
			for (Integer userId: child.getMembers()) {
				if (userId == id) continue;
				UserHandler handler = userManager.getUser(userId);
				handler.sendMessage(msg);
			}
		}
		return Constants.RTN_OK;
	}
	
	public String subscribeUser(int id, String channelName) {
		// We need to check all the tree so get the root first.
		Channel root = channelTree.getRoot();
		for (Channel cont: root.getChildren()) {
			for (Channel country: cont.getChildren()) {
				if (country.getName().equalsIgnoreCase(channelName)) {
					// Make sure the user is not already subscribed.
					if (country.getMembers().contains(id)) {
						return Constants.RTN_ALREADY_SUB;
					} else {
						// Add the user to the list.
						country.getMembers().add(id);
						return Constants.RTN_OK;
					}
				}
			}
		}
		return Constants.RTN_NOT_EXISTS;
	}
}