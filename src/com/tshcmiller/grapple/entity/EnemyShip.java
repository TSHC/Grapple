package com.tshcmiller.grapple.entity;

import com.tshcmiller.grapple.Renderable;
import com.tshcmiller.grapple.world.World;

public class EnemyShip extends Ship implements Renderable {
	
	private CustomBar healthbar;

	public EnemyShip(World world, float x, float y) {
		super(world, x, y);
		
		this.healthbar = new CustomBar(world, this, x, y + texture.getImageHeight());
	}
	
	@Override
	public void update(int delta) {
		for (Entity e : world.getEntities()) {
			if (e == this)
				continue;
			
			if (this.isColliding(e) && e instanceof Projectile) {
				((Projectile) e).hit(this);
			}
		}
		
		healthbar.update(delta);
	}
	
	public void render() {
		super.render();
		
		healthbar.render();
	}

}
