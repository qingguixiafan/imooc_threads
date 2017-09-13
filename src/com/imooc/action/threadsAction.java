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

	// Converts files to byte arrays
	public byte[] getBytesFromFile(File file) {
		byte[] bytes = null;
		try {
			InputStream is = new FileInputStream(file);
			// get file size
			long length = file.length();
			if (length > Integer.MAX_VALUE) {
				// The file is too large to be read
				throw new IOException("File is to large " + file.getName());
			}

			bytes = new byte[(int) length];
			// read the data into the byte arrays
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length && 
					(numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
			// Ensure that all data is read
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
	
	
	// Converts byte arrays to file, this is a test method too, not used in project
	public static void getFileFromBytes(byte[] b, String outputFile) {    
        File ret = null;    
        BufferedOutputStream stream = null;    
        try {    
            ret = new File(outputFile);    
            FileOutputStream fstream = new FileOutputStream(ret);    
            stream = new BufferedOutputStream(fstream);    
            stream.write(b);    
        } catch (Exception e) {    
            e.printStackTrace();    
        } finally {    
            if (stream != null) {    
                try {    
                    stream.close();    
                } catch (IOException e) {    
                    e.printStackTrace();    
                }    
            }    
        }    
    }  
}
