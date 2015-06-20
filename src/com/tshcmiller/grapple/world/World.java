package com.tshcmiller.grapple.world;

import java.awt.Color;
import java.awt.Graphics2D;

import com.tshcmiller.grapple.Grapple;
import com.tshcmiller.grapple.Renderable;
import com.tshcmiller.grapple.entity.Ship;

public class World implements Renderable {
	private Environment environment;
	
	private Ship player;
	
	public World(Ship ship) {
		this.player = ship;
	}

	@Override
	public void render(Graphics2D g) {
		int tile_size = 64;
		int width = Grapple.SIZE.width;
		int height = Grapple.SIZE.height;
		
		for (int x = 0; x < width; x += tile_size) {
			 for (int y = 0; y < height; y += tile_size) {
				g.setColor(Color.DARK_GRAY);
				g.fillRect(x, y, tile_size, tile_size);
				g.setColor(Color.GRAY);
				g.drawRect(x, y, tile_size, tile_size);
			 }
		}
	}
}
