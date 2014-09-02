package com.wipro.ws.client;

import java.util.concurrent.Future;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

import com.wipro.ws.server.SayHelloResponse;
import com.wipro.ws.server.SimpleSOAPServiceImplService;

public class Client {
    protected static Client theClient;

	public static void main(String args[]) throws Exception {
        SimpleSOAPServiceImplService service = new SimpleSOAPServiceImplService();
        com.wipro.ws.server.SimpleSOAPServiceImpl port = service.getSimpleSOAPServiceImplPort();
 
       // port.sayHelloAsync("This is Asynchrous call.. ");
        // port.sayHello("This is synchronous call");
 
        System.out.println("Execute this statement before async call is complete.. ");
        
        
        // synchronous:
        System.out.println("Airstrip One / Ganymede exchange rate, retrieved synchronously, is: " + port.sayHello("This is synchronous call"));

        // asynchronous with polling:
        try
        {
          Response<SayHelloResponse> response = port.sayHelloAsync("This is Asynchrous call.. ");
          Thread.sleep (2000L);
          SayHelloResponse output = response.get();
          System.out.println("--> retrieved via polling: " + output.getReturn());
        }
        catch (Exception exc)
        {
          System.out.println(exc.getClass().getName() + " polling for response: " + exc.getMessage());
        }
       // theClient=this;
        // asynchronous with callback:
        GetAsyncCallbackHandler getCallback=new GetAsyncCallbackHandler();
        System.out.println("Before callback");
        Future<?>responseCallback;
        responseCallback =  port.sayHelloAsync("Async with callback", getCallback);
        Thread.sleep (2000);
        String result = getCallback.getResponse().getReturn();
        System.out.println("After Callback" +result);
        /*port.sayHelloAsync("Async with callback", getCallback
        {
          public void handleResponse(Response<SayHelloResponse> response)
          {
            System.out.println("In AsyncHandler");
            try
            {
             // theClient.setCurrencyExchangeRate(response.get().getReturn());
            	theClient.setResponse(response.get().getReturn());
            	System.out.println((response.get().getReturn()));
            }
            catch (Exception exc)
            {
                System.out.println(exc.getClass().getName() + " using callback for response:" + exc.getMessage());
            }
          }
        });
 */
    }

	protected void setResponse(String return1) {
		System.out.println("In callback"+return1);
		
	}
}