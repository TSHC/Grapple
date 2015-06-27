package com.tshcmiller.grapple.inventory;

import com.tshcmiller.grapple.Grapple;
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
		super(world, x, y, "res/textures/testball.png");
	}

	@Override
	public void fire(Ship ship) {
		this.canBePickedUp = false;
		this.x = ship.getX();
		this.y = ship.getY();
		
		if (x >= Grapple.width / 2) {
			xa = -12f;
		} else {
			xa = 12f;
		}
		
		ya = -1f;
	}

	@Override
	public void hit(Ship ship) {
		this.addForDeletion();
		ship.damage(10);
	}

	@Override
	public void update(int delta) {
		super.update();
		move();
		
		if (isOutOfBounds()) {
			this.addForDeletion();
		}
	}

	
	
}