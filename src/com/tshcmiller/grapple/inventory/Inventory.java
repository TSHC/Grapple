package com.tshcmiller.grapple.inventory;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;

import com.tshcmiller.grapple.Grapple;
import com.tshcmiller.grapple.Renderable;
import com.tshcmiller.grapple.util.Loader;
import com.tshcmiller.grapple.util.Renderer;

public class Inventory implements Renderable {
	
	private List<Item> items;
	private Texture slot;
	
	private int slots;
	
	public Inventory() {
		this(5);
	}
	
	public Inventory(int slots) {
		this.slots = slots;
		this.items = new ArrayList<Item>();
		this.slot = new Loader("res/textures/inv-slot-32.png").getTexture();
	}
	
	public boolean addItem(Item item) {
		if (isFull()) {
			return false;
		}
		
		return items.add(item);
	}
	
	public void clear() {
		items.clear();
	}
	
	public boolean isEmpty() {
		return (items.size() == 0);
	}
	
	public boolean isFull() {
		return (items.size() == slots);
	}
	
	public Item removeItem(int slot) {
		return items.remove(slot);
	}
	
	public Item getItem(int slot) {
		return items.get(slot);
	}
	
	@Override
	public void render() {
		float x = 2 * (Grapple.width / slots);
		float y = 7 * (Grapple.height / 8);
		
		for (int i = 0; i < slots; i++) {
			Renderer.renderQuadTexture(slot, x, y);
			x += slot.getImageWidth();
		}
		
		x = 2 * (Grapple.width / slots);
		
		if (!isEmpty()) {
			for (int i = 0; i < items.size(); i++) {
				Renderer.renderQuadTexture(items.get(i).getTexture(), x, y);
				x += slot.getImageWidth();
			}
		}
	}
	
}
