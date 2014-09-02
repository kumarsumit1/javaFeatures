package com.wipro.servlet.impl;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(description="BasicServlet",
             urlPatterns={"/login"},
             initParams={
		      @WebInitParam(name="user",value="sumit"),
		      @WebInitParam(name="password",value="pass")
		      }           
		)

public class BasicServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4620520389821252118L;
	
	  
    /*public void init() throws ServletException {
        //we can create DB connection resource here and set it to Servlet context
        if(getServletContext().getInitParameter("dbURL").equals("jdbc:mysql://localhost:3306/sakila") &&
                getServletContext().getInitParameter("dbUser").equals("DEV") &&
                getServletContext().getInitParameter("dbUserPwd").equals("test@123"))
        getServletContext().setAttribute("DB_Success", "True");
        else throw new ServletException("DB Connection error");
    }
 */

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		
		System.out.println("hi");
		//get request parameters for userID and password
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");
         
        //get servlet config init params
        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");
        //logging example
        log("User="+user+"::password="+pwd);
         
        if(userID.equals(user) && password.equals(pwd)){
        	HttpSession sess=request.getSession();
        	sess.setAttribute("userSess", user+pwd);
        	//setting session to expiry in 30 mins
        	sess.setMaxInactiveInterval(30*60);
        	 Cookie loginCookie = new Cookie("user",user);
             //setting cookie to expiry in 30 mins
             loginCookie.setMaxAge(30*60);
             response.addCookie(loginCookie);
            //encoding url in case when when cookie is disabled 
             String encodedurl=response.encodeRedirectURL("loginSuccess.jsp");
           // response.sendRedirect("loginSuccess.jsp");
             response.sendRedirect(encodedurl);
        }else{
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);
             
        }
	}
	
 protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
	 System.out.println("in do get method");
 }
}
