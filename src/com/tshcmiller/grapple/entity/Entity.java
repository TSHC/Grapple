package com.tshcmiller.grapple.entity;

import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.tshcmiller.grapple.Renderable;

public abstract class Entity implements Renderable {
	
	protected Texture texture;
	
	protected float x;
	protected float y;
	
	public Entity(String imgPath, float x, float y) {
		this.x = x;
		this.y = y;
		
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(imgPath));
		} catch (IOException e) {
			System.out.println("Unable to load image at: " + imgPath);
			e.printStackTrace();
		}
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
}
