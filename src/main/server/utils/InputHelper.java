package main.server.utils;

import main.server.data.Constants;

public class InputHelper {
	
	public static String handleResponse(String input,  int id, ChannelManager channelManager) {
		// Avoid abnormal conditions first.
		if (input == null) return Constants.RTN_EMPTY;
		if (input.isEmpty()) return Constants.RTN_EMPTY;
		// Validate format. 
		// Since we don't have a third argument we just split at the first space character. 
		// Also, remove any quotes so they don't spoil the party.
		String[] results = input.replaceAll("\"", "").split("\\s", 2);
		if (results != null && results.length >= 2) {
			// Route action by command.
			String response;
			switch (results[0]) {
				case Constants.CMD_SEND:
					response = channelManager.sendMessage(id, results[1]);
					break;
				case Constants.CMD_SHOUT:
					response = channelManager.shoutMessage(id, results[1]);
					break;
				case Constants.CMD_SUBSCRIBE:
					response = channelManager.subscribeUser(id, results[1]);
					break;
				default:
					response = Constants.RTN_UNKNOWN;
			}
			return response;
		}
		return Constants.RTN_INVALID;
	}

}
