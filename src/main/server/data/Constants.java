package main.server.data;

public class Constants {
	// Commands
	public final static String CMD_LIST = "list";
	public final static String CMD_SEND = "send";
	public final static String CMD_SHOUT = "shout";
	public final static String CMD_SUBSCRIBE = "subscribe";
	public final static String CMD_STOP = "stop";
	
	public final static String CMD_ARG_GROUP = "groups";
	public final static String CMD_ARG_CHANNEL = "channels";
	// Return messages
	public final static String RTN_EMPTY = "Empty message";
	public final static String RTN_INVALID = "Invalid format";
	public final static String RTN_UNKNOWN = "Unknown command";
	public final static String RTN_NOT_EXISTS = "This channel does not exist.";
	public final static String RTN_ALREADY_SUB = "You are already subscribed to this channel.";
	public final static String RTN_NOT_SUB = "You are not subscribed to any channel.";
	public final static String RTN_OK = "OK";
	public final static String RTN_GREETING = "Welcome to Channel!\n"
			+ "Available commands:"
			+ "\nlist [groups|channels]"
			+ "\nsubscribe --channel"
			+ "\nsend --message"
			+ "\nshout --message"
			+ "\nstop service";
	
	public final static String MSG_SHOUT = "User %d shouts ";
	
	// Let's use geographic vocabulary to show case the functionality.
	public final static String[] continents = {"Europe", "Americas", "Oceania", "Asia", "Africa"};
	public final static String[][] countries = {
			{"France", "Germany", "Austria", "Italy", "Spain", "Greece"},
			{"USA", "Mexico", "Canada", "Colombia", "Brazil", "Argentina"},
			{"Australia", "New Zealand", "New Guinea", "Fiji", "Samoa", "Tonga"},
			{"China", "India", "Mongolia", "Pakistan", "Iran", "Irak"},
			{"Zambia", "Marocco", "Libya", "Zimbabwe", "Tunisia", "Nigeria"}
	};
}
