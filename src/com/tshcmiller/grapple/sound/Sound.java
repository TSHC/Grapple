package com.tshcmiller.grapple.sound;

import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.tshcmiller.grapple.Settings;

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
	
	public static void playSoundEffect(Sound sound) {
		if (Settings.settings.playSound)
			sound.audio.playAsSoundEffect(1.0f, 1.0f, false);
	}
	
	public static void playMusic(Sound sound) {
		if (Settings.settings.playSound)
			sound.audio.playAsMusic(1.0f, 1.0f, true);
	}
}
