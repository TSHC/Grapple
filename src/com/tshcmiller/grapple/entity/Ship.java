package com.tshcmiller.grapple.entity;

import com.tshcmiller.grapple.inventory.Inventory;
import com.tshcmiller.grapple.world.World;

public abstract class Ship extends MobileEntity {
	
	protected Inventory inventory;
	protected float health;
	
	public Ship(World world, float x, float y) {
		super(world, x, y, "res/textures/default-ship.png");	
		
		this.inventory = new Inventory(5);
		this.health = 64;
	}
	
	public void damage(float damage) {
		this.health -= damage;
	}
	
	public void setHealth(float health) {
		this.health = health;
	}
	
	public float getHealth() {
		return health;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
}
