package main.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String[] args) {
		// Start the client code.
		Client clientWorker = new Client();
		Thread client = new Thread(clientWorker);
		client.start();
		
		try {
			while (true) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String msg = reader.readLine();
				clientWorker.sendMessage(msg);
			}
		} catch (IOException e) {
			System.out.println("Client thread failed with error: " + e.getMessage());
		}
	}
}
