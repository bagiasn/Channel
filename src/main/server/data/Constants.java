package main.server.data;

public class Constants {
	
	public final static String CMD_SEND = "send";
	public final static String CMD_SHOUT = "shout";
	public final static String CMD_SUBSCRIBE = "subscribe";
	
	// Returns messages
	public final static String RTN_EMPTY = "Empty message";
	public final static String RTN_INVALID = "Invalid format";
	public final static String RTN_UNKNOWN = "Unknown command";
	public final static String RTN_NOT_EXISTS = "This channel does not exist.";
	public final static String RTN_ALREADY_SUB = "You are already subscribed to this channel.";
	public final static String RTN_NOT_SUB = "You are not subscribed to any channel.";
	public final static String RTN_OK = "OK";
	public final static String RTN_GREETING = "Welcome to Channel!\n"
			+ "Available commands:"
			+ "\nsubscribe --channel"
			+ "\nsend --message"
			+ "\nshout --message";
	
	public final static String MSG_SHOUT = "User %d shouts ";
	
	public final static String[] continents = {"Europe", "Americas", "Oceania", "Asia", "Africa"};
	public final static String[][] countries = {
			{"France", "Germany", "Austria", "Italy", "Spain", "Greece"},
			{"USA", "Mexico", "Canada", "Colombia", "Brazil", "Argentina"},
			{"Australia", "New Zealand", "New Guinea", "Fiji", "Samoa", "Tonga"},
			{"China", "India", "Mongolia", "Pakistan", "Iran", "Irak"},
			{"Zambia", "Marocco", "Libya", "Zimbabwe", "Tunisia", "Nigeria"}
	};
}
