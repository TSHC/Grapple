package com.tshcmiller.grapple.entity;

import org.lwjgl.input.Keyboard;

import com.tshcmiller.grapple.Grapple;
import com.tshcmiller.grapple.world.World;

public class Ship extends Entity {
	
	private int key_up = Keyboard.KEY_W;
	private int key_down = Keyboard.KEY_S;
	private int key_left = Keyboard.KEY_A;
	private int key_right = Keyboard.KEY_D;

	public Ship(World world, float x, float y) {
		super(world, 9f, "res/textures/default-ship.png", x, y);
	}
	
	private boolean isPressed(int key) {
		return Keyboard.isKeyDown(key);
	}
	
	public void update(int delta) {
		float force = 0.18f;
		
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
				
		if (y < 0) {
			y = 0;
		}
		
		if (y > Grapple.height - texture.getImageHeight()) {
			y = Grapple.height - texture.getImageHeight();
		}
		
		if (x < 0) {
			x = 0;
		}
		
		if (x > Grapple.width - texture.getImageWidth()) {
			x = Grapple.width - texture.getImageWidth();
		}
	}
}
