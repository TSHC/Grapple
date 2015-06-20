package com.tshcmiller.grapple.entity;

import com.tshcmiller.grapple.Renderable;

public abstract class Entity implements Renderable {
	
	protected ForceCore forces;
	protected double x;
	protected double y;
	
	
	public Entity(double x, double y) {
		this.x = x;
		this.y = y;
		this.forces = new ForceCore();
	}
	
	public void move() {
		x += forces.getHorizontalForce();
		y += forces.getVerticalForce();
	}
	
	public ForceCore getForces() {
		return forces;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
}
