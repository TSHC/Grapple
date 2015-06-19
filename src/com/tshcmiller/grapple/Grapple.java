package com.tshcmiller.grapple;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

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
	
	private void createLauncher() {
		frame.setUndecorated(true);
		frame.setSize(new Dimension(600, 360));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(launcher);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
//		frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon("res/img/cursors/default-cursor.cur").getImage(), new Point(0, 0), "default"));
		frame.setVisible(true);
		
		launcher.run();
	}
	
	private void initUI() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setPreferredSize(new Dimension(prefSize)); //Worry about res later
		frame.pack();
		frame.add(game);
		frame.setIconImage(img);
	}
	
	public static void main(String[] args) {
		Grapple grapple = new Grapple();
		Grapple.instance = grapple;
		grapple.createLauncher();
	}
	
	public void quitLauncher() {
		frame.dispose(); //get rid of the launcher frame
		initUI(); //create a new frame
		//load resources (this will be later
		thread.start(); //start the game
	}
	
	public void run() {
		
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
}
