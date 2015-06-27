package com.tshcmiller.grapple.util;

import java.util.HashSet;
import java.util.Set;

import com.tshcmiller.grapple.game.Game;

//A simple lightweight timer
public class Timer {
	
	public static int seconds = 0;
	private static Set<Timer> timers = new HashSet<Timer>();
	
	private long time;
	private long current;
	private long target;
	
	//Create a timer, specify how long this timer will run for
	public Timer(long time) {
		this.time = time;
	}
	
	public static void update(Game game) {
		if (game.isPaused)
			return ;
		
		seconds++;
		
		for (Timer t : timers) {
			t.current = seconds;
		}
	}
	
	public static int timersRunning() {
		return timers.size();
	}
	
	public long getRandomDelay(long min, long max) {
		return (long)(min + (Math.random() * max));
	}
	
	public boolean hasExpired() {
		return (current >= target);
	}
	
	public boolean isRunning() {
		return timers.contains(this);
	}
	
	public long secondsLeft() {
		return (target - current);
	}
	
	public void start() {
		current = seconds;
		target = seconds + time;
		timers.add(this);
	}
	
	public void stop() {
		current = 0;
		target = 0;
		timers.remove(this);
	}
	
	public void setTime(long time) {
		this.time = time;
		stop();
		start();
	}
	
}
