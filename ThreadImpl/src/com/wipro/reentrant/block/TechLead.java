package com.wipro.reentrant.block;

import java.util.concurrent.locks.ReentrantLock;


public class TechLead extends Thread
{
ReentrantLock question_paper;
String name;
TechLead(ReentrantLock question_paper,String name)
{
super(name);
this.question_paper=question_paper;
}
public void run()
{
System.out.println(getName()+" is waiting for question paper ");
question_paper.lock();
System.out.println(getName()+" takes the question paper ");
System.out.println(getName()+" starts taking test ");
try
{
Thread.sleep(1000);
}catch(InterruptedException e)
{
System.out.println(getName()+" is interrupted ");
}
System.out.println(getName()+" has taken the test");
System.out.println(getName()+" returns the question paper");
question_paper.unlock();
}
}

