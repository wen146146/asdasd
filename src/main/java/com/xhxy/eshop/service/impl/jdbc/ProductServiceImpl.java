package com.xhxy.eshop.service.impl.jdbc;

import java.util.List;

import com.xhxy.eshop.dao.ProductDao;
import com.xhxy.eshop.dao.impl.ProductDaoImpl;
import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.service.ProductService;

public class ProductServiceImpl{
	
	private ProductDao productDao = new ProductDaoImpl();
	

	public Product findById(Integer id) {
		return productDao.findById(id);
	}


	public List<Product> findListByCategoryId(Integer categoryId) {
		return productDao.findListByCategoryId(categoryId);
	}


	public List<Product> findAll() {
		return productDao.findAll();
	}


	public List<Product> findHot(Integer n) {
		return productDao.findHot(n);
	}


	public List<Product> findLatest(Integer n) {
		return productDao.findLatest(n);
	}


	public List<Product> findByKeywords(String name, String brief, String detail) {
		// 暂未实现
		return null;
	}


	// 分页查询商品
	// 参数: categoryId - 分类ID（null表示查询所有）, pageNum - 当前页码, pageSize - 每页数量
	// 返回: 分页商品列表
	public List<Product> findByPage(Integer categoryId, int pageNum, int pageSize){
		return null;
	}

	// 统计商品总数
	// 参数: categoryId - 分类ID（null表示统计所有）
	// 返回: 商品总数
	public int countByCategoryId(Integer categoryId){
		return 0;
	}

	public List<Product> findBypage(Integer categoryId, Integer page, Integer size) {
		// 数据库操作: 分页查询商品
		// 参数: categoryId - 分类ID, page - 页码, size - 每页数量
		// 返回: 分页商品列表
		return null;
	}


}
