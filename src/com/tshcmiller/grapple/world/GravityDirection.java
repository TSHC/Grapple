package com.tshcmiller.grapple.world;

public enum GravityDirection {
	PULL_UPWARDS    ("up"),
	PULL_DOWNWARDS  ("down"),
	PULL_LEFTWARDS  ("left"),
	PULL_RIGHTWARDS ("right"),
	PULL_CENTER     ("center");
	
	private String name;
	
	private GravityDirection(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
