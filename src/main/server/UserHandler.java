package main.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import main.server.utils.UserManager;

public class UserHandler implements Runnable {
	
	private Socket socket;
	private int id;
	
	private InputStream inputStream;
	private OutputStream outputStream;
	
	public UserHandler(int id, Socket socket) {
		this.id = id;
		this.socket = socket;
	}

	@Override
	public void run() {
		byte[] inputBuffer = new byte[256];
		String msg;
		// Get a reference to the user manager.
		UserManager userManager = UserManager.getInstance();
		
		try {
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
			// Server starts the conversation by sending greetings and the available commands.
			outputStream.write("Welcome to Channel".getBytes());
			outputStream.write("Available commands are:...".getBytes());
			while (true) {
				inputStream.read(inputBuffer);
				msg = new String(inputBuffer, StandardCharsets.UTF_8);
				System.out.println("User says: " + msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// Remove yourself from the list, before exiting.
			userManager.removeUser(id);
		}
	}
	
	public void sendMessage(String message) {
		if (outputStream != null) {
			try {
				outputStream.write(message.getBytes(), 0, message.length());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("This user is not alive");
		}
	}
}
