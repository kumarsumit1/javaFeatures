package com.wipro.thread;


public class ThreadTest {

	
	public static void main(String[] args) {
		
		MyRunnable t1=new MyRunnable("thread 1");
		
		
		MyRunnable t2=new MyRunnable("thread 2");
		t1.start();
		t2.start();
		
		
	}

}
