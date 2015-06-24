package com.tshcmiller.grapple.entity;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;

import java.awt.Rectangle;
import java.io.IOException;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import com.tshcmiller.grapple.Renderable;
import com.tshcmiller.grapple.world.World;

public abstract class Entity implements Renderable {
	
	protected Texture texture;
	protected World world;
	
	protected float x;
	protected float y;
	protected float sxf;
	protected float syf; 
	protected float rot;
//	protected float mass;
	protected boolean isVisible;
	
	public Entity(World world, float mass, String imgPath, float x, float y) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.rot = 0;
//		this.mass = mass;
		this.isVisible = true;
		
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(imgPath));
		} catch (IOException e) {
			System.out.println("Unable to load image at: " + imgPath);
			e.printStackTrace();
		}
	}
	
	public abstract void update(int delta);
	
	public void applyHorizontalGravity(float dsxf) {
		sxf += (dsxf / 1);
	}
	
	public void applyVerticalGravity(float dsyf) {
		syf += (dsyf / 1);
	}
	
	public void applyDiagonalGravity(float dsxf, float dsyf) {
		applyHorizontalGravity(dsxf);
		applyVerticalGravity(dsyf);
	}
	
	public boolean isColliding(Entity e) {
		Rectangle rect1 = new Rectangle((int) x, (int) y, texture.getImageWidth(), texture.getImageHeight());
		Rectangle rect2 = new Rectangle((int) e.x, (int) e.y, e.texture.getImageWidth(), e.texture.getImageHeight());
		
		return (rect1.intersects(rect2));
	}
	
	public void render() {
		glPushMatrix();
		
		texture.bind();
		glTranslatef(x, y, 0);
    	glColor3f(1,1,1);
		
		glBegin(GL_QUADS);
	      glTexCoord2f(0, 0);
	      glVertex2f(0, 0);
	      glTexCoord2f(0, texture.getHeight());
	      glVertex2f(0, texture.getImageHeight());
	      glTexCoord2f(texture.getWidth(), texture.getHeight());
	      glVertex2f(texture.getImageWidth(), texture.getImageHeight());
	      glTexCoord2f(texture.getWidth(), 0);
	      glVertex2f(texture.getImageWidth(), 0);
		glEnd();
		
		glPopMatrix();
	}
	
	public void move() {
		if (y < 10) {
//			isVisible = false;
			y = 10;
			syf /= -2;
		}
		
		if (y > 650) {
//			isVisible = false;
			y = 650;
			syf /= -2;
		}
		
		if (x < 0) {
			x = 0;
			sxf /= - 2;
		}
		
		if (x > 1150) {
			x = 1150;
			sxf /= -2;
		}
		
		x += sxf;
		y += syf;
	}
	
	public void reset() {
		x = (float) (Math.random() * 1150f);
		y = (float) (Math.random() * 600f);
	}
	
	public boolean isVisible() {
		return isVisible;
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
	
	public float getSYF() {
		return syf;
	}
}
