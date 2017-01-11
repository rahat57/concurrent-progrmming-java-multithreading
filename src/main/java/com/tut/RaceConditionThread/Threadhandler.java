package com.tut.RaceConditionThread;

public class Threadhandler {

	public static void main(String[] args) {

		Runnable runable = () -> {
			String name =Thread.currentThread().getName();
			System.out.println("running in thread "+name);
			
		};
		
		Thread t = new Thread(runable);
		t.setName("testing thread");
		t.start();
	}

	
	
	
}
