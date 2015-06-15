package com.tshcmiller.grapple;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * The Grapple class serves as the base object that starts
 * the game.
 * 
 * @author TSHC
 */
public final class Grapple implements Runnable {

	public static Grapple instance;
	
	private Dimension prefSize; //The preferred size of the window
	private Game game; //The game to be played
	private GrappleLauncher launcher; //The launcher for this game
	private JFrame frame; //The window that the game will be played on
	private Thread thread; //The main logic thread
	private Image img = new ImageIcon("res/img/test-icon.png").getImage();


	
	private Grapple() {
		prefSize = Toolkit.getDefaultToolkit().getScreenSize();
		game = new Game();
		launcher = new GrappleLauncher();
		frame = new JFrame();
		thread = new Thread(this, "Main Thread");
	}
	
	public static void main(String[] args) {
		Grapple grapple = new Grapple();
		Grapple.instance = grapple;
		grapple.createLauncher();
		
		while (true) {
			grapple.frame.repaint();
			
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void createLauncher() {
		frame.setUndecorated(true);
		frame.setSize(new Dimension(600, 360));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(launcher);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
//		frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("res/img/cursors/default-cursor.cur").getImage(), new Point(0, 0), "default"));
		frame.setVisible(true);
	}
	
	private void initUI() {
		
		frame.setUndecorated(true);
		frame.setPreferredSize(new Dimension(prefSize)); //Worry about res later
		frame.pack();
		frame.add(game);
		frame.setIconImage(img);
	}
	
	public void run() {
		
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
}
