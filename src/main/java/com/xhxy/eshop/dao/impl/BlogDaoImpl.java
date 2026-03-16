package com.xhxy.eshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xhxy.eshop.dao.BlogDao;
import com.xhxy.eshop.dao.CommentDao;
import com.xhxy.eshop.entity.Blog;
import com.xhxy.eshop.util.JdbcUtils;

public class BlogDaoImpl implements BlogDao{

	@Override
	public List<Blog> findAll() {
		// SQL：查询全部Blog
		String sql = "select * from blog";
		try(
			Connection conn = JdbcUtils.getConnection();	//静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
		)
		{
			ResultSet rs = pstmt.executeQuery();
			List<Blog> blogList = new ArrayList<Blog>();
			
			while( rs.next()) {
				Blog blog = new Blog();
				blog.setId(rs.getInt("id"));
				blog.setTitle(rs.getString("title"));
				blog.setPic(rs.getString("pic"));
				blog.setContent(rs.getString("Content"));
				blog.setCreateTime(rs.getDate("createtime"));
				blogList.add(blog);
			}
			return blogList;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Blog findById(Integer blogId) {
		// SQL：查询某篇推荐文章
		String sql = "select * from blog where id = ?";
		try(
			Connection conn = JdbcUtils.getConnection();//JDBC工具类
			PreparedStatement pstmt = conn.prepareStatement(sql)
				)
		{
			pstmt.setInt(1, blogId);
			ResultSet rs = pstmt.executeQuery();
			if(rs != null && rs.next()) {
				Blog blog = new Blog();
				blog.setId(rs.getInt("id"));
				blog.setTitle(rs.getString("title"));
				blog.setPic(rs.getString("pic"));
				blog.setContent(rs.getString("Content"));
				blog.setCreateTime(rs.getDate("createtime"));
				
				// 设置 评论
				CommentDao commentDao = new CommentDaoImpl();
				blog.setCommentList(commentDao.findByBlogId(blogId));
				return blog;
			}
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Blog> findLatestBlog(Integer n) {
		// SQL：查询最新的n篇Blog
		String sql = "select * from blog order by createtime desc limit ?";
		try(
			Connection conn = JdbcUtils.getConnection();	//静态方法
			PreparedStatement pstmt = conn.prepareStatement(sql)
		)
		{
			pstmt.setInt(1, n);
			ResultSet rs = pstmt.executeQuery();
			List<Blog> blogList = new ArrayList<Blog>();
			
			while( rs.next()) {
				Blog blog = new Blog();
				blog.setId(rs.getInt("id"));
				blog.setTitle(rs.getString("title"));
				blog.setPic(rs.getString("pic"));
				blog.setContent(rs.getString("Content"));	// 可以省略
				blog.setCreateTime(rs.getDate("createtime"));
				
				blogList.add(blog);
			}
			return blogList;
		}catch(SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
