package com.tut.SingletonDoubleCheckLocking;



public class Singleton {

	private static  Singleton instance;
	private static Object key = new Object();
	
	private Singleton() { }
	
	public static Singleton getInstance(){
		
		if (instance != null) {
			return instance;
		}
		synchronized (key) {
			if (instance != null) {
				instance = new Singleton();
				
			}
			return instance;
		}
		
	}
}
