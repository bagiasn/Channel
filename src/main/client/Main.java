package main.client;

public class Main {
	
	public static void main(String[] args) {
		// Start the client code.
		Thread client = new Thread(new Client());
		client.start();
		
		try {
			client.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
