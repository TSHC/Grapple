package com.tshcmiller.grapple;

import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class GrappleLauncher {
	
	private Texture background;
	
	public void loadLauncher() {
		try {
			background = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/textures/splash.png"));
			Display.setDisplayMode(new DisplayMode(800, 450));
			Display.setTitle("Grapple Launcher");

		} catch (LWJGLException | IOException e) {
			e.printStackTrace();
		}
		
		
	}
}