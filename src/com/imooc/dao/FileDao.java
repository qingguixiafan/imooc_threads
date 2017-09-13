package com.imooc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.imooc.action.threadsAction;
import com.imooc.emtity.File;
import com.imooc.util.DBUtil;

public class FileDao {
	public void fileUpload (File file) {
		Connection conn = DBUtil.getConnection();
		String sql = "insert into imooc_threads_file(file_name,content) "
				+ "values(?,?)";
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql);
			stat.setString(1, file.getFile_name());
			stat.setBytes(2, file.getContent());
			stat.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//this just is a test method, it's not used in this project
	public void downLoad() {
		Connection conn = DBUtil.getConnection();
		String sql = "select content from imooc_threads_file where 1=1 "
				+"and id=1";
		PreparedStatement stat = null;
		try {
			stat = conn.prepareStatement(sql);
			ResultSet res = stat.executeQuery();
			while (res.next()) {
				byte[] bytes = res.getBytes("content");
		        threadsAction action = new threadsAction();
		        action.getFileFromBytes(bytes, "I:/copyChild.jpg");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
