package com.tshcmiller.grapple;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GrappleLauncher extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;
	
	private String[] options;
	private int currentIndex = 0;
	
	public GrappleLauncher() {
		options = new String[] {
				"Single Player",
				"Multi Player",
				"Options",
				"Exit"
		};
		
		setFocusable(true);
		addKeyListener(this);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		int x = 100;
		int y = 100;
		
		g.setFont(new Font("Courier", Font.PLAIN, 14));
		String selected = "> " + options[currentIndex];
		
		for (int i = 0; i < options.length; i++) {
			if (currentIndex == i)
				g.drawString(selected, x, y + (i * 25));
			else
				g.drawString(options[i], x, y + (i * 25));
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		
		if (keycode == KeyEvent.VK_UP) {
			currentIndex = --currentIndex < 0 ? 0 : currentIndex;
		} 
		else if (keycode == KeyEvent.VK_DOWN) {
			currentIndex = ++currentIndex >= options.length ? options.length - 1 : currentIndex;
		}
		
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {}
}
