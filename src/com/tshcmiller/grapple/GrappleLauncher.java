package com.tshcmiller.grapple;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;

public class GrappleLauncher extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Font contb;
	private Font contl;
	private GrappleLauncherListener listener;

	protected int indexSelected; //What index is currently selected
	protected boolean running; //If the launcher is active or not
	protected String[] options; //Options on screen
	
	private long ticks; //How many updates this launcher has had
	
	public GrappleLauncher() {
		this.listener = new GrappleLauncherListener(this);
		this.running = false;
		this.indexSelected = 0;
		this.options = new String[] {
			"Start New Game",
			"Resume Game",
			"Options",
			"Exit"
		};
		
		loadFonts();
		
		setFocusable(true);
		addKeyListener(listener);
		addMouseListener(listener);
		addMouseMotionListener(listener);
	}
	
	private void loadFonts() {
		try {
			GraphicsEnvironment graphics = GraphicsEnvironment.getLocalGraphicsEnvironment();
			
			contb = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/contb.ttf"));
			contb = contb.deriveFont(Font.PLAIN, 48f);
			contl = Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/contl.ttf"));
			contl = contl.deriveFont(Font.PLAIN, 18f);
			
			graphics.registerFont(contb);
		} catch (FontFormatException | IOException e) {
			System.err.println("Unable to load custom fonts");
			e.printStackTrace();
		}
	}
	
	private void renderBackground(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
	}
	
	private void renderForeground(Graphics2D g) {
		Color selected = Color.CYAN; //Color when an option is selected
		Color unselected = new Color(0x00, 0x88, 0x88); //Color when an option is not selected
		
		//Options
		int x = 25;
		int y = 260;
		int offs = 0;
		
		g.setFont(contl);
		
		for (int i = 0; i < options.length; i++) {
			offs = 25 * i;
			
			if (indexSelected == i) {
				g.setColor(selected);
				g.drawString("| " + options[i], x, y + offs);
			} else {
				g.setColor(unselected);
				g.drawString(options[i], x, y + offs);
			}
		}
		
		
		//Title
		g.setFont(contb);
		g.setColor(selected);
		g.drawString("Grapple", (getWidth() >> 1) - 75, 60);
		
		//Game Information
		g.setFont(contl.deriveFont(12f));
		g.setColor(unselected);
		g.drawString("TSHC", 515, 325);
		g.drawString("Version 0.1.3", 515, 340);
	}
	
	public void run() {
		running = true;
		
		while (running) {
			repaint();
			ticks++;
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Grapple.instance.quitLauncher();
	}
	
	public void select(int index) {
		switch (index) {
			case 0:
				running = false;
			break;
			
			case 1:
				System.out.println("Resuming last save!");
			break;
				
			case 2:
				System.out.println("Options was pressed!");
			break;
			
			case 3:
				System.exit(0);
			break;
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D) g;
		
		ticks++;
		renderBackground(g2); //Render the background
		renderForeground(g2); //Render the Foreground
	}
}