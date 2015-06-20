package com.tshcmiller.grapple;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.tshcmiller.grapple.entity.Ship;
import com.tshcmiller.grapple.world.World;

public class Game extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
		
	private Thread thread;
	private World world;
	private Ship player;
	
	private boolean running;
	
	public Game() {
		player = new Ship(600, 250);
		world = new World(player);
		running = false;
		thread = new Thread(this, "Main Game Thread");
		
		setFocusable(true);
		addKeyListener(player.getListener());
	}
	
	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		
		Graphics2D g = (Graphics2D) graphics;
		
		world.render(g); //render the world
		player.render(g); //render entities
	}

	@Override
	public void run() {
		
		running = true;
		long ticks = 0;
		
		while (running) {
			//read user input
			player.getForces().applyVerticalForce(1); //move entities
			player.move();
			//handle collisions
			repaint();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Thread getThread() {
		return thread;
	}
}
