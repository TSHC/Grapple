package com.tshcmiller.grapple.util;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

public final class Renderer {
	
	private static final TrueTypeFont CONTL = new Loader("res/fonts/contl.ttf").getCustomFont(false);
	
	private Renderer() {}
	
	public static void renderQuadTexture(Texture texture, float x, float y) {
		float width = texture.getImageWidth();
		float height = texture.getImageHeight();
		
		Color.white.bind();
		texture.bind();
		glTranslatef(x, y, 0);
		
		glBegin(GL_QUADS);
			glTexCoord2f(0, 0);
			glVertex2f(0, 0);
			glTexCoord2f(1, 0);
			glVertex2f(width, 0);
			glTexCoord2f(1, 1);
			glVertex2f(width, height);
			glTexCoord2f(0, 1);
			glVertex2f(0, height);
		glEnd();
		
		glLoadIdentity();
	}
	
	public static void drawString(float x, float y, String text) {
		drawString(x, y, text, Color.cyan);
	}
	
	public static void drawString(float x, float y, String text, Color color) {
		CONTL.drawString(x, y, text, color);
	}
}
