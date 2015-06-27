package com.tshcmiller.grapple.util;

import static org.lwjgl.opengl.GL11.*;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;

public final class Renderer {
	
	private static final TrueTypeFont SMALL = new Loader("res/fonts/contl.ttf").getCustomFont(16, false);
	private static final TrueTypeFont LARGE = new Loader("res/fonts/contb.ttf").getCustomFont(32, false);
	
	private Renderer() {}
	
	public static void renderCardText(float x, float y, float width, float height, Color cardColor, Color textColor, String text) {
		glColor4f(cardColor.r, cardColor.g, cardColor.b, cardColor.a);
		
		glBegin(GL_QUADS);
			glVertex2f(x, y);
			glVertex2f(x + width, y);
			glVertex2f(x + width, y + height);
			glVertex2f(x, y + height);
		glEnd();
		
		renderHeaderText(x, y, text, textColor);
	}
	
	public static void renderHeaderText(float x, float y, String text, Color color) {		
		LARGE.drawString(x, y, text, color);
	}
	
	public static void renderNormalText(float x, float y, String text, Color color) {
		SMALL.drawString(x, y, text, color);
	}
	
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
}
