package com.xhxy.eshop.entity;

/**
 * 实体类：商品图片
 *
 */
public class Picture {
	private Integer id;
	private String file;  // 文件地址
	
	// getter和setter
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}
	
	
}
