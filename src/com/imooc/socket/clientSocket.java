package com.imooc.socket;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.SynchronousQueue;

import com.imooc.action.threadsAction;
import com.imooc.dao.FileDao;
import com.imooc.dao.UseDao;
import com.imooc.emtity.File;
import com.imooc.emtity.User;

public class clientSocket extends Thread {
	public static boolean isLogin = false;
	public static Scanner console = new Scanner(System.in);
	public static void main(String[] args) {
		clientSocket socket = new clientSocket();
		socket.start();
	}
	
	public void run() {
		System.out.println("=========welcome to file cloud platform=========");
		System.out.println("we will provid the follow functions,please input suited ID");
		System.out.println("1: new user register");
		System.out.println("2: old user login");
		System.out.println("3: exit program");
		int inputId = console.nextInt();
		clientSocket socket = new clientSocket();
		if (inputId==1) {
			User user = socket.register();
			if (user==null) {
				System.out.println("register is faid, program exit");
			}else{
				isLogin = true;
				System.out.println("register is succed,please enter the path to the upload file ");
				socket.uploadFile();
			}
		} else if (inputId==2) {
			File uploadFile = socket.uploadFile();
			if (uploadFile==null) {
				System.out.println("failed to upload file, program exit ");
			} else {
				System.out.println("succed to upload file,goodbye.");
			}
		} else if(inputId==3){
			System.out.println("goodbye");
			return; 
		} else {
			System.out.println("Input error, program exit");
		}
	}
	
	// register succeed return not null User Object,else return null User object
	public User register() {
		int i = 0;
		User user = null;
		while (i < 3) {
			System.out.println("please input your name");
			String user_name = console.next();
			System.out.println("please input your password");
			String password = console.next();
			System.out.println("please input you password again");
			String confirmPassword = console.next();
			if (!password.equals(confirmPassword)) {
				System.out.println("The password you entered twice is not the same,please Confirm input");
				i++;
			}
			if (password.equals(confirmPassword)) {
				user = new User();
				user.setUser_name(user_name);
				user.setPassword(password);
				//Add user information to the database after the registration is successful
				UseDao dao = new UseDao();
				dao.addUser(user);
				break;
			}
		}
		return user;
	}
	
	
	// upload succeed return not null File Object,else return null File object
	public File uploadFile() {
		int i = 0;
		com.imooc.emtity.File emtityFile = null;
		while (i < 3) {
			System.out.println("please input your file path");
			String path = console.next();
			java.io.File file = new java.io.File(path);
			if (!file.isFile()) {
				System.out.println("you entered a path error");
				i++;
			} else {
				threadsAction action = new threadsAction();
				byte[] bytes = action.getBytesFromFile(file);
				String fileName = file.getName();
				emtityFile = new File();
				emtityFile.setFile_name(fileName);
				emtityFile.setContent(bytes);
				FileDao dao = new FileDao();
				dao.fileUpload(emtityFile);
				System.out.println("upload file succed");
				break;
			}
		}
		return emtityFile;
	}
	
	
	public void creCliSocket(String file_name, byte[] bytes) throws Exception {  
        File  file = new File();
        file.setFile_name(file_name);
        file.setContent(bytes);
        
        Socket socket = new Socket("127.0.0.1", 3000);  
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());  
        oos.writeObject(file);  
        oos.close();  
        socket.close();  
    }  
}
