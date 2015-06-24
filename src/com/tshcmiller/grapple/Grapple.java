package com.tshcmiller.grapple;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

public class Grapple {
	
	public static final String TITLE = "Grapple 0.1.9";

	public static int width = 1200;
	public static int height = 700;
	
	private boolean running;
	private long lastFrame;
	private long lastFPS;
	private int FPS;
	private int targetFPS;
	private int currentFPS;
	
	private Game game;
		
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

		GL11.glViewport(0, 0, width, height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	private void start() {
		running = true;
		
		try {
			Display.setTitle(TITLE);
			
			if (Settings.settings.useFullScreen) {
				Display.setFullscreen(true);
				width = Display.getWidth();
				height = Display.getHeight();
			} else {
				width = Settings.settings.prefWidth;
				height = Settings.settings.prefHeight;
				Display.setDisplayMode(new DisplayMode(width, height));	
			}
			
			Display.create();
		} catch (LWJGLException e) {
			System.out.println("Window creation failed.");
			e.printStackTrace();
			System.exit(0);
		}
		
		game = new Game();
		lastFPS = getTime();
		initGL();		
		run();
	}
	
	public void stop() {
		//save...
		Settings.save();
		Display.destroy();
		AL.destroy();
		System.exit(0);
	}
	
	public static void main(String[] args) {
		Settings.load();
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
			updateTimers();
			calculateFPS();
			
			Display.update();
			Display.sync(targetFPS);
		}
		
		stop();
	}
	
	public void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		game.getWorld().render();
		
		if (Settings.settings.showDevOptions)
			game.contl.drawString(10, 10, "FPS: " + currentFPS, Color.cyan);
	}
	
	public void update(int delta) {
		game.getWorld().update(delta);	
		
		if (Keyboard.isKeyDown(Keyboard.KEY_MINUS)) {
			Settings.settings.playSound = !Settings.settings.playSound;
		}
		
		if (Keyboard.isKeyDown(Keyboard.KEY_F2)) {
			Settings.settings.showDevOptions = !Settings.settings.showDevOptions;
		}
	}
	
	public void updateTimers() {
		game.getWorld().updateTimer();
	}
	
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		
		return delta;
	}
	
	public void calculateFPS() {
		if (getTime() - lastFPS > 1000) {
			currentFPS = FPS;
			FPS = 0;
			lastFPS += 1000;
		}
		
		++FPS;
	}
	
	public static long getTime() {
		return System.nanoTime() / 1000000;
	}
	
	public static long getSeconds() {
		return getTime() / 1000;
	}

}