package com.tshcmiller.grapple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public final class Settings implements Serializable {
	private static final long serialVersionUID = 1L;

	public static Settings settings;
	
	//default settings
	public boolean playSound = true;
	public boolean useFullScreen = true;
	public boolean showDevOptions = false;
	public int targetFPS = 60;
	public int prefWidth = 1200;
	public int prefHeight = 700;
	
	private Settings() {}
	
	public static void save() {
		try {
			File res = new File("res/");
			File file;
			FileOutputStream fos;
			ObjectOutputStream out;
			
			res.mkdirs(); //force to create the file
			
			file = new File(res, "settings.ser");
			fos = new FileOutputStream(file, false);
			out = new ObjectOutputStream(fos);
			
			out.writeObject(settings);
			
			fos.close();
			out.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void load() {
		File target = new File("res/settings.ser");
		
		FileInputStream fis;
		ObjectInputStream ois;
		
		try {
			fis = new FileInputStream(target);
			ois = new ObjectInputStream(fis);
			
			settings = (Settings) ois.readObject();
			
			fis.close();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			settings = new Settings();
		}
	}
	
}
