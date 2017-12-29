package main.client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client implements Runnable {
	
	private static final int port = 32467;

	@Override
	public void run() {
		byte[] inputBuffer = new byte[256];
		Socket socket = null;
		String msg;
		
		try {
			socket = new Socket(InetAddress.getByName(null), port);
			while(true) {
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
}
