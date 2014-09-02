package com.wipro.callable;
 
import java.util.concurrent.Callable;
 
public class FutureTaskCallable implements Callable<String> {
 
    private long waitTime;
     
    public FutureTaskCallable(int timeInMillis){
        this.waitTime=timeInMillis;
    }
    @Override
    public String call() throws Exception {
        Thread.sleep(waitTime);
        //return the thread name executing this callable task
        return Thread.currentThread().getName();
    }
 
}