package com.tshcmiller.grapple.world;

import com.tshcmiller.grapple.Grapple;
import com.tshcmiller.grapple.entity.Entity;

public class Gravity {
		
	private GravityDirection direction;
	private GravityDirection nextDirection;
	private float force;
	
	public Gravity(GravityDirection direction, float force) {
		this.direction = direction;
		this.nextDirection = nextDirection();
		this.force = force;
	}
	
	public void apply(Entity e) {
		float cxa = e.getXA();
		float cya = e.getYA();
		
		switch (direction) {
			case PULL_UPWARDS:
				cya -= force;
				e.setYA(cya);
			return;
			
			case PULL_DOWNWARDS:
				cya += force;
				e.setYA(cya);
			return;
			
			case PULL_LEFTWARDS:
				cxa -= force;
				e.setXA(cxa);
			return;
			
			case PULL_RIGHTWARDS:
				cxa += force;
				e.setXA(cxa);
			return;
			
			case PULL_CENTER:
				float x = Grapple.width >> 1;
				float y = Grapple.height >> 1;
				float ex = e.getX();
				float ey = e.getY();
				
				if (ex > x) {
					cxa -= force;
				} else {
					cxa += force;
				}
				
				if (ey > y) {
					cya -= force;
				} else {
					cya += force;
				}
				
				e.setXA(cxa);
				e.setYA(cya);

			return;
		}
	}
	
	public void changeDirection() {
		this.direction = nextDirection;
		this.nextDirection = nextDirection();
	}
	
	public GravityDirection nextDirection() {
		GravityDirection next = null;
		
		int counter = 0;
		
		do {
			int rand = (int)(Math.random() * GravityDirection.values().length);
			next = GravityDirection.values()[rand];
			counter++;
			
			if (counter > 1000)
				break;
			
		} while (direction == next || direction == GravityDirection.PULL_CENTER);
		
		return next;
	}
	
	public GravityDirection getGravityDirection() {
		return direction;
	}
	
	public GravityDirection getNextGravityDirection() {
		return nextDirection;
	}
	
	public void setGravityDirection(GravityDirection direction) {
		this.direction = direction;
	}
}
