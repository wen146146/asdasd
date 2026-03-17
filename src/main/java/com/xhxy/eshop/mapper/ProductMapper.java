package com.xhxy.eshop.mapper;

import com.xhxy.eshop.entity.Product;
import java.util.ArrayList;
import java.util.List;

public interface ProductMapper {
	// 数据库操作: SELECT * FROM product WHERE id = ?
	// 参数: id - 商品ID
	// 返回: 商品对象，找不到返回null
	Product findById(Integer id);
	
	// 数据库操作: SELECT * FROM product WHERE category_id = ?
	// 参数: categoryId - 分类ID
	// 返回: 该分类下的商品列表
	ArrayList<Product> findListByCategoryId(Integer categoryId);
	
	// 数据库操作: SELECT * FROM product
	// 参数: 无
	// 返回: 所有商品列表
	List<Product> findAll();
	
	// 数据库操作: SELECT * FROM product ORDER BY view_count DESC LIMIT ?
	// 参数: n - 查询热门商品的数量
	// 返回: 热门商品列表
	List<Product> findHot(Integer n);
	
	// 数据库操作: SELECT * FROM product ORDER BY create_time DESC LIMIT ?
	// 参数: n - 查询最新商品的数量
	// 返回: 最新商品列表
	List<Product> findLatest(Integer n);
}
