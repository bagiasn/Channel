package main.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client implements Runnable {
	
	private final static int port = 32467;
	private final static int bufferSize = 512;
	
	private Socket socket;
	
	@Override
	public void run() {
		try {
			socket = new Socket(InetAddress.getByName(null), port);
			while (true) {
				byte[] inputBuffer = new byte[bufferSize];
				socket.getInputStream().read(inputBuffer);
				String msg = new String(inputBuffer, StandardCharsets.UTF_8);
				System.out.println(">: " + msg);
				System.out.print(">: ");
			}
		} catch (IOException e) {
			System.out.println("Error occured: " + e.getMessage());
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					System.out.println("Error while closing socket: " + e.getMessage());
				}
			}
		}
	}
	
	public void sendMessage(String msg) {
		try {
			socket.getOutputStream().write(msg.getBytes());
		} catch (IOException e) {
			System.out.println(">: Could not send message " + msg + " Reason: " + e.getMessage());
		}
	}
}
