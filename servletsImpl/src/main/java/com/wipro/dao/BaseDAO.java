package com.wipro.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public abstract class BaseDAO {

	public static Connection getConnection() {
		Connection con = null;
		PropertyResourceBundle bundle = (PropertyResourceBundle) ResourceBundle
				.getBundle("com.wipro.resource.app");
		String conUrl = bundle.getString("jdbc.conurl");
		String driverName = bundle.getString("jdbc.drivername");
		String databaseName = bundle.getString("jdbc.databasename");
		String usrName = bundle.getString("jdbc.username");
		String usrPass = bundle.getString("jdbc.userpassword");
		try {
			// Loading Driver
			Class.forName(driverName);
			// Getting Connection
			con = DriverManager.getConnection(conUrl + databaseName, usrName,
					usrPass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
