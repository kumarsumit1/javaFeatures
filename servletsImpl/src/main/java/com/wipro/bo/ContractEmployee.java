package com.wipro.bo;

import javax.xml.datatype.XMLGregorianCalendar;

public class ContractEmployee {
	
	 
    protected String email;    
    protected String firstName;    
    protected int idEmployee;    
    protected XMLGregorianCalendar joinDate;    
    protected String lastName;
    protected String status;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public int getIdEmployee() {
		return idEmployee;
	}
	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}
	public XMLGregorianCalendar getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(XMLGregorianCalendar joinDate) {
		this.joinDate = joinDate;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}


}
