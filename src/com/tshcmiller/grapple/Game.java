package com.tshcmiller.grapple;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

import com.tshcmiller.grapple.world.World;

public class Game {
	
	private World world;
	public TrueTypeFont contl;
	
	public Game() {
		world = new World(this, 0.09f);
		
		try {
			System.out.println("Registering fonts");
			
			InputStream stream = ResourceLoader.getResourceAsStream("res/fonts/contl.ttf");
			
			Font font = Font.createFont(Font.TRUETYPE_FONT, stream);
			font = font.deriveFont(24f);
						
			contl = new TrueTypeFont(font, false);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}
	
	public World getWorld() {
		return world;
	}
}