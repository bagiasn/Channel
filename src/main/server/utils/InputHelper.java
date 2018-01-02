package main.server.utils;

import main.server.data.Constants;

public class InputHelper {
	
	public static String handleResponse(String input,  int id, ChannelManager channelManager) {
		// Avoid abnormal conditions first.
		if (input == null) return Constants.RTN_EMPTY;
		if (input.isEmpty()) return Constants.RTN_EMPTY;
		// Validate format. 
		// Since we don't have a third argument we just split at the first space character. 
		String[] results = input.split("\\s+", 2);
		if (results != null && results.length >= 2) {
			String resultTrimmed = results[1].trim();
			// Route action by command.
			String response;
			switch (results[0].toLowerCase()) {
				case Constants.CMD_SEND:
					response = channelManager.sendMessage(id, resultTrimmed);
					break;
				case Constants.CMD_SHOUT:
					response = channelManager.shoutMessage(id, resultTrimmed);
					break;
				case Constants.CMD_SUBSCRIBE:
					response = channelManager.subscribeUser(id, resultTrimmed);
					break;
				case Constants.CMD_LIST:
					response = channelManager.getList(resultTrimmed);
					break;
				case Constants.CMD_STOP:
					response = Constants.CMD_STOP;
					break;
				default:
					response = Constants.RTN_UNKNOWN;
			}
			return response;
		}
		return Constants.RTN_INVALID;
	}

}
