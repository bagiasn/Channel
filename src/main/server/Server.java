package main.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import main.server.utils.UserManager;

public class Server implements Runnable {

	private final static int port = 32467;
	private final static int maxUsers = 50;
	
	@Override
	public void run() { 
		// Initialize user manager.
		UserManager userManager = UserManager.getInstance();
		// Create an executor for better thread management.
		Executor userPool = Executors.newFixedThreadPool(maxUsers);
		// Counter for the users.
		int userId = 0;
		
		ServerSocket server = null;

		try {
			// Create a server socket to handle connections.
			server = new ServerSocket(port);
			// Run forever.
			while (true) {
				// Create a new socket for each client.
				Socket clientSocket = server.accept();
				// Increase the number of users.
				userId++;
				// Handle each user in a separate thread.
				UserHandler user = new UserHandler(userId, clientSocket);
				// Add the user to the list.
				userManager.insertUser(userId, user);
				// Execute.
				userPool.execute(user);
			}
		} catch (IOException e) {
			System.out.println("Server failed with error: " + e.getMessage());
		} finally {
			if (server != null)
				try {
					server.close();
				} catch (IOException e) {
					System.out.println("Server could not close socket. Error: " + e.getMessage());
				}
		}
	}

}
