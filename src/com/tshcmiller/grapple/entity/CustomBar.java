package com.tshcmiller.grapple.entity;

import org.newdawn.slick.Color;

import com.tshcmiller.grapple.Renderable;
import com.tshcmiller.grapple.util.Renderer;
import com.tshcmiller.grapple.world.World;

public class CustomBar extends Entity implements Renderable {
	
	private Ship ship;
	
	private float barFilled;
	
	public CustomBar(World world, Ship ship, float x, float y) {
		super(world, x, y, "res/textures/healthbar-empty.png");
		this.ship = ship;
		this.barFilled = ship.getHealth();
	}
	
	@Override
	public void update(int delta) {
		this.x = ship.getX();
		this.y = ship.getY() + ship.getTexture().getImageHeight();
		this.barFilled = ship.getHealth();
		
		if (barFilled < 0) {
			barFilled = 0;
		}
	}
	
	public void render() {
		Renderer.renderQuadTexture(texture, 0, x, y);
		Renderer.renderQuad(new Color(0, 1f, 0, 1f), x, y, barFilled, 8);
	}

	
}
