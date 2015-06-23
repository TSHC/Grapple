package com.tshcmiller.grapple;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.tshcmiller.grapple.entity.Ship;


public class Grapple {
	
	public static final String TITLE = "Grapple 0.1.9";

	public static final int WIDTH = 1360;
	public static final int HEIGHT = 730;
	
	private boolean running;
	private long lastFrame;
	private long lastFPS;
	private int FPS;
	private int targetFPS;
	
	Ship ship;
	
	public Grapple() {
		running = false;
		lastFrame = 0;
		FPS = 0;
		lastFPS = 0;
		targetFPS = 60;
	}
	
	private void initGL() {
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	private void start() {
		running = true;
		
		try {
			Display.setTitle(TITLE);
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));	
			Display.create();
		} catch (LWJGLException e) {
			System.out.println("Window creation failed.");
			e.printStackTrace();
			System.exit(0);
		}
		
		ship = new Ship(50, 50);
		lastFPS = getTime();
		initGL();
		run();
	}
	
	public static void main(String[] args) {
		Grapple grapple = new Grapple();
		grapple.start();
	}
	
	public void run() {
		while (running) {
			if (Display.isCloseRequested()) {
				running = false;
				break;
			}
			
			int delta = getDelta();
			
			update(delta);
			render();
			
			Display.update();
			Display.sync(targetFPS);
		}
		
		Display.destroy();
	}
	
	public void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

		ship.render();
	}
	
	public void update(int delta) {
		ship.update(delta);
		calculateFPS();
	}
	
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		
		return delta;
	}
	
	public int calculateFPS() {
		if (getTime() - lastFPS > 1000) {
			FPS = 0;
			lastFPS += 1000;
		}
		
		return ++FPS;
	}
	
	public long getTime() {
		return System.nanoTime() / 1000000;
	}

}