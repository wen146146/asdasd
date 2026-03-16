package com.xhxy.eshop.service.impl.jdbc;

import com.xhxy.eshop.dao.CommentDao;
import com.xhxy.eshop.dao.impl.CommentDaoImpl;
import com.xhxy.eshop.entity.Comment;
import com.xhxy.eshop.service.CommentService;

public class CommentServiceImpl implements CommentService {

	private CommentDao commentDao = new CommentDaoImpl();
	
	@Override
	public Integer save(Comment comment) {
		return commentDao.save(comment);
	}

}
