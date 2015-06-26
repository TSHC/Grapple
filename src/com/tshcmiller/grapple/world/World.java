package com.tshcmiller.grapple.world;

import java.util.ArrayList;
import java.util.List;

import com.tshcmiller.grapple.Grapple;
import com.tshcmiller.grapple.Renderable;
import com.tshcmiller.grapple.entity.Entity;
import com.tshcmiller.grapple.entity.GravityBall;
import com.tshcmiller.grapple.entity.PlayerShip;
import com.tshcmiller.grapple.util.Renderer;
import com.tshcmiller.grapple.util.Timer;

public class World implements Renderable {
	
	public static final float TERMINAL_VELOCITY = 4.25f;
	
	private List<Entity> entities; //The entities that are in this world
	private Gravity gravity;
	private Timer timer;
	
	private int maxdelay = 15;
	private int mindelay = 5;
	
	public World() {
		this.entities = new ArrayList<Entity>();
		this.entities.add(new PlayerShip(this, 400, 400));
		this.gravity = new Gravity(GravityDirection.PULL_DOWNWARDS, (1f / 32f));
		this.timer = new Timer(0);
		this.timer.addRandomDelay(mindelay, maxdelay);
	}
	
	//Load a world from a file
	public World(World world) {
		this.entities = world.entities;
		this.gravity = world.gravity;
	}
	
	public void dropRandomEntity() {
		if (!flag) 
			return;
		
			float x = (float)(Math.random() * Grapple.width); 
			float y = (float)(Math.random() * Grapple.height);
		
			switch (gravity.getGravityDirection()) {
				case PULL_UPWARDS:
					y = Grapple.height;
				break;
				
				case PULL_DOWNWARDS:
					y = 0;
				break;
				
				case PULL_LEFTWARDS:
					x = Grapple.width;
				break;
				
				case PULL_RIGHTWARDS:
					x = 0;
				break;
				
				default:
					break;
			}
			
			entities.add(new GravityBall(this, x, y));
			flag = false;
	}
	
	boolean flag = true;
	
	public void update(int delta) {
		if (Entity.entitiesToDelete.size() > 0) {
			entities.removeAll(Entity.entitiesToDelete);
			Entity.entitiesToDelete.clear();
		}
		
		System.out.println(Grapple.getTime());
		
		if (Timer.seconds % 5 == 0 && flag) {
			dropRandomEntity();
		} 
		
		if (Timer.seconds % 5 != 0) {
			flag = true;
		}
		
		if (timer.hasExpired()) {
			gravity.changeDirection();
			timer.addRandomDelay(mindelay, maxdelay);
		}
		
		for (Entity e : entities) {
			gravity.apply(e);
			e.update(delta);
		}
	}
	
	public void render() {
//		if (timer.getSecondsLeft() <= 5) {
			Renderer.drawString((Grapple.width >> 1) - 250, 100, "Gravity changing to " + gravity.getNextGravityDirection() + " in " + timer.getSecondsLeft());
//		}
			
			Renderer.drawString(10, 25, "Entities: " + entities.size());
		
		for (Entity e : entities) {
			e.render();
		}
	}

	public List<Entity> getEntities() {
		return entities;
	}
}
