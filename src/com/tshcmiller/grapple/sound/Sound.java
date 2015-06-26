package com.tshcmiller.grapple.sound;

import org.newdawn.slick.openal.Audio;

import com.tshcmiller.grapple.Settings;
import com.tshcmiller.grapple.util.Loader;

public enum Sound {
	
	LAUNCHER_SCROLL("launcher-scroll.wav");
	
	private Audio audio;
	
	Sound(String name) {
		audio = new Loader("res/audio/" + name).getAudio();
	}
	
	public static void playSoundEffect(Sound sound) {
		if (Settings.settings.playSound)
			sound.audio.playAsSoundEffect(1.0f, 1.0f, false);
	}
	
	public static void playMusic(Sound sound) {
		if (Settings.settings.playSound)
			sound.audio.playAsMusic(1.0f, 1.0f, true);
	}
}
