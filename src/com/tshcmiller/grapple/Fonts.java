package com.tshcmiller.grapple;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

public final class Fonts {
	
	private Fonts() {}
	
	public static Font contb;
	public static Font contl;
	
	static {
		try {
		GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
		contb = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/contb.ttf"));
		contb = contb.deriveFont(Font.PLAIN, 48f);
		contl = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/contl.ttf"));
		contl = contl.deriveFont(Font.PLAIN, 18f);
		
		graphics.registerFont(contb);
		graphics.registerFont(contl);
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}
}
