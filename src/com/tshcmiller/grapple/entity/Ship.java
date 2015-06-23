package com.tshcmiller.grapple.entity;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.tshcmiller.grapple.Grapple;

public class Ship extends Entity {
	
	private int key_up = Keyboard.KEY_W;
	private int key_down = Keyboard.KEY_S;
	private int key_left = Keyboard.KEY_A;
	private int key_right = Keyboard.KEY_D;
	
	private float syf = 0.0f;
	private float sxf = 0.0f;

	public Ship(float x, float y) {
		super("res/textures/default-ship.png", x, y);
	}
	
	private boolean isPressed(int key) {
		return Keyboard.isKeyDown(key);
	}
	
	public void update(int delta) {
		float force = 16f / 100f;
		float gravity = 8f / 100f;
		
		if (isPressed(key_up)) {
			syf -= force;
		}
		
		if (isPressed(key_down)) {
			syf += force;
		}
		
		if (isPressed(key_left)) {
			sxf -= force;
		}
		
		if (isPressed(key_right)) {
			sxf += force;
		}
		
		y += syf;
		x += sxf;
		syf += gravity;
		
		if (y < 0) {
			y = 0;
		}
		
		if (y > Grapple.HEIGHT) {
			y = Grapple.HEIGHT;
		}
		
		if (x < 0) {
			x = 0;
		}
		
		if (x > Grapple.WIDTH) {
			x = Grapple.WIDTH;
		}
	}
	
	public void render() {
		GL11.glPushMatrix();
		texture.bind();
		GL11.glTranslatef(x, y, 0);
    	GL11.glColor3f(1,1,1);
		
		GL11.glBegin(GL11.GL_QUADS);
	      GL11.glTexCoord2f(0, 0);
	      GL11.glVertex2f(0, 0);
	      GL11.glTexCoord2f(0, texture.getHeight());
	      GL11.glVertex2f(0, texture.getImageHeight());
	      GL11.glTexCoord2f(texture.getWidth(), texture.getHeight());
	      GL11.glVertex2f(texture.getImageWidth(), texture.getImageHeight());
	      GL11.glTexCoord2f(texture.getWidth(), 0);
	      GL11.glVertex2f(texture.getImageWidth(), 0);
		GL11.glEnd();
		
		GL11.glPopMatrix();
	}
}
