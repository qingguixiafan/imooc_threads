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
			FileDao fileDao = new FileDao();
			fileDao.fileUpload(file);
			oos.close();  
			ss.close();  
		}
	}
}
