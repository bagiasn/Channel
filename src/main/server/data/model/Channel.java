package main.server.data.model;

import java.util.ArrayList;
import java.util.List;

public class Channel {
	
	private String name;
	private Channel parent;
	private List<Channel> children;
	private List<Integer> members;
	
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

	public Channel(String name, Channel parent) {
		this.name = name;
		this.parent = parent;
		this.children = new ArrayList<Channel>();
		this.members = new ArrayList<Integer>();
	}

	public void addChild(Channel child) {
		children.add(child);
	}

	public List<Integer> getMembers() {
		return members;
	}

	public void addMember(int memberId) {
		members.add(memberId);
	}
}
