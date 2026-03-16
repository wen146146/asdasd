package com.xhxy.eshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xhxy.eshop.dao.CommentDao;
import com.xhxy.eshop.dao.UserDao;
import com.xhxy.eshop.entity.Comment;
import com.xhxy.eshop.util.JdbcUtils;

public class CommentDaoImpl implements CommentDao{

	@Override
	public List<Comment> findByBlogId(Integer blogId) {
		// SQL：查询某篇文章的所有评论
		String sql = "select * from comment where blog_id = ?";
		try(
			Connection conn = JdbcUtils.getConnection();//JDBC工具类的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
		)
		{
			pstmt.setInt(1, blogId);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null) {
				List<Comment> commentList = new ArrayList<>();
				while( rs.next()) {
					Comment comment = new Comment();
					comment.setId(rs.getInt("id"));
					comment.setContent(rs.getString("content"));
					comment.setCreateTime(rs.getDate("createtime"));
					
					// 设置 评论人
					UserDao userDao = new UserDaoImpl();
					comment.setUser(userDao.findById(rs.getInt("user_id")));
					
				    commentList.add(comment);
				}
				return commentList;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public int save(Comment comment) {
		String sql = "insert into comment(content,createtime,blog_id,user_id)"
				+ " value(?,?,?,?)";
		try(
			Connection conn = JdbcUtils.getConnection();//JdbcUtils的静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
			)
		{
			pstmt.setString(1, comment.getContent());
			pstmt.setDate(2, new java.sql.Date(comment.getCreateTime().getTime()));
			pstmt.setInt(3, comment.getBlog().getId());
			pstmt.setInt(4, comment.getUser().getId());
			
			int rows = pstmt.executeUpdate();
			return rows;
			
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

}
