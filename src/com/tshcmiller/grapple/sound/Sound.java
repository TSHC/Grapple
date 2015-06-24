package com.tshcmiller.grapple.sound;

import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public enum Sound {
	
	LAUNCHER_SCROLL("launcher-scroll.wav");
	
	private Audio audio;
	
	Sound(String name) {
		try {
			audio = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream("res/audio/"+ name));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Audio getAudio() {
		return audio;
	}
	
	public static void playSoundEffect(Sound sound) {
		sound.audio.playAsSoundEffect(1.0f, 1.0f, false);
	}
	
	public static void playMusic(Sound sound) {
		sound.audio.playAsMusic(1.0f, 1.0f, true);
	}
}
