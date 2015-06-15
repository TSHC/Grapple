package com.tshcmiller.grapple;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Game extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private boolean running;
	
	public Game() {
		running = false; //Do not start the game yet
	}
	
	public void startGame() {
		if (running) {
			JOptionPane.showMessageDialog(null, "An instance of Grapple is already running!");
			return;
		}
		
		running = true;
	}
	
	public void paint(Graphics g) {
		if (!running)
			return;
		
		super.paint(g);
		
		Graphics2D g2 = (Graphics2D) g;
		g2.drawString("Grapple!", 150, 150);
	}

}
