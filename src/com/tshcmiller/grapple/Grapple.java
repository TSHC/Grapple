package com.tshcmiller.grapple;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * The Grapple class serves as the base object that starts
 * the game.
 * 
 * @author TSHC
 */
public final class Grapple {

	public static final Dimension SIZE = Toolkit.getDefaultToolkit().getScreenSize();

	public static Grapple instance;
	
	private Game game; //The game to be played
	private GrappleLauncher launcher; //The launcher for this game
	private JFrame frame; //The window that the game will be played on
//	private Image img = new ImageIcon("res/img/test-icon.png").getImage();

	private Grapple() {
		game = new Game();
		launcher = new GrappleLauncher();
		frame = new JFrame();
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setPreferredSize(new Dimension(SIZE)); //Worry about res later
		frame.pack();
		frame.add(game);
		startGame();
	}
	
	public static void main(String[] args) {
		Grapple grapple = new Grapple();
		Grapple.instance = grapple;
//		grapple.createLauncher(); //-> use this line to start up from the launcher
		grapple.initUI(); //-> use this line to start up from the game
	}
	
	public void quitLauncher() {
		frame.dispose(); //get rid of the launcher frame
		initUI(); //create a new frame
	}
	
	public void startGame() {
		frame.setVisible(true);
		game.getThread().start();
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
}
