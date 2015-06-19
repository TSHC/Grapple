package com.tshcmiller.grapple.sound;

public enum Sounds {

	LAUNCHER_SCROLL("res/audio/launcher-scroll.wav");
	
	protected String path;
	
	private Sounds(String path) {
		this.path = path;
	}
}
