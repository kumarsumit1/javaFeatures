package com.wipro.volatilevar;

import java.lang.Thread.State;

public class TestThread {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		try {
			Thread t1=new Thread(new VolaThread(),"Thread_ONE");
			Thread t2=new Thread(new VolaThread(), "Thread_TWO");
			t1.start();
		
			t2.start();
      while ( t1.isAlive())
      {
			   System.out.println("thread is alive");
			   Thread.sleep(500);
      }
      Thread t3=new Thread(new VolaThread(), "Thread_3");
      t3.start();
      State thr=t1.getState();
      System.out.println("State of Thread one is"+thr.name() +" ...."+thr.toString());
      t1.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
