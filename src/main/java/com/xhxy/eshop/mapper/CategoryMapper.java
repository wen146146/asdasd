package com.xhxy.eshop.mapper;

import com.xhxy.eshop.entity.Category;
import java.util.List;

public interface CategoryMapper {
	// 数据库操作: SELECT * FROM category WHERE id = ?
	// 参数: id - 分类ID
	// 返回: 分类对象，找不到返回null
	Category findById(Integer id);
	
	// 数据库操作: SELECT * FROM category
	// 参数: 无
	// 返回: 所有分类列表
	List<Category> findAll();
	
	// 数据库操作: SELECT * FROM category WHERE parent_id IS NULL
	// 参数: 无
	// 返回: 顶级分类列表（一级分类）
	List<Category> findTopCategory();
	// 数据库操作: SELECT * FROM category WHERE parent_id = ?
	// 参数: parentId - 父分类ID
	// 返回: 子分类列表
	List<Category> findChildCategory(Integer parentId);
}
