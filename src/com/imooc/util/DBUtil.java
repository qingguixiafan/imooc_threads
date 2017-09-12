package com.imooc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * �ṩ�����ݿ����ӵ�connection
 */
public class DBUtil {

	private static final String url = "jdbc:mysql://localhost:3306/imooc";
	private static final String user = "root";
	private static final String password = "root";
	private static Connection con = null;
	
	static {
		try {
			// 1������������
			Class.forName("com.mysql.jdbc.Driver");
			// 2������ݿ�����
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