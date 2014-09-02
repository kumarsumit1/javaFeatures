<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Login Success Page</title>
</head>
<body>
<% 
//allow only if session exist
String userSes=null;
if(session.getAttribute("userSess")==null){
//	response.sendRedirect("login.html");
} else{
	//session returns vlues as object hence typecasting is to be done.
	userSes=(String)session.getAttribute("userSess");
}
String userName=null;
String jsessID=null;
Cookie[] cookie=request.getCookies();
if(cookie !=null){
	for(Cookie co :cookie){
		System.out.println("The cookie name in jsp is :" + co.getName());
		System.out.println("The cookie value in jsp is :" +co.getValue());
	 if(co.getName().equalsIgnoreCase("user")) userName=co.getValue();
	 if(co.getName().equalsIgnoreCase("JSESSIONID")) jsessID=co.getValue();
	}
// if(userName==null) response.sendRedirect("login.html");
}

%>
<h3>Hi <%=userName %>, Login successful.</h3>
<h3> The JSESSIONID is <%=jsessID %></h3>
<h3> The session value is <%=userSes %></h3>
<a href="<%=response.encodeRedirectURL("checkout.jsp") %>"> Checkout Page</a>
<form action="<%=response.encodeRedirectURL("LogoutServlet") %>" method="post">
<h2> To logout press the logout button</h2>
<input type="submit" value="Logout"> 
</form>
</body>
</html>