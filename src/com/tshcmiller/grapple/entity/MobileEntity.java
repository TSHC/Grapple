package com.tshcmiller.grapple.entity;

import java.awt.Rectangle;

import com.tshcmiller.grapple.Grapple;
import com.tshcmiller.grapple.util.Renderer;
import com.tshcmiller.grapple.world.World;

public abstract class MobileEntity extends Entity {
	
	protected float xa;
	protected float ya; 
	protected float rot;
	
	public MobileEntity(World world, float x, float y, String img) {
		super(world, x, y, img);
		
		this.xa = 0;
		this.ya = 0;
		this.rot = 0;
	}
	
	public boolean isColliding(Entity e) {
		Rectangle rect1 = new Rectangle((int) x, (int) y, texture.getImageWidth(), texture.getImageHeight());
		Rectangle rect2 = new Rectangle((int) e.x, (int) e.y, e.texture.getImageWidth(), e.texture.getImageHeight());
		
		return (rect1.intersects(rect2));
	}
	
	public boolean isOutOfBounds() {
		return (x < 0 || x > Grapple.width || y < 0 || y > Grapple.height);
	}
	
	public void launchFrom(float x, float y, float xa, float ya) {
		this.x = x;
		this.y = y;
		this.xa = xa;
		this.ya = ya;
	}
	
	public void move() {
		y += ya;
		x += xa;
	}
	
	public void update() {
		world.getGravity().apply(this);
	}
	
	public void render() {
		Renderer.renderQuadTexture(texture, rot, x, y);
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
	
	public void setXA(float xa) {
		this.xa = xa;
	}
	
	public void setYA(float ya) {
		this.ya = ya;
	}
	
	public void setRotation(float rot) {
		this.rot = rot;
	}
}
