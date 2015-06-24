package com.tshcmiller.grapple.entity;

import com.tshcmiller.grapple.world.World;

public class GravityBall extends Entity {

	public GravityBall(World world, float x, float y) {
		super(world, 20f, "res/textures/testball.png", x, y);
	}
	
	@Override
	public void update(int delta) {
		move();
	}
	
}
