package com.tshcmiller.grapple.game;

import org.lwjgl.input.Keyboard;

import com.tshcmiller.grapple.Renderable;
import com.tshcmiller.grapple.world.World;

public class Game implements Renderable {
	
	private World world;
	public boolean isPaused;
		
	public Game() {
		world = new World();
		isPaused = false;
	}
	
	public void togglePause() {
		isPaused = !isPaused;
	}
	
	public void update(int delta) {
		while (Keyboard.next()) {
			if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE && Keyboard.getEventKeyState()) {
				togglePause();
			}
		}
		
		if (!isPaused) {
			world.update(delta);
		}
	}
	
	public void render() {
		world.render();
	}
	
	public World getWorld() {
		return world;
	}
}