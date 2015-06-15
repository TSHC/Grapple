package com.tshcmiller.grapple;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import com.tshcmiller.grapple.sound.Sound;
import com.tshcmiller.grapple.sound.Sounds;

public class GrappleLauncher extends JPanel implements KeyListener, MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;
	
	private String[] options;
	private int defaultIndex = 0;
	private int mouseIndex = -1;
	
	public GrappleLauncher() {
		options = new String[] {
				"Single Player",
				"Multi Player",
				"Options",
				"Exit"
		};
		
		setFocusable(true);
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	
	public void select(int index) {
		switch (index) {
			case 0:
				System.out.println("SinglePlayer");
			break;
			
			case 1:
				System.out.println("MultiPlayer");
			break;
			
			case 2:
				System.out.println("Options");
				Sound.playSound = !Sound.playSound;
			break;
				
			case 3:
				System.exit(0);
			break;
		}
	}
	
	//Inherited Methods
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		int x = 25;
		int y = 225;
		
		g.setFont(new Font("Constantia", Font.PLAIN, 16));		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		defaultIndex = mouseIndex == -1 ? defaultIndex : mouseIndex;
		String selected = options[defaultIndex];
		
		for (int i = 0; i < options.length; i++) {
			if (defaultIndex == i) {
				g.setColor(Color.WHITE);
				g.drawString("> ", x, y + (i * 25));
				g.setFont(new Font("Constantia", Font.BOLD, 16));		
				g.setColor(Color.CYAN);
				g.drawString(selected, x + 12, y + (i * 25));
			}
			else {
				g.setFont(new Font("Constantia", Font.PLAIN, 16));		
				g.setColor(Color.CYAN.darker().darker());
				g.drawString(options[i], x, y + (i * 25));
			}
		}
		
		g.setColor(Color.CYAN);
		g.setFont(new Font("Razer Header Regular", Font.PLAIN, 36));
		g.drawString("Grapple!", (getWidth() >> 1) - 75, 60);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		int max = options.length - 1;
		
		if (keycode == KeyEvent.VK_UP) {
			Sound.play(Sounds.LAUNCHER_SELECT);
			defaultIndex = --defaultIndex < 0 ? max : defaultIndex;
		} 
		else if (keycode == KeyEvent.VK_DOWN) {
			Sound.play(Sounds.LAUNCHER_SELECT);
			defaultIndex = ++defaultIndex >= options.length ? 0 : defaultIndex;
		}
		else if (keycode == KeyEvent.VK_ENTER) {
			select(defaultIndex);
		}
		
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}


	@Override
	public void mouseDragged(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		System.out.printf("x: %d, y: %d%n", x, y);
		
		if (y > 211 && y < 227) {
			if (mouseIndex != 0 && defaultIndex != 0)
				Sound.play(Sounds.LAUNCHER_SELECT);
			
			mouseIndex = 0;
		}
		else if (y > 232 && y < 252) {
			if (mouseIndex != 1  && defaultIndex != 1)
				Sound.play(Sounds.LAUNCHER_SELECT);
			
			mouseIndex = 1;
		}
		else if (y > 257 && y < 277) {
			if (mouseIndex != 2 && defaultIndex != 2)
				Sound.play(Sounds.LAUNCHER_SELECT);
			
			mouseIndex = 2;
		}
		else if (y > 283 && y < 302) {
			if (mouseIndex != 3 && defaultIndex != 3)
				Sound.play(Sounds.LAUNCHER_SELECT);
			
			mouseIndex = 3;
		}
		else {
			mouseIndex = -1;
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (mouseIndex == -1)
			return;
		
		select(mouseIndex);		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}
}
