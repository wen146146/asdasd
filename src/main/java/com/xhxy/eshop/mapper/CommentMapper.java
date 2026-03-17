package com.xhxy.eshop.mapper;

import com.xhxy.eshop.entity.Comment;
import java.util.List;

public interface CommentMapper {
	// 数据库操作: SELECT * FROM comment WHERE blog_id = ?
	// 参数: blogId - 博客ID
	// 返回: 该博客的所有评论列表
	List<Comment> findByBlogId(Integer blogId);
	
	// 数据库操作: INSERT INTO comment (...)
	// 参数: comment - 评论对象
	// 返回: 受影响的行数（成功返回1）
	int save(Comment comment);
}
