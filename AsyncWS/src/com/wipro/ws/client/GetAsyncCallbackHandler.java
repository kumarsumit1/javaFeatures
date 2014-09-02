package com.wipro.ws.client;

import java.util.concurrent.ExecutionException;

import javax.xml.ws.AsyncHandler;
import javax.xml.ws.Response;

import com.wipro.ws.server.SayHelloResponse;

public class GetAsyncCallbackHandler implements AsyncHandler<SayHelloResponse> {
	 private SayHelloResponse output;
	@Override
	public void handleResponse(Response<SayHelloResponse> response) {
		
		     try {
		        output = response.get();
		     } catch (ExecutionException e) {
		        e.printStackTrace ();
		     } catch (InterruptedException e) {
		        e.printStackTrace ();
		     }
		
	}
	SayHelloResponse getResponse (){
        return output;
  }
}
