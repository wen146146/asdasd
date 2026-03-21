package com.xhxy.eshop.entity;

import java.io.Serializable;

/**
 * 实体类: 管理员
 * 继承自User类，代表系统管理员
 */
public class Admin extends User implements Serializable {
	private static final long serialVersionUID = 1L;
	private String department;	// 所属部门，例如：研发部、运营部等
	
	// getter和setter
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
}
