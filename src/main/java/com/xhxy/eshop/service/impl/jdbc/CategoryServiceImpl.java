package com.xhxy.eshop.service.impl.jdbc;

import java.util.List;

import com.xhxy.eshop.dao.CategoryDao;
import com.xhxy.eshop.dao.impl.CategoryDaoImpl;
import com.xhxy.eshop.entity.Category;
import com.xhxy.eshop.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao = new CategoryDaoImpl();
	
	@Override
	public Category findById(Integer id) {
		return categoryDao.findById(id);
	}

	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public List<Category> findTopCategory() {
		return categoryDao.findTopCategory();
	}

}
