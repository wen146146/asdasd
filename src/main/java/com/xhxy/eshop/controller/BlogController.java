package com.xhxy.eshop.controller;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xhxy.eshop.entity.Blog;
import com.xhxy.eshop.entity.Comment;
import com.xhxy.eshop.service.BlogService;
import com.xhxy.eshop.service.CommentService;
import com.xhxy.eshop.service.UserService;
import com.xhxy.eshop.service.impl.mybatis.BlogServiceImpl;
import com.xhxy.eshop.service.impl.mybatis.CommentServiceImpl;
import com.xhxy.eshop.service.impl.mybatis.UserServiceImpl;

// BlogController: 博客/文章控制器
// 访问路径: /blog
// 涉及页面: blog-list.jsp(文章列表), blog-detail.jsp(文章详情)
// 功能: 1.获取文章列表 2.获取文章详情 3.提交评论
@WebServlet("/blog")
public class BlogController  extends BaseServlet {

	private BlogService blogService = new BlogServiceImpl();
	private UserService userService = new UserServiceImpl();
	private CommentService commentService = new CommentServiceImpl();
	
	// 文章列表
	// 功能: 获取所有博客文章列表
	// 参数: 无
	// 返回页面: blog-list.jsp
	// 数据库操作: BlogService.findAll() -> 查询所有文章
	public String index(HttpServletRequest request, HttpServletResponse response) {
		List<Blog> blogs = blogService.findAll();
		
		request.setAttribute("blogs", blogs);
		return "blog-list.jsp";
	}
	
	// 文章详情
	// 功能: 获取单篇博客文章的详细内容，同时获取关联的评论
	// 参数: id（请求参数）- 文章ID
	// 返回页面: blog-detail.jsp
	// 数据库操作: BlogService.findByBlogId() -> 根据ID查询文章
	public String detail(HttpServletRequest request, HttpServletResponse response) {
		Integer blogId = Integer.parseInt(request.getParameter("id"));
		Blog blog = blogService.findByBlogId(blogId);
		
		request.setAttribute("blog", blog);
		return "blog-detail.jsp";
	}
	
	// 提交评论
	// 功能: 用户对文章提交评论
	// 参数: blogId(文章ID), userId(用户ID), content(评论内容)
	// 返回: 重定向到文章详情页（r:表示重定向）
	// 数据库操作: CommentService.save() -> 保存评论
	public String saveComment(HttpServletRequest request, HttpServletResponse response) {
		Integer blogId = Integer.parseInt(request.getParameter("blogId"));
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		String content = request.getParameter("content");
		
		Comment comment = new Comment();
		comment.setBlog(blogService.findByBlogId(blogId));
		comment.setUser(userService.findById(userId));
		comment.setContent(content);
		comment.setCreateTime(new java.util.Date());
		
		commentService.save(comment);
		
		return "r:/blog?method=detail&id="+blogId;
	}
}
