package com.tshcmiller.grapple.inventory;

import com.tshcmiller.grapple.entity.Entity;
import com.tshcmiller.grapple.world.World;

public abstract class Item extends Entity {
	
	protected boolean canBePickedUp;
	protected boolean isInAnInventory;
	
	public Item(World world, float x, float y, float mass, String img) {
		super(world, x, y, mass, img);
		
		this.canBePickedUp = true;
		this.isInAnInventory = false;
	}
	
	public void addToInventory(Inventory inventory) {
		this.isInAnInventory = true;
		this.canBePickedUp = false;
		inventory.addItem(this);
	}
	
	public boolean canBePickedUp() {
		return canBePickedUp;
	}
}