package com.xhxy.eshop.service.impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.mapper.ProductMapper;
import com.xhxy.eshop.service.ProductService;
import com.xhxy.eshop.util.MybatisUtlils;

public class ProductServiceImpl implements ProductService {
	
	private ProductMapper getProductMapper() {
		return MybatisUtlils.getSqlSession().getMapper(ProductMapper.class);
	}
	
	@Override
	public Product findById(Integer id) {
		// 数据库操作: SELECT * FROM product WHERE id = ?
		// 参数: id - 商品ID
		// 返回: 商品对象，找不到返回null
		return getProductMapper().findById(id);
	}

	@Override
	public List<Product> findListByCategoryId(Integer categoryId) {
		// 数据库操作: SELECT * FROM product WHERE category_id = ?
		// 参数: categoryId - 分类ID
		// 返回: 该分类下的商品列表
		return getProductMapper().findListByCategoryId(categoryId);
	}

	@Override
	public List<Product> findAll() {
		// 数据库操作: SELECT * FROM product
		// 参数: 无
		// 返回: 所有商品列表
		return getProductMapper().findAll();
	}

	@Override
	public List<Product> findHot(Integer n) {
		// 数据库操作: SELECT * FROM product ORDER BY view_count DESC LIMIT ?
		// 参数: n - 查询热门商品的数量
		// 返回: 热门商品列表
		return getProductMapper().findHot(n);
	}

	@Override
	public List<Product> findLatest(Integer n) {
		// 数据库操作: SELECT * FROM product ORDER BY create_time DESC LIMIT ?
		// 参数: n - 查询最新商品的数量
		// 返回: 最新商品列表
		return getProductMapper().findLatest(n);
	}

	@Override
	public List<Product> findByKeywords(String name, String brief, String detail) {
		// 数据库操作: 多条件模糊查询商品
		// 参数: name - 商品名, brief - 简介, detail - 详情
		// 返回: 符合条件的商品列表
		return getProductMapper().findByKeywords(name, brief, detail);
	}
	
	@Override
	public List<Product> findBypage(Integer categoryId, Integer page, Integer size) {
		// 数据库操作: 分页查询商品
		// 参数: categoryId - 分类ID, page - 页码, size - 每页数量
		// 返回: 分页商品列表
		return getProductMapper().findBypage(categoryId, page, size);
	}
	
	@Override
	public Integer countByCategoryId(Integer categoryId) {
		// 数据库操作: 查询指定分类的商品总数
		// 参数: categoryId - 分类ID
		// 返回: 商品总数
		return getProductMapper().countByCategoryId(categoryId);
	}
	
	@Override
	public Integer countAll() {
		// 数据库操作: 查询所有商品总数
		// 参数: 无
		// 返回: 商品总数
		return getProductMapper().countAll();
	}
}
