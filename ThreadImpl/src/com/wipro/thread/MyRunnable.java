package com.wipro.thread;

import java.sql.Date;

public  class MyRunnable implements Runnable {
	  String test="";
	  Thread t ;
	  Date date ;
	  

	String threadname =Thread.currentThread().getName();
	MyRunnable()
	{
		
	}
	
	MyRunnable(String threadname1)
	{	
		threadname1=threadname;
	}
	@Override
	public void run() {
		System.out.println("In run method "+test);
		
		System.out.println("thread name is " +Thread.currentThread().getName());
	}
		
	public void start(){
		System.out.println("in start"+ threadname);
		t = new Thread (threadname);
		t.start();
	}

}
