package com.tshcmiller.grapple.entity;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class ShipListener implements KeyListener, MouseListener {
	
	private Ship ship;
	private List<Integer> keys;
	
	
	public ShipListener(Ship ship) {
		this.ship = ship;
		this.keys = new ArrayList<Integer>();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		
		if (!keys.contains(keycode)) {
			keys.add(keycode);
		}
		
		ship.keyPressed(keys);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys.remove((Object) e.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
