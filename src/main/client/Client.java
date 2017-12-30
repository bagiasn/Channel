package main.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client implements Runnable {
	
	private static final int port = 32467;
	private Socket socket;
	
	@Override
	public void run() {
		String msg;
		
		try {
			socket = new Socket(InetAddress.getByName(null), port);
			while (true) {
				byte[] inputBuffer = new byte[64];
				socket.getInputStream().read(inputBuffer);
				msg = new String(inputBuffer, StandardCharsets.UTF_8);
				System.out.println("Server says: " + msg);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void sendMessage(String msg) {
		try {
			socket.getOutputStream().write(msg.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
