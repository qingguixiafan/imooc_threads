package com.imooc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.imooc.emtity.User;
import com.imooc.util.DBUtil;

/**
 * 提供与用户表操作相关的方法
 */
public class UseDao {
	public void addUser (User user) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement stat = null;
		String sql = "insert into imooc_threads_user"
				+ "(user_name,password) values(?,?)";
		try {
			stat = conn.prepareStatement(sql);
			stat.setString(1, user.getUser_name());
			stat.setString(2, user.getPassword());
			stat.execute();
			System.out.println("Record is inserted into table!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean login (User user) {
		Connection conn = DBUtil.getConnection();
		// 在user.getUser_name()前后加‘’是因为sql语句需要给字符串类型的变量加‘’
		String sql = "select id from imooc_threads_user where 1=1 "
				+"and user_name = '"+ user.getUser_name() + "' and password = "
				+user.getPassword();
		PreparedStatement stat = null;
		boolean result = false;
		try {
			stat = conn.prepareStatement(sql);
			ResultSet res = stat.executeQuery();
			if(res.next()) {
				result = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			return result;
		}
	}
}
