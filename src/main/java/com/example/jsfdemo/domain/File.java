package com.example.jsfdemo.domain;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class File {
	
	private String path = null;
	private String hash = null;
	private String size;
	
	public File(String path, String hash, String size) {
		this.path = path;
		this.hash = hash;
		this.size = size;
	}
	public String getPath() {
		return path;
	}
	@Size(max = 32)
	public String getHash() {
		return hash;
	}
	@Min(0)
	public String getSize() {
		return size;
	}
}
