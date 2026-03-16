package com.xhxy.eshop.entity;

import java.util.List;

/**
 * 实体类：商品分类
 *
 */
public class Category {
	private Integer id;
	private String name;	// 分类名称
	private int grade;		// 分类的层级：顶层为0，第2层为1
	private String icon;	// 顶层分类的小图标，用font awesome字符表示
	
	private Category parent;		// 父分类
	private List<Category> children;	//子分类
	
	// getter 和 setter
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Category getParent() {
		return parent;
	}
	public void setParent(Category parent) {
		this.parent = parent;
	}
	public List<Category> getChildren() {
		return children;
	}
	public void setChildren(List<Category> children) {
		this.children = children;
	}
}
