package com.tshcmiller.grapple;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import com.tshcmiller.grapple.sound.Sound;
import com.tshcmiller.grapple.sound.Sounds;

public class GrappleLauncherListener implements KeyListener, MouseListener, MouseMotionListener {

	private GrappleLauncher launcher; //The launcher that this object listens for
	
	public GrappleLauncherListener(GrappleLauncher launcher) {
		this.launcher = launcher;
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if (y > 235 && y < 270) {
			if (launcher.indexSelected != 0) {
				Sound.play(Sounds.LAUNCHER_SCROLL);
			}
			
			launcher.indexSelected = 0;
		}
		
		if (y > 270 && y < 290) {
			if (launcher.indexSelected != 1) {
				Sound.play(Sounds.LAUNCHER_SCROLL);
			}
			
			launcher.indexSelected = 1;
		}
		
		if (y > 290 && y < 310) {
			if (launcher.indexSelected != 2) {
				Sound.play(Sounds.LAUNCHER_SCROLL);
			}
			
			launcher.indexSelected = 2;
		}
		
		if (y > 310 && y < 375) {
			if (launcher.indexSelected != 3) {
				Sound.play(Sounds.LAUNCHER_SCROLL);
			}
			
			launcher.indexSelected = 3;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		launcher.select(launcher.indexSelected);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int keycode = e.getKeyCode();
		int length = launcher.options.length - 1;
		
		if (keycode == KeyEvent.VK_UP) {
			launcher.indexSelected = (--launcher.indexSelected < 0) ? length : launcher.indexSelected;
			Sound.play(Sounds.LAUNCHER_SCROLL);
		}
		
		if (keycode == KeyEvent.VK_DOWN) {
			launcher.indexSelected = (++launcher.indexSelected > length) ? 0 : launcher.indexSelected;
			Sound.play(Sounds.LAUNCHER_SCROLL);
		}
		
		if (keycode == KeyEvent.VK_ENTER) {
			launcher.select(launcher.indexSelected);
//			Sound.play(Sounds.LAUNCHER_SELECT);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
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
