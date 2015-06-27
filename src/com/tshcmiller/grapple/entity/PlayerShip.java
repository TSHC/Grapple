package com.tshcmiller.grapple.entity;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

import com.tshcmiller.grapple.Grapple;
import com.tshcmiller.grapple.inventory.Item;
import com.tshcmiller.grapple.util.Renderer;
import com.tshcmiller.grapple.util.Timer;
import com.tshcmiller.grapple.world.World;

public class PlayerShip extends Ship {
	
	private Timer cooldown;
	public CustomBar healthbar;
	
	public PlayerShip(World world, float x, float y) {
		super(world, x, y);
		
		cooldown = new Timer(5);
		this.healthbar = new CustomBar(world, this, x, y + texture.getImageHeight());
	}
	
	private void handleCollisions() {
		for (Entity e : world.getEntities()) {
			if (e == this)
				continue;
			
			if (this.isColliding(e) && e instanceof Item) {
				Item item = (Item) e;
				if (!inventory.isFull() && item.canBePickedUp()) {
					item.addToInventory(inventory);
					e.addForDeletion();
				}
			}
		}
	}
	
	private void processInput() {
		float multiplier = 1;
		float acceleration = multiplier * (1f / 16f);
		
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			ya -= acceleration;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			ya += acceleration;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			xa -= acceleration;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			xa += acceleration;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			if (cooldown.isRunning() && !cooldown.hasExpired()) {
				return;
			}
			
			if (!inventory.isEmpty()) {
				Entity e = (Entity) inventory.removeItem(0);
				if (e instanceof Projectile) {
					World.bufferEntities.add(e);
					cooldown.start();
					((Projectile) e).fire(this);
				}
			}
		}
	}
	
	//0.125 * 0.0625 (MIN) ( 1/8)
	//1.500 * 0.0625 (MAX) (12/8)
	public void update(int delta) {
		super.update();
		
		if (cooldown.isRunning()) {
			if (cooldown.hasExpired()) {
				cooldown.stop();
			}
		}
		
		healthbar.update(delta);
		
		processInput();
		move();
		handleCollisions();
	}
	
	public void render() {
		super.render();
		
		inventory.render();
		healthbar.render();
		
		if (cooldown.isRunning()) {
			float x = 2 * (Grapple.width / 5);
			float y = 6 * (Grapple.height / 8);
			
			Renderer.renderHeaderText(x, y, "Able to fire in: " + cooldown.secondsLeft(), Color.red);
		}
	}
}
