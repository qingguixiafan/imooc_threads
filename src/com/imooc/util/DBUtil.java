package com.imooc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * provide connection object, it's a connection to database
 */
public class DBUtil {

	private static final String url = "jdbc:mysql://localhost:3306/imooc";
	private static final String user = "root";
	private static final String password = "root";
	private static Connection con = null;
	
	static {
		try {
			// 1 load driver program
			Class.forName("com.mysql.jdbc.Driver");
			// 2 get database connection
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		return con;
	}
}