package com.imooc.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.imooc.dao.UseDao;
import com.imooc.emtity.User;

public class threadsAction {

	public static void main(String[] args) {
		UseDao userDao = new UseDao();
		User user = new User();
		user.setUser_name("jack");
		user.setPassword("2555");
		// userDao.addUser(user);
//		System.out.println(userDao.login(user));
//		
//		threadsAction test = new threadsAction();
//		byte[] bytes = test.getBytesFromFile("I:/child.jpg");
//		File file = new File("I:/child.jpg");
//		System.out.println(file.getName());
//		
//		FileAction action = new FileAction();
//		com.imooc.emtity.File file2 = action.createFile(file.getName(), bytes);
//		test.getFileFromBytes(bytes, "I:/copyChild.jpg");
	}
    
	// 将文件转换为字节数组
	public byte[] getBytesFromFile(File file) {
		byte[] bytes = null;
		try {
//			File file = new File(filePath);
			InputStream is = new FileInputStream(file);
			// 获取文件大小
			long length = file.length();
			if (length > Integer.MAX_VALUE) {
				// 文件太大，无法读取
				throw new IOException("File is to large " + file.getName());
			}

			// 创建一个数据来保存文件数据
			bytes = new byte[(int) length];
			// 读取数据到byte数组中
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length && 
					(numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
			// 确保所有数据均被读取
			if (offset < bytes.length) {
				throw new IOException("Could not completely read file " + file.getName());
			}
			// Close the input stream and return bytes
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bytes;
	}
	
	// 将字节数组转换为文件
	public static void getFileFromBytes(byte[] b, String outputFile) {    
        File ret = null;    
        BufferedOutputStream stream = null;    
        try {    
            ret = new File(outputFile);    
            FileOutputStream fstream = new FileOutputStream(ret);    
            stream = new BufferedOutputStream(fstream);    
            stream.write(b);    
        } catch (Exception e) {    
            // log.error("helper:get file from byte process error!");    
            e.printStackTrace();    
        } finally {    
            if (stream != null) {    
                try {    
                    stream.close();    
                } catch (IOException e) {    
                    // log.error("helper:get file from byte process error!");    
                    e.printStackTrace();    
                }    
            }    
        }    
//        return ret;    
    }  
}
