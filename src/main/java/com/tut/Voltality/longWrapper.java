package com.tut.Voltality;

public class longWrapper {
	
	private Object key = new Object();
	private  long l;
	
	public longWrapper(long l) {
		
		this.l=l;
	}
	
	public long getValue() {
		synchronized (key) {
		return l;
		}
	}
	
	public void incrementValue() {
		
		synchronized (key) {
			l = l + 1;
		}
		
	}
}
