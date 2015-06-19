package com.tshcmiller.grapple.sound;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

public final class Sound {
	
	public static boolean playSound = true; //Play sound
	
	private Clip clip; //Each sound reserves a clip...allows mutliple sounds to be played at once
	
	//You can not instantiate outside this class
	private Sound() throws LineUnavailableException {
		clip = AudioSystem.getClip();
	} 
	
	public static void play(Sounds sound) {
		if (!playSound)
			return;
		
		String path = sound.path;
		
		try {
			AudioInputStream input = AudioSystem.getAudioInputStream(new File(path));
			Sound inst = new Sound();
			Clip clip = inst.clip;
			
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
