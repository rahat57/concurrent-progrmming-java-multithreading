package com.tut.SingletonRacecondition;

public class Singleton {

	private static Singleton instance;
	
	private  Singleton() {}
	
	public static  Singleton Singleton() {
		
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
	
}
