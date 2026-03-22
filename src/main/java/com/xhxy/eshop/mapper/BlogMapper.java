package com.xhxy.eshop.mapper;

import com.xhxy.eshop.entity.Blog;
import org.apache.ibatis.annotations.Param;
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
	
	// 数据库操作: 分页查询博客
	// 参数: page - 页码, size - 每页数量
	// 返回: 分页博客列表
	List<Blog> findBypage(@Param("page") Integer page, @Param("size") Integer size);
	
	// 数据库操作: 查询博客总数
	// 参数: 无
	// 返回: 博客总数
	Integer countAll();
}
