package com.xhxy.eshop.service.impl.jdbc;

import com.xhxy.eshop.dao.CommentDao;
import com.xhxy.eshop.dao.impl.CommentDaoImpl;
import com.xhxy.eshop.entity.Comment;
import com.xhxy.eshop.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {

	private CommentDao commentDao = new CommentDaoImpl();
	
	@Override
	public Integer save(Comment comment) {
		return commentDao.save(comment);
	}
	public List<Comment> findByBlogId(Integer blogId) {
		// 数据库操作: SELECT * FROM comment WHERE blog_id = ?
		// 参数: blogId - 博客ID
		// 返回: 该博客的所有评论列表
		return commentDao.findByBlogId(blogId);
	}

}
