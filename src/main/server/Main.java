package main.server;

public class Main {

	public static void main(String[] args) {
		// Start our main server.
		Thread server = new Thread(new Server());
		server.start();
		
		try {
			server.join();
		} catch (InterruptedException e) {
			System.out.println("Server crashed with error: " + e.getMessage());
		}
	}

}
