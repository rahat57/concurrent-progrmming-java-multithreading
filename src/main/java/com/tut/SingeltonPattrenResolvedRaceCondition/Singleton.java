package com.tut.SingeltonPattrenResolvedRaceCondition;

public class Singleton {

	private static Singleton instance;
	
	private  Singleton() {}
	
	public static synchronized Singleton Singleton() {
		
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
	
}
