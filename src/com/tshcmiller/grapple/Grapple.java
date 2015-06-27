package com.tshcmiller.grapple;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.openal.AL;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import com.tshcmiller.grapple.game.Game;
import com.tshcmiller.grapple.game.GameState;
import com.tshcmiller.grapple.util.Renderer;
import com.tshcmiller.grapple.util.Timer;

public class Grapple {
	
	public static final String TITLE = "Grapple 0.1.9";
	public static GameState state = GameState.LAUNCHER;

	public static int width = 1200;
	public static int height = 700;
	
	private boolean running;
	private long lastFrame;
	private long lastFPS;
	private int FPS;
	private int currentFPS;
	
	private Game game;
		
	public Grapple() {
		running = false;
		lastFrame = 0;
		FPS = 0;
		lastFPS = 0;
	}
	
	private void initGL() {
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
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
		Display.update();
		
		while (running) {
			if (Display.isCloseRequested()) {
				running = false;
				break;
			}
						
			int delta = getDelta();
			
			update(delta);
			render();
			calculateFPS();
			
			Display.update();
			Display.sync(60);
		}
		
		stop();
	}
	
	public void render() {
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		Renderer.renderNormalText(10, 10, "FPS: " + currentFPS, Color.cyan);
		game.render();
	}
	
	public void update(int delta) {
		game.update(delta);
		
		while (Keyboard.next())
		if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE && Keyboard.getEventKeyState()) {
			System.out.println("paused");
		}
	}
	
	public int getDelta() {
		long time = getTime();
		int delta = (int) (time - lastFrame);
		lastFrame = time;
		
		return delta;
	}
	
	public void calculateFPS() {
		if (getTime() - lastFPS > 1000) {
			Timer.update(game);
			currentFPS = FPS;
			FPS = 0;
			lastFPS += 1000;
		}
		
		++FPS;
	}
	
	public static long getTime() {
		return System.nanoTime() / 1000000;
	}
}