package com.xhxy.eshop.service.impl;

import java.util.List;

import com.xhxy.eshop.dao.BlogDao;
import com.xhxy.eshop.dao.impl.BlogDaoImpl;
import com.xhxy.eshop.entity.Blog;
import com.xhxy.eshop.service.BlogService;

public class BlogServiceImpl implements BlogService {

	private BlogDao blogDao = new BlogDaoImpl();
	@Override
	public List<Blog> findAll() {
		return blogDao.findAll();
	}
	@Override
	public Blog findByBlogId(Integer blogId) {
		return blogDao.findById(blogId);
	}
	@Override
	public List<Blog> findLatestBlog(Integer rows) {
		return blogDao.findLatestBlog(rows);
	}

}
