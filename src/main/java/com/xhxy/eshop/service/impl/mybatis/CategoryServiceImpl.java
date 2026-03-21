package com.xhxy.eshop.service.impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.Category;
import com.xhxy.eshop.mapper.CategoryMapper;
import com.xhxy.eshop.service.CategoryService;
import com.xhxy.eshop.util.MybatisUtlils;

public class CategoryServiceImpl implements CategoryService {

	private CategoryMapper categoryMapper = MybatisUtlils.getSqlSession().getMapper(CategoryMapper.class);
	
	@Override
	public Category findById(Integer id) {
		// 数据库操作: SELECT * FROM category WHERE id = ?
		// 参数: id - 分类ID
		// 返回: 分类对象，找不到返回null
		return categoryMapper.findById(id);
	}

	@Override
	public List<Category> findAll() {
		// 数据库操作: SELECT * FROM category
		// 参数: 无
		// 返回: 所有分类列表
		return categoryMapper.findAll();
	}

	@Override
	public List<Category> findTopCategory() {
		// 数据库操作: SELECT * FROM category WHERE parent_id IS NULL
		// 参数: 无
		// 返回: 顶级分类列表（一级分类）
		return categoryMapper.findTopCategory();
	}
	public List<Category> findChildCategory(Integer id){
		// 数据库操作: SELECT * FROM category WHERE parent_id IS NULL
		// 参数: 父类id
		// 返回: 低级分类列表（一级分类）
		return categoryMapper.findChildCategory(id);
	}
}
