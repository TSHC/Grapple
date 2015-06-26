package com.tshcmiller.grapple.entity;

public interface Item {
	
	public void useItem(Entity e);
	public boolean isStackable();
	public int getMaxStackSize();
	
}
