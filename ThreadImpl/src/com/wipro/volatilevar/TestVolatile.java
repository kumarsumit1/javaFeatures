package com.wipro.volatilevar;

/*
 * Ideally, if keepRunning wasn't volatile, thread should keep on running indefinetely.
 *  But, it does stop after few seconds.
 * 
 *  When a variable is volatile, it is guaranteeing that it will not be cached and 
 *  that different threads will see the updated value. However, not marking it volatile 
 *  does not guarantee the oppostie. 
 * 
 */
class TestVolatile extends Thread {

    boolean keepRunning = true;

    public void run() {
        while (keepRunning) {
        }

        System.out.println("Thread terminated.");
    }

    public static void main(String[] args) throws InterruptedException {
    	TestVolatile t = new TestVolatile();
        t.start();
        Thread.sleep(1000);
        t.keepRunning = false;
        System.out.println("keepRunning set to false.");
    }
}