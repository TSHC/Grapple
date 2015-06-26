package com.tshcmiller.grapple.entity;

import com.tshcmiller.grapple.world.World;

public abstract class Ship extends Entity {
	
	public Ship(World world, float x, float y) {
		super(world, x, y, 1350f, "res/textures/default-ship.png");		
	}
	
}
