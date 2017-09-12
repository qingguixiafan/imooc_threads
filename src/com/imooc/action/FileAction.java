package com.imooc.action;

import com.imooc.emtity.File;

public class FileAction {
	public File createFile(String fileName, byte[] bytes) {
		File file = new File();
		file.setFile_name(fileName);
		file.setContent(bytes);
		return file;
	}
}
