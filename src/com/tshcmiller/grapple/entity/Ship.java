package com.tshcmiller.grapple.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;

import com.tshcmiller.grapple.Fonts;

public class Ship extends Entity {

	private ShipListener listener;
	
	public Ship(double x, double y) {
		super(x, y);
		
		this.listener = new ShipListener(this);
	}
	
	public void keyPressed(List<Integer> list) {
		double amount = 25.0;
		
		if (list.contains(KeyEvent.VK_W)) {
			forces.applyVerticalForce(-amount);
		}
		
		if (list.contains(KeyEvent.VK_S)) {
			forces.applyVerticalForce(amount);
		}
		
		if (list.contains(KeyEvent.VK_A)) {
			forces.applyHorizontalForce(-amount);
		}
		
		if (list.contains(KeyEvent.VK_D)) {
			forces.applyHorizontalForce(amount);
		}
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, 60, 60);
		
		g.setFont(Fonts.contl);
		g.drawString(String.format("Horizontal Force: %.2f", forces.getHorizontalForce()), 68, 635);
		g.drawString(String.format("Vertical Force: %.2f", forces.getVerticalForce()), 68, 660);
	}

	public ShipListener getListener() {
		return listener;
	}
}
