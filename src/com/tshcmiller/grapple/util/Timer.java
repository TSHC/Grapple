package com.tshcmiller.grapple.util;

import java.util.HashSet;
import java.util.Set;

public class Timer {
	
	public static int seconds = 0;
	private static Set<Timer> timers = new HashSet<Timer>();
	
	private boolean isPaused;
	private long timeOut;
	private long timeCurrent;
	private long timeEnd;

	public Timer(long delay) {
		timeCurrent = seconds;
		timeEnd = seconds + delay;
		timers.add(this);
	}
	
	public static void pauseAllTimers() {
		for (Timer t : timers) {
			t.pause();
		}
	}
	
	public static void resumeAllTimers() {
		for (Timer t : timers) {
			t.resume();
		}
	}
	
	public static void updateTimers() {
		seconds++;
		
		for (Timer t : timers) {
			if (!t.isPaused) {
				t.timeCurrent = seconds;
			} else {
				t.timeOut++;
			}
		}
	}
	
	public void addRandomDelay(long max) {
		delay((long) (1 + (Math.random() * max)));
	}
	
	public void addRandomDelay(long min, long max) {
		delay((long)(min + Math.random() * max));
	}
	
	public void delay(long delay) {
		if (hasExpired()) {
			timeCurrent = seconds + delay;
			timeEnd = timeCurrent + delay;
		} else {
			timeCurrent += delay;
			timeEnd += delay;
		}
		
		System.out.println("delayed timer: " + delay);
	}
	
	public void pause() {
		isPaused = true;
	}
	
	public void resume() {
		isPaused = false;
		delay(timeOut);
		timeOut = 0;
	}
	
	public void reset(long delay) {
		timeCurrent = Timer.seconds;
		timeEnd = timeCurrent + delay;
	}
	
	public boolean hasExpired() {
		return (timeCurrent >= timeEnd);
	}
	
	public long getSecondsLeft() {
		return (timeEnd - timeCurrent);
	}
	
	//Timer gravity = new Timer(15);
	
	
}
