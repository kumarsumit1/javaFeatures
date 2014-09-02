package com.wipro.reentrant.block;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantImpl {
	/*
	 * Hr of a company wants to hire 3 java developer.For this he select 3 Tech Leads to conduct test and select candidates.
	 * One Tech Lead will select one candidate.But their is only one question paper,and xerox machine is not working.So now 
	 * at a time only one Tech Lead can conduct the test.When one Tech Lead is having question paper it conducts the test and 
	 * other Tech Leads have to wait for question paper.When one Tech Lead has taken the test then he gives question paper back.
	 *  Now other Tech Leads can take the question paper and conduct Test. 
	 * 
	 * 
	 */
	
	public static void main(String args[])
	{
	ReentrantLock question_paper=new ReentrantLock();
	new TechLead(question_paper,"John").start();
	new TechLead(question_paper,"Tim").start();
	new TechLead(question_paper,"Mac").start();
	System.out.println("Hr completes his work ");
	}


}
