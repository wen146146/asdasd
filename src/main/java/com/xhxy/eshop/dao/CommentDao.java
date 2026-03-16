package com.xhxy.eshop.dao;

import java.util.List;

import com.xhxy.eshop.entity.Comment;

public interface CommentDao {
	// 查询某篇推荐文章的全部评论comment
	public List<Comment> findByBlogId(Integer blogId);
	
	// 保存某用户对某文章的评论 comment
	public int save(Comment comment);
}
