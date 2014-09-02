package com.wipro.servlet.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet (urlPatterns={"/LogoutServlet"})
public class LogoutServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2746632809340548113L;

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException ,ServletException{
		response.setContentType("text/html");
		Cookie userCookie=null;
		Cookie[] cookie=request.getCookies();
		if(cookie !=null){
			for(Cookie co:cookie){
				if(co.getName().equalsIgnoreCase("user"))
					userCookie=co;
			}
		}
		//There is no method to remove the cookie but we can set the maximum age to 0 
		//so that it will be deleted from client browser immediately.
	 if(userCookie !=null){
		 userCookie.setMaxAge(0);
		 response.addCookie(userCookie);
	 }
	 response.sendRedirect("login.html");
	}
}
