package com.tut.SingletonDoubleCheckLockingResolved;


public class Singleton {

	private static volatile Singleton instance;
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