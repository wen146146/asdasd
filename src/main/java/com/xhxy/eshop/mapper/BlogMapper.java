package com.xhxy.eshop.mapper;

import com.xhxy.eshop.entity.Blog;
import java.util.List;

public interface BlogMapper {
	// 数据库操作: SELECT * FROM blog
	// 参数: 无
	// 返回: 所有博客列表
	List<Blog> findAll();
	
	// 数据库操作: SELECT * FROM blog ORDER BY create_time DESC LIMIT ?
	// 参数: n - 查询最新几条
	// 返回: 最新博客列表
	List<Blog> findLatestBlog(Integer n);
	
	// 数据库操作: SELECT * FROM blog WHERE id = ?
	// 参数: blogId - 博客ID
	// 返回: 博客对象，找不到返回null
	Blog findById(Integer blogId);
}
