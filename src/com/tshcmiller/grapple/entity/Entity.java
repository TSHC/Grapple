package com.tshcmiller.grapple.entity;

import java.awt.Rectangle;
import java.util.HashSet;
import java.util.Set;

import org.newdawn.slick.opengl.Texture;

import com.tshcmiller.grapple.Grapple;
import com.tshcmiller.grapple.Renderable;
import com.tshcmiller.grapple.util.Loader;
import com.tshcmiller.grapple.util.Renderer;
import com.tshcmiller.grapple.world.World;

public abstract class Entity implements Renderable {
	
	public static Set<Entity> entitiesToDelete = new HashSet<Entity>();
	
	protected Texture texture;
	protected World world;
	
	protected float x;
	protected float y;
	protected float xa;
	protected float ya; 
	protected float rot;
	protected float mass;
	protected boolean isVisible;
	
	public Entity(World world, float x, float y, float mass, String img) {
		this.texture = new Loader(img).getTexture();
		this.world = world;
		this.x = x;
		this.y = y;
		this.xa = 0;
		this.ya = 0;
		this.rot = 0;
		this.mass = mass;
		this.isVisible = true;
	}
	
	public abstract void update(int delta);
	
	protected void addForDeletion() {
		entitiesToDelete.add(this);
	}
	
	public void move() {
		y += ya;
		x += xa;
	}
	
	public boolean isColliding(Entity e) {
		Rectangle rect1 = new Rectangle((int) x, (int) y, texture.getImageWidth(), texture.getImageHeight());
		Rectangle rect2 = new Rectangle((int) e.x, (int) e.y, e.texture.getImageWidth(), e.texture.getImageHeight());
		
		return (rect1.intersects(rect2));
	}
	
	public boolean isOutOfBounds() {
		return (x < 0 || x > Grapple.width || y < 0 || y > Grapple.height);
	}
	
	public void render() {
		Renderer.renderQuadTexture(texture, x, y);
	}
	
	public Texture getTexture() {
		return texture;
	}
	
	public World getWorld() {
		return world;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getXA() {
		return xa;
	}
	
	public float getYA() {
		return ya;
	}
	
	public float getRotation() {
		return rot;
	}
	
	public float getMass() {
		return mass;
	}
	
	public boolean isVisible() {
		return isVisible;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	public void setXA(float xa) {
		this.xa = xa;
	}
	
	public void setYA(float ya) {
		this.ya = ya;
	}
	
	public void setRotation(float rot) {
		this.rot = rot;
	}
	
	public void setMass(float mass) {
		this.mass = mass;
	}
}
