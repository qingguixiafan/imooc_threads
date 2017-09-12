package com.imooc.test;

import java.io.File;

import com.imooc.action.threadsAction;
import com.imooc.dao.FileDao;
import com.imooc.socket.clientSocket;
import com.imooc.socket.serverSocket;

public class Test {

	public static void main(String[] args) {
		FileDao dao = new FileDao();
		dao.downLoad();
//		threadsAction test = new threadsAction();
//		byte[] bytes = test.getBytesFromFile("I:/child.jpg");
//		File file = new File("I:/child.jpg");
//		
//		//ΕάΏΝ»§¶Λ
//		clientSocket cs = new clientSocket();
//		try {
//			cs.creCliSocket(file.getName(), bytes);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
