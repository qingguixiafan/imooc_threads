package com.imooc.emtity;

import java.io.Serializable;
import java.util.Arrays;

public class File implements Serializable{
	private int id;
	private String file_name;
	private byte[] content;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "File [id=" + id + ", file_name=" + file_name + ", content=" + Arrays.toString(content) + "]";
	}
	
	
}
