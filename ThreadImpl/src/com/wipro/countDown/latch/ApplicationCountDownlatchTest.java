package com.wipro.countDown.latch;

public class ApplicationCountDownlatchTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		        boolean result = false;
		        try {
		            result = ApplicationStartupUtil.checkExternalServices();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		        System.out.println("External services validation completed !! Result was :: "+ result);
		    }
		

	

}
