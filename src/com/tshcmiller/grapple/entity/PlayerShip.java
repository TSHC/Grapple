package com.tshcmiller.grapple.entity;

import org.lwjgl.input.Keyboard;

import com.tshcmiller.grapple.world.World;

public class PlayerShip extends Ship {

	public PlayerShip(World world, float x, float y) {
		super(world, x, y);
	}
	
			
	public void update(int delta) {
		float tick = 16f;
		float multiplier = 1;
//		float gravity = 0.50f * (1f / tick);
		
		//0.125 * 0.0625 (MIN) ( 1/8)
		//1.500 * 0.0625 (MAX) (12/8)
		float acceleration = multiplier * (1f / tick);
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			ya -= acceleration;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			ya += acceleration;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			xa -= acceleration;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			xa += acceleration;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			
		}
		
//		ya += gravity;
		
//		System.out.printf("XA: %.2f, YA: %.2f%n", xa, ya);
		
		x += xa;
		y += ya;
		
	}
}
