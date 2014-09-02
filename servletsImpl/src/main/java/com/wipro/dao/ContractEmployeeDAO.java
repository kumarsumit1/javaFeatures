package com.wipro.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.wipro.bo.ContractEmployee;

public class ContractEmployeeDAO extends BaseDAO {

	/**
	 * This class provides implementation for db access for Contract Employees
	 * 
	 * @author Sriram Rajagopalan
	 * @version 1.0
	 * 
	 */

	public static List<ContractEmployee> getAllContractEmployees() {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<ContractEmployee> employeetList = new ArrayList<ContractEmployee>();

		try {

			// Get a connection to the database
			conn = getConnection();

			// Prepare the statement to execute
			stmt = conn.createStatement();

			// Execute the query
			rs = stmt.executeQuery("select * from training.contract_employees");
			
			GregorianCalendar cal = new GregorianCalendar();
			XMLGregorianCalendar calendar = null;

			while (rs.next()) {
				ContractEmployee employee = new ContractEmployee();
				employee.setIdEmployee(rs.getInt("id_employee"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				employee.setEmail(rs.getString("email"));
				cal.setTime(rs.getDate("join_date"));
				calendar = DatatypeFactory.newInstance()
						.newXMLGregorianCalendar(cal);
				employee.setJoinDate(calendar);
				employee.setStatus(rs.getString("status"));
				employeetList.add(employee);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// return the List
		return employeetList;
	}

	public static ContractEmployee getContractEmployeeById(int Id) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ContractEmployee employee = new ContractEmployee();

		try {

			// Get a connection to the database
			conn = getConnection();

			// Prepare the statement to execute
			stmt = conn.createStatement();

			// Execute the query
			//rs = stmt.executeQuery("select * from training.contract_employees where id_employee="+ Id);
			rs = stmt.executeQuery("select * from contract_employees where id_employee="+ Id);
			GregorianCalendar cal = new GregorianCalendar();
			XMLGregorianCalendar calendar = null;

			while (rs.next()) {
				employee.setIdEmployee(rs.getInt("id_employee"));
				employee.setFirstName(rs.getString("first_name"));
				employee.setLastName(rs.getString("last_name"));
				cal.setTime(rs.getDate("join_date"));
				calendar = DatatypeFactory.newInstance()
						.newXMLGregorianCalendar(cal);
				employee.setJoinDate(calendar);
				employee.setStatus(rs.getString("status"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// return the Employee Details

		return employee;

	}

	public static void addContractEmployee(ContractEmployee employee) {

		Connection conn = null;
		Statement stmt = null;

		try {

			// Get a connection to the database
			conn = getConnection();

			// Prepare the statement to execute
			stmt = conn.createStatement();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(employee.getJoinDate().toGregorianCalendar().getTime());

			// Execute the query
			String query = "INSERT INTO training.contract_employees"
					+ "(first_name, last_name, email, join_date, status) VALUES ("
					+ "'" + employee.getFirstName() + "'" + "," + "'"
					+ employee.getLastName() + "'" + "," + "'"
					+ employee.getEmail() + "'" + "," + "'" + date + "'" + ","
					+ "'" + employee.getStatus() + "'" + ")";

			stmt.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void deleteContractEmployee(int Id) {

		Connection conn = null;
		Statement stmt = null;

		try {

			// Get a connection to the database
			conn = getConnection();

			// Prepare the statement to execute
			stmt = conn.createStatement();

			// Execute the query
			String query = "update training.contract_employees set status='inactive' where id_employee="
							+ Id;

			stmt.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {

		// Testing getAllContractEmployees()
		System.out.println("Testing getAllContractEmployees()...");
		List<ContractEmployee> employeetList = getAllContractEmployees();
		Iterator<ContractEmployee> iterator = employeetList.iterator();
		ContractEmployee employee = null;
		while (iterator.hasNext()) {
			employee = (ContractEmployee) iterator.next();
			System.out.println("Employee Name: " + employee.getFirstName()
					+ " " + employee.getLastName());

		}

		// Testing getContractEmployeeById()
		System.out.println("Testing getContractEmployeeById()...");
		employee = getContractEmployeeById(1);
		System.out.println("Employee Name: " + employee.getFirstName() + " "
				+ employee.getLastName());

		// Testing AddContractEmployee()
		GregorianCalendar cal = new GregorianCalendar();
		XMLGregorianCalendar calendar = null;
		System.out.println("Testing AddContractEmployee()...");
		employee.setFirstName("test_first_name");
		employee.setLastName("test_last_name");
		employee.setEmail("test@test4training.com");
		
		cal.setTime(new Date(System.currentTimeMillis()));
		try {
			calendar = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(cal);
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		employee.setJoinDate(calendar);
		employee.setStatus("active");
		addContractEmployee(employee);
		System.out.println("Employee added successfully");

		// Testing deleteContractEmployee()
		System.out.println("Testing deleteContractEmployeeById()...");
		deleteContractEmployee(1);
		System.out.println("Employee deleted successfully");

	}

}
