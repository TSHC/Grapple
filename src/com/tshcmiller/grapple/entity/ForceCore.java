package com.tshcmiller.grapple.entity;

import com.tshcmiller.grapple.Grapple;

public class ForceCore {
	
	private double horizontal;
	private double vertical;
	private double max_force;
	
	public ForceCore() {
		horizontal = 0.0;
		vertical = 0.0;
		max_force = 9.81;
	}
	
	public void applyHorizontalForce(double amount) {
		amount /= 1000;
		double nextAmount = horizontal + amount;
		
		if (!(nextAmount > max_force))
			horizontal += amount;
	}
	
	public void applyVerticalForce(double amount) {
		amount /= 1000;
		double nextAmount = vertical + amount;
		
		if (!(nextAmount > max_force))
			vertical += amount;
	}
	
	public void applyCenterForce(Entity e, double amount) {
		amount /= 1000;
		int wcen = Grapple.SIZE.width >> 1;
		int hcen = Grapple.SIZE.height >> 1;
		double x = e.getX();
		double y = e.getY();
		
		if (wcen > x) {
			x += amount;
		} else {
			x -= amount;
		}
		
		if (hcen > y) {
			y += amount;
		} else {
			y -= amount;
		}
	}
	
	public void applyDoubleForce(double horizontal_amount, double vertical_amount) {
		horizontal_amount /= 1000;
		vertical_amount /= 1000;
		
		horizontal += horizontal_amount;
		vertical += vertical_amount;
	}
	
	public void cancelForces() {
		horizontal = 0.0;
		vertical = 0.0;
	}
	
	public double getHorizontalForce() {
		return horizontal;
	}
	
	public double getVerticalForce() {
		return vertical;
	}
}
