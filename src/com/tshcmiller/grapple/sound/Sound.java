package com.tshcmiller.grapple.sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public final class Sound {
	
	private Sound() {} //You can not instantiate outside this class
	
	public static boolean playSound = true;
	
	public static void play(Sounds sound) {
		if (!playSound)
			return;
		
		String path = sound.path;
		
		try {
			AudioInputStream input = AudioSystem.getAudioInputStream(new File(path));
			Clip clip = AudioSystem.getClip();
			clip.open(input);
			
			new Thread() {
				
				public void run() {
					synchronized (clip) {
						clip.stop();
						clip.setFramePosition(0);
						clip.start();
					}
				}
				
			}.start();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
