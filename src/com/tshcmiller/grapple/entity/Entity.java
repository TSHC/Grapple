package com.tshcmiller.grapple.entity;

import java.util.HashSet;
import java.util.Set;

import org.newdawn.slick.opengl.Texture;

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

	protected boolean isVisible;
	
	public Entity(World world, float x, float y) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.isVisible = true;
	}
	
	public Entity(World world, float x, float y, String img) {
		this.texture = new Loader(img).getTexture();
		this.world = world;
		this.x = x;
		this.y = y;

		this.isVisible = true;
	}
	
	public abstract void update(int delta);
	
	public void addForDeletion() {
		entitiesToDelete.add(this);
	}
	
	public void render() {
		Renderer.renderQuadTexture(texture, 0, x, y);
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
	

}
