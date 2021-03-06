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
		renderQuad(cardColor, x, y, width, height);
		renderHeaderText(x, y, text, textColor);
	}
	
	public static void renderHeaderText(float x, float y, String text, Color color) {		
		LARGE.drawString(x, y, text, color);
	}
	
	public static void renderNormalText(float x, float y, String text, Color color) {
		SMALL.drawString(x, y, text, color);
	}
	
	public static void renderQuad(Color color, float x, float y, float width, float height) {
		glDisable(GL_TEXTURE_2D);
		
		glColor3f(color.r, color.g, color.b);
		
		glBegin(GL_QUADS);
			glVertex2f(x, y);
			glVertex2f(x + width, y);
			glVertex2f(x + width, y + height);
			glVertex2f(x, y + height);
		glEnd();		
		
		glEnable(GL_TEXTURE_2D);
	}
	
	public static void renderQuadTexture(Texture texture, float rot, float x, float y) {
		float width = texture.getImageWidth();
		float height = texture.getImageHeight();
		
		Color.white.bind();
		texture.bind();
		
		glTranslatef(x + width / 2, y + height / 2, 0);
		glRotatef(rot, 0, 0, 1);
		glTranslatef(-width / 2, -height / 2, 0);
		
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
