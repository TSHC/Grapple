package com.tshcmiller.grapple.entity;

import com.tshcmiller.grapple.inventory.Inventory;
import com.tshcmiller.grapple.world.World;

public abstract class Ship extends Entity {
	
	protected Inventory inventory;
	
	public Ship(World world, float x, float y) {
		super(world, x, y, 1350f, "res/textures/default-ship.png");	
		
		this.inventory = new Inventory(5);
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
}
