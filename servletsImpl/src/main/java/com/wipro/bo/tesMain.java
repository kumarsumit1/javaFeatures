package com.wipro.bo;

import com.wipro.dao.ContractEmployeeDAO;

public class tesMain {

	/**
	 * @param args
	 */
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			ContractEmployeeDAO ce=new ContractEmployeeDAO();
			ContractEmployee cd=ce.getContractEmployeeById(4);
		    System.out.println(cd.getFirstName());			
		}finally{
			
		}
		
	}
	
}
