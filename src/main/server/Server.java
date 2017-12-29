package main.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server implements Runnable {

	private final static int port = 32467;
	private final static int maxUsers = 50;
	
	@Override
	public void run() { 
		Executor userPool = Executors.newFixedThreadPool(maxUsers);
		
		ServerSocket server = null;
		// 
		try {
			// Create a server socket to handle connections.
			server = new ServerSocket(port);
			// Run forever.
			while(true) {
				// Create a new socket for each client.
				Socket clientSocket = server.accept();
				// Handle each user in a separate thread.
				UserHandler user = new UserHandler(clientSocket);
				// Execute.
				userPool.execute(user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (server != null)
				try {
					server.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
