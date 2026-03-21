package com.xhxy.eshop.service.impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.Comment;
import com.xhxy.eshop.mapper.CommentMapper;
import com.xhxy.eshop.service.CommentService;
import com.xhxy.eshop.util.MybatisUtlils;

public class CommentServiceImpl implements CommentService {

	private CommentMapper getCommentMapper() {
		return MybatisUtlils.getSqlSession().getMapper(CommentMapper.class);
	}
	
	@Override
	public Integer save(Comment comment) {
		// 数据库操作: INSERT INTO comment (...)
		// 参数: comment - 评论对象（需包含博客ID、用户ID、内容等）
		// 返回: 受影响的行数（成功返回1）
		int result = getCommentMapper().save(comment);
		return result;
	}
	
	@Override
	public List<Comment> findByBlogId(Integer blogId) {
		// 数据库操作: SELECT * FROM comment WHERE blog_id = ?
		// 参数: blogId - 博客ID
		// 返回: 该博客的所有评论列表
		return getCommentMapper().findByBlogId(blogId);
	}
}
