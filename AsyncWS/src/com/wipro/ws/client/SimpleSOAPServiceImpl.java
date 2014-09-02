package com.wipro.ws.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

@WebService
public class SimpleSOAPServiceImpl{
 
    @WebMethod
    public NumVo sayHello(@WebParam(name = "echoTEst") String echo,@WebParam(name = "echoT") String echoT) {
        try {
            Thread.sleep(1000*60); // Sleep for 1 minutes
           // return "Hello "+echo;
            NumVo tesObj=new NumVo();
            tesObj.setTest("check");
            tesObj.setTest1("check1");
            tesObj.setTestOne(1);
            return tesObj;
        }
        catch(InterruptedException ex) {
            // Exception Handling code here..
        }
        return null;
    }
 
    public static void main(String args[]) {
        Endpoint.publish("http://localhost:1235/MySOAPService", new SimpleSOAPServiceImpl());
    }
}