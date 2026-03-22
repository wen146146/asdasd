package com.xhxy.eshop.service.impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.Blog;
import com.xhxy.eshop.mapper.BlogMapper;
import com.xhxy.eshop.service.BlogService;
import com.xhxy.eshop.util.MybatisUtlils;

public class BlogServiceImpl implements BlogService {

	private BlogMapper getBlogMapper() {
		return MybatisUtlils.getSqlSession().getMapper(BlogMapper.class);
	}
	
	@Override
	public List<Blog> findAll() {
		// 数据库操作: SELECT * FROM blog
		// 参数: 无
		// 返回: 所有博客列表
		return getBlogMapper().findAll();
	}
	
	@Override
	public Blog findByBlogId(Integer blogId) {
		// 数据库操作: SELECT * FROM blog WHERE id = ?
		// 参数: blogId - 博客ID
		// 返回: 博客对象，找不到返回null
		return getBlogMapper().findById(blogId);
	}
	
	@Override
	public List<Blog> findLatestBlog(Integer rows) {
		// 数据库操作: SELECT * FROM blog ORDER BY create_time DESC LIMIT ?
		// 参数: rows - 查询最新几条
		// 返回: 最新博客列表
		return getBlogMapper().findLatestBlog(rows);
	}
	
	@Override
	public List<Blog> findBypage(Integer page, Integer size) {
		// 数据库操作: 分页查询博客
		// 参数: page - 页码, size - 每页数量
		// 返回: 分页博客列表
		return getBlogMapper().findBypage(page, size);
	}
	
	@Override
	public Integer countAll() {
		// 数据库操作: 查询博客总数
		// 参数: 无
		// 返回: 博客总数
		return getBlogMapper().countAll();
	}
}
