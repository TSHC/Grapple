package com.tshcmiller.grapple.world;

import static org.lwjgl.opengl.GL11.*;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.Color;

import com.tshcmiller.grapple.Game;
import com.tshcmiller.grapple.Grapple;
import com.tshcmiller.grapple.Renderable;
import com.tshcmiller.grapple.entity.Entity;
import com.tshcmiller.grapple.entity.GravityBall;
import com.tshcmiller.grapple.entity.Ship;

public class World implements Renderable {
		
	private enum GravityState { UP, DOWN, LEFT, RIGHT };
	
	private GravityState gravityState;
	private GravityState nextState;
	private List<Entity> entities;
	private Ship player;
	private Game game;
	
	private float gravity;
	private long targetTime;
	
	public World(Game game, float gravity) {
		this.gravityState = GravityState.DOWN;
		this.entities = new ArrayList<Entity>();
		this.player = new Ship(this, 50, 50);
		this.game = game;

		this.gravity = gravity;
		this.targetTime = Grapple.getSeconds() + 10 + ((long) (Math.random() * 15));
		
		setNextGravityState();
		
/* 
        >>Used for gravity testing purposes<<
  		float x, y;
		
		for (int i = 0; i < 15; i++) {
			x = (float)(Math.random() * 1000);
			y = (float)(Math.random() * 600);
			
			entities.add(new GravityBall(this, x, y));
		}
*/		
		entities.add(player);
	}
	
	public void alterGravity() {
		gravityState = nextState;

		boolean a = (gravity < 0 && (gravityState == GravityState.DOWN || gravityState == GravityState.RIGHT));
		boolean b = (gravity > 0 && (gravityState == GravityState.UP || gravityState == GravityState.LEFT));
		
		if (a || b) {
			gravity *= -1;
		}
		
		setNextGravityState();
	}
	
	public void update(int delta) {
		updateTimer();
		
		List<Entity> del = new ArrayList<Entity>();
		
		for (Entity e : entities) {
			if (!e.isVisible()) {
				del.add(e);
			}
			
			if (gravityState == GravityState.UP || gravityState == GravityState.DOWN)
				e.applyVerticalGravity(gravity);
			else
				e.applyHorizontalGravity(gravity);
			e.update(delta);
		}
				
		entities.removeAll(del);
	}
	
	public void render() {
		for (Entity e : entities) {
			e.render();
		}
	}
	
	public void updateTimer() {
		if (Grapple.getSeconds() + 5 >= targetTime) {
			int seconds = (int) (targetTime - Grapple.getSeconds());
			
			float x = (Grapple.WIDTH >> 1) - 250;
			float y = 50;
			
			game.contl.drawString(x, y, "Gravity changing to " + nextState + " in " + seconds + " seconds!", Color.cyan);
		}
		
		if (Grapple.getSeconds() >= targetTime) {
			targetTime = Grapple.getSeconds() + (15 + (1 + ((long) (Math.random() * 15)))); //range: 45 - 60 seconds
			alterGravity();
		}		
	}
	
	public GravityState getNextGravityState() {
		return nextState;
	}
	
	public void setNextGravityState() {
		GravityState nextGravityState;
		GravityState currentGravityState = gravityState;
		
		do {
			nextGravityState = GravityState.values()[((int) (Math.random() * GravityState.values().length))];
		} while (nextGravityState == currentGravityState);
		
		nextState = nextGravityState;
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public Ship getPlayer() {
		return player;
	}
	
	public float getGravity() {
		return gravity;
	}

}
