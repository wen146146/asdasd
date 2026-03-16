package com.xhxy.eshop.dao;

import java.util.List;

import com.xhxy.eshop.entity.Blog;

public interface BlogDao {
	
	// 查询全部推荐文章
	List<Blog> findAll();
	
	// 查询最新的n篇推荐文章：用于首页显示用
	List<Blog> findLatestBlog(Integer n);
	
	// 查询某篇推荐文章
	Blog findById(Integer blogId);
}
