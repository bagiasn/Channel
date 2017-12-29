package main.server.model;

import java.util.ArrayList;
import java.util.List;

public class Channel {
	
	private String name;
	private Channel parent;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Channel getParent() {
		return parent;
	}

	public List<Channel> getChildren() {
		return children;
	}

	private List<Channel> children;

	public Channel(String name, Channel parent) {
		this.name = name;
		this.parent = parent;
		this.children = new ArrayList<Channel>();
	}

	public void addChild(Channel child) {
		children.add(child);
	}
}
