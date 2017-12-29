package main.server.utils;

import java.util.HashMap;
import java.util.Map;

import main.server.UserHandler;

public class UserManager {
	
	private static UserManager instance;
	
	Map<Integer, UserHandler> userMap;
	
	private UserManager() {
		userMap = new HashMap<Integer, UserHandler>();
	}
	
	public static UserManager getInstance() {
		if (instance == null) {
			synchronized (UserManager.class) {
				if (instance == null) {
					instance = new UserManager();
				}
			}
		}
		return instance;
	}
	
	public void insertUser(int id, UserHandler user) {
		userMap.put(id, user);
	}
	
	public void removeUser(int id) {
		userMap.remove(id);
	}
	
	public UserHandler getUser(int id) {
		return userMap.get(id);
	}
}
