
package com.wipro.ws.server;

import java.util.concurrent.Future;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.AsyncHandler;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.Response;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "SimpleSOAPServiceImpl", targetNamespace = "http://client.ws.wipro.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SimpleSOAPServiceImpl {


    /**
     * 
     * @param echo
     * @return
     *     returns javax.xml.ws.Response<com.wipro.ws.server.SayHelloResponse>
     */
    @WebMethod(operationName = "sayHello")
    @RequestWrapper(localName = "sayHello", targetNamespace = "http://client.ws.wipro.com/", className = "com.wipro.ws.server.SayHello")
    @ResponseWrapper(localName = "sayHelloResponse", targetNamespace = "http://client.ws.wipro.com/", className = "com.wipro.ws.server.SayHelloResponse")
    public Response<SayHelloResponse> sayHelloAsync(
        @WebParam(name = "echo", targetNamespace = "")
        String echo);

    /**
     * 
     * @param asyncHandler
     * @param echo
     * @return
     *     returns java.util.concurrent.Future<? extends java.lang.Object>
     */
    @WebMethod(operationName = "sayHello")
    @RequestWrapper(localName = "sayHello", targetNamespace = "http://client.ws.wipro.com/", className = "com.wipro.ws.server.SayHello")
    @ResponseWrapper(localName = "sayHelloResponse", targetNamespace = "http://client.ws.wipro.com/", className = "com.wipro.ws.server.SayHelloResponse")
    public Future<?> sayHelloAsync(
        @WebParam(name = "echo", targetNamespace = "")
        String echo,
        @WebParam(name = "asyncHandler", targetNamespace = "")
        AsyncHandler<SayHelloResponse> asyncHandler);

    /**
     * 
     * @param echo
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sayHello", targetNamespace = "http://client.ws.wipro.com/", className = "com.wipro.ws.server.SayHello")
    @ResponseWrapper(localName = "sayHelloResponse", targetNamespace = "http://client.ws.wipro.com/", className = "com.wipro.ws.server.SayHelloResponse")
    @Action(input = "http://client.ws.wipro.com/SimpleSOAPServiceImpl/sayHelloRequest", output = "http://client.ws.wipro.com/SimpleSOAPServiceImpl/sayHelloResponse")
    public String sayHello(
        @WebParam(name = "echo", targetNamespace = "")
        String echo);

}
