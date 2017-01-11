package com.tut.javaPuzzlerLockMessBugCaseResolved;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class Worker extends Thread {

	private volatile boolean quittingTime = false;
	private Object lock = new Object();
	
	public void run() {
		while (!quittingTime) {
			
			working();
			System.out.println("Still Working ..!");	
		}
		
		System.out.println("cofee is good ..!");
	}
	
	private void working() {
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
		e.printStackTrace(); }	}
	
	synchronized void quit() throws InterruptedException { 
		synchronized (lock) {
			quittingTime = true;
			System.out.println("calling join");
			join();
			System.out.println("back from join");
	
		}
		
	}
	
	synchronized void keepworking() {
		synchronized (lock) {
		quittingTime = false;
		System.out.println("keep working");
		}
	}
	
	public static void main(String args[]) throws InterruptedException {
		final Worker worker = new Worker();
		worker.start();
		
		Timer t = new Timer(true);
		t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				worker.keepworking();
			}
		}, 500);
		
		Thread.sleep(400);
		worker.quit();
	}
	
}
