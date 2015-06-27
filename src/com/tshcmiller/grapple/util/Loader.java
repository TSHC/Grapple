package com.tshcmiller.grapple.util;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Loader {
	
	private String path;
	
	public Loader(String path) {
		this.path = path;
	}
	
	public Audio getAudio() {
		Audio audio = null;
		
		try {
			audio = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return audio;
	}
	
	public TrueTypeFont getCustomFont(float size, boolean antiAlias) {
		Font font = null;
		
		try {			
			InputStream stream = ResourceLoader.getResourceAsStream("res/fonts/contl.ttf");
			
			font = Font.createFont(Font.TRUETYPE_FONT, stream);
			font = font.deriveFont(size);
						
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		
		return new TrueTypeFont(font, antiAlias);
	}
	
	public Texture getTexture() {
		Texture texture = null;
		
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return texture;
	}
}
