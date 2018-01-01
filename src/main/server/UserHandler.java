package main.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import main.server.data.Constants;
import main.server.utils.ChannelManager;
import main.server.utils.InputHelper;
import main.server.utils.UserManager;

public class UserHandler implements Runnable {
	
	private final static int bufferSize = 512;
	
	private int id;
	private Socket socket;
	private OutputStream outputStream;
	
	public UserHandler(int id, Socket socket) {
		this.id = id;
		this.socket = socket;
	}

	@Override
	public void run() {
		// Get a reference to the channel manager.
		ChannelManager channelManager = ChannelManager.getInstance();
		
		try {
			InputStream inputStream = socket.getInputStream();
			// Reference the member variable to send messages concurrently.
			outputStream = socket.getOutputStream();
			// Server starts the conversation by sending greetings and the available commands.
			outputStream.write(Constants.RTN_GREETING.getBytes(), 0, Constants.RTN_GREETING.length());
			while (true) {
				// We reallocate a new buffer to avoid cleaning it up. Let GC do its job.
				byte[] inputBuffer = new byte[bufferSize];
				// Block while waiting for a message.
				inputStream.read(inputBuffer);
				// Convert the byte array to string.
				String msg = new String(inputBuffer, StandardCharsets.UTF_8);
				// Log the message as arrived.
				System.out.println("User " + id + " says: " + msg);
				// Pass the message to handle appropriately.
				String result = InputHelper.handleResponse(msg, id, channelManager);
				// Just, send the result as it is.
				outputStream.write(result.getBytes());			
			}
		} catch (IOException e) {
			System.out.println("Error occured: " + e.getMessage());
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println("Could not close socket.");
				}
			}	
			// Get a reference to the user manager.
			UserManager userManager = UserManager.getInstance();
			// Remove yourself from the list, before exiting.
			userManager.removeUser(id);
		}
	}
	
	public void sendMessage(String message) {
		if (outputStream != null) {
			try {
				outputStream.write(message.getBytes(), 0, message.length());
			} catch (IOException e) {
				System.out.println("Error occured: " + e.getMessage());
			}
		} else {
			System.out.println("This user is not alive");
		}
	}
}
