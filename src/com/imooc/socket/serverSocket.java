package com.imooc.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.imooc.action.threadsAction;
import com.imooc.dao.FileDao;
import com.imooc.emtity.File;

public class serverSocket {
	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(3000);
		while (true) {
			Socket socket = ss.accept();  
			ObjectInputStream oos = new ObjectInputStream(socket.getInputStream());  
			File file = (File) oos.readObject();
			//这里服务器应该将file对象中的content字节数组保存到数据库中，
			//但是测试时是将其反序列化为文件
//        threadsAction action = new threadsAction();
//        action.getFileFromBytes(file.getContent(), "I:/copyChild.jpg");
			
			FileDao fileDao = new FileDao();
			fileDao.fileUpload(file);
			oos.close();  
			ss.close();  
		}
	}
}
