package com.wipro.thread.join;
 
public class ThreadJoinExample {
 
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyRunnable(), "t1");
        Thread t2 = new Thread(new MyRunnable(), "t2");
        Thread t3 = new Thread(new MyRunnable(), "t3");
         
        t1.start();
         
        //start second thread after waiting for 2 seconds or if it's dead
        try {
        	System.out.println("Waiting for thread 1 to join");
        	 System.out.println("The state of thread one is "+t1.getState());
            t1.join(2000);
            System.out.println("joining thread one");
            System.out.println("The state of thread one is "+t1.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         
        t2.start();
        System.out.println("thread two started"); 
        System.out.println("The state of thread one is "+t1.getState());
        //start third thread only when first thread is dead
        try {
        	 System.out.println("Waiting thread to join default");
             System.out.println("The state of thread one is "+t1.getState());
            t1.join();
            System.out.println("The state of thread one is "+t1.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         System.out.println("before statring thread 3 the thread on state is"+t1.getState());
        t3.start();
         System.out.println("The state of thread one is "+t1.getState());
        //let all threads finish execution before finishing main thread
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         
        System.out.println("All threads are dead, exiting main thread");
    }
 
}
 
class MyRunnable implements Runnable{
 
    @Override
    public void run() {
        System.out.println("Thread started:::"+Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread ended:::"+Thread.currentThread().getName());
    }
     
}