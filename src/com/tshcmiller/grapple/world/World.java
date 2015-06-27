package com.tshcmiller.grapple.world;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;

import com.tshcmiller.grapple.Grapple;
import com.tshcmiller.grapple.Renderable;
import com.tshcmiller.grapple.entity.EnemyShip;
import com.tshcmiller.grapple.entity.Entity;
import com.tshcmiller.grapple.entity.PlayerShip;
import com.tshcmiller.grapple.inventory.GravityBall;
import com.tshcmiller.grapple.util.Loader;
import com.tshcmiller.grapple.util.Renderer;
import com.tshcmiller.grapple.util.Timer;

public class World implements Renderable {
	
	public static final float TERMINAL_VELOCITY = 4.25f;
	public static Set<Entity> bufferEntities = new HashSet<Entity>();
	
	private List<Entity> entities; //The entities that are in this world
	private PlayerShip ship; //The player ship
	private EnemyShip enemy;
	private Gravity gravity;
	private Timer timer;
	
	public World() {
		this.entities = new ArrayList<Entity>();
		this.ship = new PlayerShip(this, 100, Grapple.height >> 1);
		this.enemy = new EnemyShip(this, 800, Grapple.height >> 1);
		this.entities.add(ship);
		this.entities.add(enemy);
		this.gravity = new Gravity(GravityDirection.PULL_DOWNWARDS, (1f / 32f));
		this.timer = new Timer(15);
		this.timer.start();
	}
	
	//Load a world from a file
	public World(World world) {
		this.entities = world.entities;
		this.gravity = world.gravity;
	}

	public void dropRandomEntity() {
		if (!flag) 
			return;
		
		float x =  0;
		float y =  0;
		float xa = 0;
		float ya = 0;
		
		//TODO better implementation to this
		switch (gravity.getGravityDirection()) {
			case PULL_DOWNWARDS:
				x = (float) (Math.random() * Grapple.width);
				y = Grapple.height;
				xa = 0.25f;
				ya = -6.25f;
			break;
				
			case PULL_UPWARDS:
				x = (float)(Math.random() * Grapple.width);
				xa = 0.25f;
				ya = 6.25f;
			break;
				
			case PULL_LEFTWARDS:
				y = (float) (Math.random() * Grapple.height);
				xa = 6.25f;
				ya = 0.25f;
			break;
				
			case PULL_RIGHTWARDS:
				x = Grapple.width;
				y = (float)(Math.random() * Grapple.height);
				xa = -6.25f;
				ya = 0.25f;
			break;
				
			default: break;
		}

		GravityBall ball = new GravityBall(this, x, y);
		ball.launchFrom(x, y, xa, ya);
		
		entities.add(ball);
		flag = false;
	}
	
	boolean flag = true;
	
	public void update(int delta) {
		if (Entity.entitiesToDelete.size() > 0) {
			entities.removeAll(Entity.entitiesToDelete);
			Entity.entitiesToDelete.clear();
		}
		
		if (bufferEntities.size() > 0) {
			entities.addAll(bufferEntities);
			bufferEntities.clear();
		}
				
		if (Timer.seconds % 5 == 0) {
			if (flag)
				dropRandomEntity();
		} else {
			flag = true;
		}
		
		if (timer.hasExpired()) {
			gravity.changeDirection();
			timer.setTime(timer.getRandomDelay(5, 15));
		}
		
		for (Entity e : entities) {
			e.update(delta);
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			float health = ship.getHealth();
			ship.setHealth(health - 1);
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			gravity.setGravityDirection(GravityDirection.PULL_DOWNWARDS);
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			gravity.setGravityDirection(GravityDirection.PULL_LEFTWARDS);
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			gravity.setGravityDirection(GravityDirection.PULL_RIGHTWARDS);
		}
	}
	
	public void render() {
		Texture tex = new Loader("res/textures/lab-tile.png").getTexture();
		
		for (float x = 0; x < Grapple.width; x += 64) {
			for (float y = 0; y < Grapple.height; y += 64) {
				Renderer.renderQuadTexture(tex, 0, x, y);
			}
		}
		
		if (timer.secondsLeft() <= 5) {
			String message = "Gravity changing to " + gravity.getNextGravityDirection() + " in " + timer.secondsLeft();			
			Renderer.renderCardText((Grapple.width >> 1) - 250, 150, 423, 45, new Color(1f, 1f, 1f, 0.9f), Color.cyan, message);
		}
							
		for (Entity e : entities) {
			e.render();
		}
	}

	public List<Entity> getEntities() {
		return entities;
	}
	
	public Gravity getGravity() {
		return gravity;
	}
}
