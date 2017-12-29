package main.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class UserHandler implements Runnable {
	
	private Socket socket;
	
	private InputStream inputStream;
	private OutputStream outputStream;
	
	public UserHandler(Socket clientSocket) {
		this.socket = clientSocket;
	}

	@Override
	public void run() {
		byte[] inputBuffer = new byte[256];
		String msg;
		
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
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
