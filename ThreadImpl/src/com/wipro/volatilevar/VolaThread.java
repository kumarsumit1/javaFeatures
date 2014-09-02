package com.wipro.volatilevar;

public class VolaThread implements Runnable {

	  private volatile int i=0;
	  private int j=0;
	@Override
	public void run() {
		if(Thread.currentThread().getName().equalsIgnoreCase("Thread_ONE")){
			System.out.println(Thread.currentThread().getName());
			System.out.println("volatile var value is "+i);
			System.out.println("private var value is" + j);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i=5;
			System.out.println(Thread.currentThread().getName());
			System.out.println("volatile var value is "+i);
			System.out.println("private var value is" + j);	
		}
		
	}

}
