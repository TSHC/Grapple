package com.tshcmiller.grapple.entity;

import com.tshcmiller.grapple.world.World;

public class GravityBall extends Entity implements Item {
	
	public GravityBall(World world, float x, float y) {
		super(world, x, y, 1, "res/textures/testball.png");
	}

	@Override
	public void useItem(Entity e) {
		
	}

	@Override
	public boolean isStackable() {
		
		return false;
	}

	@Override
	public int getMaxStackSize() {
		
		return 0;
	}

	@Override
	public void update(int delta) {
		move();
		if (isOutOfBounds()) {
			addForDeletion();
		}
	}
	
}
