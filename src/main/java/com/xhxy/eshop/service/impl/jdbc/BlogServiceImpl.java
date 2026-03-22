package com.xhxy.eshop.service.impl.jdbc;

import java.util.List;

import com.xhxy.eshop.dao.BlogDao;
import com.xhxy.eshop.dao.impl.BlogDaoImpl;
import com.xhxy.eshop.entity.Blog;
import com.xhxy.eshop.service.BlogService;

public class BlogServiceImpl  {

	private BlogDao blogDao = new BlogDaoImpl();

	public List<Blog> findAll() {
		return blogDao.findAll();
	}

	public Blog findByBlogId(Integer blogId) {
		return blogDao.findById(blogId);
	}

	public List<Blog> findLatestBlog(Integer rows) {
		return blogDao.findLatestBlog(rows);
	}

}
