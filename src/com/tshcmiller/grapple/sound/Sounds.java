package com.tshcmiller.grapple.sound;

public enum Sounds {

	LAUNCHER_SELECT("res/sounds/launcher-select.wav");
	
	protected String path;
	
	private Sounds(String path) {
		this.path = path;
	}
}
