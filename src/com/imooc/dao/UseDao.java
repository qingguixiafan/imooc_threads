package com.imooc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.imooc.emtity.User;
import com.imooc.util.DBUtil;

/**
 * �ṩ���û��������صķ���
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
		// ��user.getUser_name()ǰ��ӡ�������Ϊsql�����Ҫ���ַ������͵ı����ӡ���
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
