package com.xhxy.eshop.service;

import java.util.List;

import com.xhxy.eshop.entity.Category;

public interface CategoryService {
	// 获取某个id号的分类
	public Category findById(Integer id);
	
	// 获取全部的分类项
	public List<Category> findAll();
	
	// 获取顶层分类项
	public List<Category> findTopCategory();
}
