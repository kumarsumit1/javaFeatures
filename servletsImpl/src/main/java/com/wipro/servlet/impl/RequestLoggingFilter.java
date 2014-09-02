package com.wipro.servlet.impl;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/RequestLoggingFilter")
public class RequestLoggingFilter implements Filter {
	private ServletContext servContext=null;

	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		this.servContext=arg0.getServletContext();
		 this.servContext.log("RequestLoggingFilter initialized");
		Enumeration<String> param=arg0.getInitParameterNames();
		while(param.hasMoreElements()){
			System.out.println("the requestlogging filter init method having init params as :"+param.nextElement());
		}
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		Enumeration<String> attribName=req.getAttributeNames();
		while(attribName.hasMoreElements()){
			System.out.println("The attrib elements in requestLogging is "+attribName.nextElement());
		}
		
		Enumeration<String> paramNames=req.getParameterNames();
		while(paramNames.hasMoreElements()){
			String parName=paramNames.nextElement();
			System.out.println("The parameter name in request is "+parName);
			this.servContext.log(req.getRemoteAddr() + "::Request Params::{"+parName+"="+req.getParameter(parName)+"}");
		}
		 Cookie[] cookies = req.getCookies();
	        if(cookies != null){
	            for(Cookie cookie : cookies){
	                this.servContext.log(req.getRemoteAddr() + "::Cookie::{"+cookie.getName()+","+cookie.getValue()+"}");
	            }
	        }
	        // pass the request along the filter chain
	        chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	
}
