package com.tshcmiller.grapple.inventory;

import com.tshcmiller.grapple.entity.Projectile;
import com.tshcmiller.grapple.entity.Ship;
import com.tshcmiller.grapple.world.World;

//A gravity ball is an item that the user can pick up.
//While being held, it will slowly increase the holder's gravity
//When thrown, the ball becomes a deadly projectile that will
//significantly increase the mass of the first available target it
//hits

public class GravityBall extends Item implements Projectile {

	public GravityBall(World world, float x, float y) {
		super(world, x, y, 1f, "res/textures/testball.png");
	}

	@Override
	public void fire() {
		this.canBePickedUp = false;
	}

	@Override
	public void hit(Ship ship) {
		
		
	}

	@Override
	public void update(int delta) {
		move();
		
		if (isOutOfBounds()) {
			this.addForDeletion();
		}
	}

	
	
}