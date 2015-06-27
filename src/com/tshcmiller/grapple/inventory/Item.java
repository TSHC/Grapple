package com.tshcmiller.grapple.inventory;

import com.tshcmiller.grapple.entity.MobileEntity;
import com.tshcmiller.grapple.world.World;

public abstract class Item extends MobileEntity {
	
	protected boolean canBePickedUp;
	protected boolean isInAnInventory;
	
	public Item(World world, float x, float y, String img) {
		super(world, x, y, img);
		
		this.canBePickedUp = true;
		this.isInAnInventory = false;
	}
	
	public void update() {
		world.getGravity().apply(this);
		this.rot += 1f;
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