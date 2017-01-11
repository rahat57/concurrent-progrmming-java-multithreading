package com.tut.Voltality;

public class RaceCondition {

	public static void main(String[] args) throws InterruptedException {
		
		longWrapper l=new longWrapper(0l);
		
		Runnable r = () -> {
			
			for (int i = 0; i <1000; i++) {
				l.incrementValue();
			}
		};

		Thread [] threads = new Thread[1000];
		
		for (int i = 0; i < threads.length; i++) {
			threads[i]= new Thread(r);
			threads[i].start();
		}
		
		for (int i = 0; i < threads.length; i++) {
			threads[i].join();
		}
		
		System.out.println("long value = "+l.getValue());
		
	}

}
