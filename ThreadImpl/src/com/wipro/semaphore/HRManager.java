package com.wipro.semaphore;

import java.util.concurrent.Semaphore;

public class HRManager {
	
	/*
	 * The organization needs to recruit 4 Java developers. HR Manager asks 4 Tech Leads to conduct test and recruit the
	 *  canditates. The problem here is that the Tech Leads have only 2 test paper and the photo copy machine is down. 
	 *  That means at a time only to candidates can give the test and other 2 have to wait.
	 */

	 
	 public static void main(String args[]){
	  Semaphore questionPaperPool = new Semaphore(2);
	  
	  TechLead techLead1 = new TechLead(questionPaperPool,"John TL");
	  TechLead techLead2 = new TechLead(questionPaperPool,"Doe TL");
	  TechLead techLead3 = new TechLead(questionPaperPool,"Mark TL");
	  TechLead techLead4 = new TechLead(questionPaperPool,"Albert TL");
	  
	  
	  techLead1.start();
	  techLead2.start();
	  techLead3.start();
	  techLead4.start();
	  
	  System.out.println("No work for HR manager");
	  
	 }

}
